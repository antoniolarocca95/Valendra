package searchengine;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

/*Search Engine for Valendra
 *
 *This class is responsible for taking the parsed user input
 *and passing it into the database handler to receive
 *a list of related documents.
 *
 *This will output a list of documents to be displaeyed on the front end.
 */
public class SearchEngine {

	private String[][] parsedInput;

	public SearchEngine(String[][] parsedInput) {
		this.parsedInput = parsedInput;
	}

	/*
	 * Gets parsedInput
	 *
	 * @returns parsedInput
	 */
	public String[][] getParsedInput() {
		return this.parsedInput;
	}

	/*
	 * Queries the database using the parsed user input.
	 *
	 * @returns a string of documents that relate to user input.
	 */
	public static void createIndex() throws IOException {
		INDEX_DIRECTORY = "indexDirectory";
		FILES_DIRECTORY = "filesDirectory";
		StandardAnalyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter w = new IndexWriter(INDEX_DIRECTORY, config);
		File dir = new File(FILES_DIRECTORY);
		File[] files = dir.listFiles();

		for (File file : files) {
			Document document = new Document();
			
			String path = file.getCanonicalPath();
			document.add(new Field("path", path, Field.Store.YES, Field.Index.UN_TOKENIZED));
			
			Reader reader = new FileReader(file);
			document.add(new Field("contents", contents));
		
			indexWriter.addDocument(document);
		}
		indexWriter.optimize();
		indexWriter.close();
	}

	public static void search(String searchString) throws IOException, ParseException {
		Directory directory = FSDirectory.getDirectory(INDEX_DIRECTORY);
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		
		StandardAnalyzer analyzer = new StandardAnalyzer();
		QueryParser queryParser = new QueryParser("contents", analyzer);
		Query query = queryParser.parse(searchString);
		Hits hits = indexSearcher.search(query);
		
		Iterator<Hit> it = hits.iterator();
		displayResults(it);
	}

	public static void displayResults(Iterator<Hit> it) {
		while (it.hasNext()) {
			Hit hit = it.next();
			Document document = hit.getDocument();
			String path = document.get("path");
			System.out.println("Hit: " + path);
		}
	}

	public static void queryDatabase(String query) throws IOException, ParseException {
		StandardAnalyzer analyzer = new StandardAnalyzer();

		// 1. create the index
		Directory index = new RAMDirectory();

		IndexWriterConfig config = new IndexWriterConfig(analyzer);

		IndexWriter w = new IndexWriter(index, config);

		addDoc(w, "Lucene in Action", "193398817");
		addDoc(w, "Lucene for Dummies", "55320055Z");
		addDoc(w, "Managing Gigabytes", "55063554A");
		addDoc(w, "The Art of Computer Science", "9900333X");
		w.close();

		// 2. query
		String querystr = query.length() > 0 ? query : "lucene";

		Query q1 = new QueryParser("title", analyzer).parse(querystr);
		Query q2 = new QueryParser("contents", analyzer).parse(querystr);

		// 3. search
		int hitsPerPage = 10;
		IndexReader reader = DirectoryReader.open(index);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs docs1 = searcher.search(q1, hitsPerPage);
		TopDocs docs2 = searcher.search(q2, hitsPerPage);
		ScoreDoc[] hits = (docs1.scoreDocs + docs2.scoreDocs);

		// 4. display results
		System.out.println("Found " + hits.length + " hits.");
		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			System.out.println((i + 1) + ". " + "\t" + d.get("title"));
		}

		// reader can only be closed when there
		// is no need to access the documents any more.
		reader.close();
	}

	private static void addDoc(IndexWriter w, String title, String contents) throws IOException {
		Document doc = new Document();
		doc.add(new TextField("title", title, Field.Store.YES));

		doc.add(new StringField("contents", contents, Field.Store.YES));
		w.addDocument(doc);
	}
}
