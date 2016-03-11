package searchengine;

/*Search Engine for Valendra
 *
 *This class is responsible for taking the parsed user input
 *and passing it into the database handler to receive
 *a list of related documents.
 *
 *This will output a list of documents to be displaeyed on the front end.
 */
public class SearchEngine{

	private String[][] parsedInput;

	public SearchEngine(String[][] parsedInput){
		this.parsedInput = parsedInput;
	}

	/*Gets parsedInput
	 *
	 *@returns parsedInput
	 */
	public String[][] getParsedInput(){
		return this.parsedInput;
	}

	/*Queries the database using the parsed user input.
	 *
	 *@returns a string of documents that relate to user input.
	 */
	public String[] queryDatabase(){
		return null;
	}




}
