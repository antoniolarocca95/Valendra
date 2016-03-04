package valendra;



/*This is the email class for Valendra.
 *
 *This class is respnsible for constructings email messages and sending them over
 *SMTP (i think).
 *
 */
public class Email{
	private String to;
	private String from;
	private String subject;
	private String message;
	private String cc;
	private String bcc;
	
	/*Gonna probably add a builder for this.
	 *
	 */
	public Email(String to, String from, String subject, String message, String cc, String bcc){
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.message = message;
		this.cc = cc;
		this.bcc = bcc;
	}

	/*GETTERS AND SETTERS HERE*/
	public String getTo(){
		return this.to;
	}

	public String getFrom(){
		return this.from;
	}

	public String getSubject(){
		return this.subject;
	}

	public String getMessage(){
		return this.message;
	}

	public String getCc(){
		return this.cc;
	}

	public String getBcc(){
		return this.bcc
	}

	
	/*Send email over SMTP(?)
	 *
	 *@return true->success ; false->unsuccessful
	 */
	public bool send(){
		return null;
	}



}








