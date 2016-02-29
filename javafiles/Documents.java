package project;

public class Documents {
  static final int TYPE_HTML = 0;
  static final int TYPE_TXT = 1;
  static final int TYPE_PDF = 2;
  
  int docType;
  int rating;
  String uploader;
  String[] tags;
  String[] comments;
  
  public Documents() {
    
  }

  public int getDocType() {
    return docType;
  }

  public void setDocType(int docType) {
    this.docType = docType;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getUploader() {
    return uploader;
  }

  public void setUploader(String uploader) {
    this.uploader = uploader;
  }

  public String[] getTags() {
    return tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
  }

  public String[] getComments() {
    return comments;
  }

  public void setComments(String[] comments) {
    this.comments = comments;
  }
}
