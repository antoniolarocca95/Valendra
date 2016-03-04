package valendra;



/*Parser class for Valendra.
 *
 *This class is responsible for parsing user input for queries.
 *It identifies boolean operators in the user input and returns 
 *a nicely packaged set of strings for the search engine to use.s 
 *
 *@author pberoy
 */
public class UserInputParser{

	private String input;
	
	public UserInputParser(String input){
		this.input = input;
	}

	/*Returns the user input.
	 *
	 *@return The user input.
	 */
	public String getInput(){
		return this.input;
	}

	/*Set input
	 *s
	 */
	public void setInput(input){
		this.input = input;
	}

	/*@author: pberoy
	 *Parse the user input.
	 *AND in user input is read as a boolean AND
	 *OR in user input is read as a boolean OR
	 *NOT in user input is read as a boolean NOT
	 *
	 *@returns a nested array of strings of the form 
	 *	[[AND terms], [OR terms], [NOT terms]] ... maybe
	 *
	 *
	 *
	 */
	public String[][] parseInput(){
		return null;
	}

}
