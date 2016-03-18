package console;

import java.io.IOException;
import java.util.Scanner;

import database.DatabaseHandler;
import database.DatabaseUploading;

public class Console {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String input = "";
		String username = "", password = "";
		while (!input.equals("exit")) {
			while (!input.equals("login") && !input.equals("register") && !input.equals("exit")) {
				System.out.println("Do you wish to [login], [register] or [exit]");
				input = sc.nextLine();
			}
			if (input.equals("login")) {
				System.out.println("Type in your username");
				username = sc.nextLine();
				System.out.println("Type in your password");
				password = sc.nextLine();
				if (login(username, password)) {
					searcher();
				} else {
					System.out.println("Invalid user");
				}
			} else if (input.equals("register")) {
				System.out.println("Type in your first name");
				String fname = sc.nextLine();
				System.out.println("Type in your last name");
				String lname = sc.nextLine();
				System.out.println("Type in your email");
				String email = sc.nextLine();
				System.out.println("Type in your username");
				username = sc.nextLine();
				System.out.println("Type in your password");
				password = sc.nextLine();
				System.out.println("Confirm your password");
				String conpassword = sc.nextLine();
				if (password.equals(conpassword)) {
					register(fname, lname, username, password, email);
					searcher();
				} else {
					System.out.println("Passwords did not match");
				}
			}
		}
		sc.close();
		System.exit(0);
	}

	private static void searcher() throws IOException {
		Scanner sc = new Scanner(System.in);
		String input = "";
		while (!input.equals("logout")) {
			while (!input.equals("upload") && !input.equals("search") && !input.equals("logout")) {
				System.out.println("Do you wish to [search], [upload] or [logout]?");
				input = sc.nextLine();
			}
			if (input.equals("upload")) {
				System.out.println("Where is the location of the document to upload?");
				input = sc.nextLine();
				upload(input);
			} else if (input.equals("search")) {
				System.out.println("What would you like to find?");
				input = sc.nextLine();
				search(input);
			}
		}
		sc.close();
	}

	private static void register(String fname, String lname, String username, String password, String email) {
		DatabaseUploading.addUser(fname, lname, email, username, password);
	}

	private static boolean login(String username, String password) {
		return DatabaseHandler.loginUser(username, password);
	}

	private static void search(String query) {

	}

	private static void upload(String doclocation) throws IOException {
		DatabaseUploading.addDocument(doclocation);
	}
}
