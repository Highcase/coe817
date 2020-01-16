package part2;

import java.util.Scanner;

public class Vigenere {

	private static final int ASCII_A = 65;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String plainText = "", encoded = "", decoded = "", key = null;
		System.out.println("Note: This program will work for any message/key used with/without spaces, caps/without caps.");
		
		System.out.println("Please enter the message you wish to encrypt (i.e.\"TO BE OR NOT TO BE" 
				+ " THAT IS THE QUESTION\").");
		plainText = scan.nextLine().replace(" ", "").toUpperCase();
	
		System.out.println("Message to encrypt: " + plainText);
		
		System.out.println("Please enter the key you wish to use (i.e. \"RELATIONS\").");
		key = scan.next().toUpperCase();
		
		// encoding algorithm
		int ki = 0, mi = 0, ci = 0;
		for(int i = 0; i < plainText.length(); i++) {
			ki = (int) key.charAt(i % key.length()) - ASCII_A;
			mi = (int) plainText.charAt(i) - ASCII_A;
			encoded += (char)((mi + ki) % 26 + ASCII_A);
		}
		
		System.out.println("This is the encoded message: " + encoded);
		
		// decoding algorithm
		for(int i = 0; i < encoded.length(); i++) {
			ki = (int) key.charAt(i % key.length()) - ASCII_A;
			ci = (int) encoded.charAt(i) - ASCII_A;
			decoded += (char)((ci - ki + 26) % 26 + ASCII_A);
		}
		
		System.out.println("This is the decoded message: " + decoded);
		
		scan.close();
	}

}
