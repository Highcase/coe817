package coe817lab2;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class JEncrypRSA {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] userInput, encryptedOutput, decryptedOutput;
		Scanner scan;
		KeyPairGenerator keyGenerator;
		KeyPair rsaKeyPair;
		Key publicKey, privateKey;
		Cipher rsaCipherObj = null;
		
		System.out.println("Please enter the message \"No body can see me\".");
		scan = new Scanner(System.in);
		userInput = scan.nextLine().getBytes();
		scan.close();
		
		try {
			// A key pair generator is used to generate private/public 
			//keys for assymetric algorithms.
			keyGenerator = KeyPairGenerator.getInstance("RSA");
			rsaKeyPair = keyGenerator.generateKeyPair();
			publicKey = rsaKeyPair.getPublic();
			privateKey = rsaKeyPair.getPrivate();
			
			// instantiate the Cipher object passing the algorithm used.
			rsaCipherObj = Cipher.getInstance("RSA");
			// set the cipher object to encrypt operation mode and initialize.
			rsaCipherObj.init(Cipher.ENCRYPT_MODE, publicKey);
			// encrypt the message
			encryptedOutput = rsaCipherObj.doFinal(userInput);
			//print to screen the encrypted message byte code.
			System.out.println("\n" + "Encrypted byte code " + encryptedOutput.toString() + "\n");
			System.out.println("Encrypted string format: " + new String(encryptedOutput) + "\n");
			// set the cipher object to decrypt operation mode and reinitialize.
			rsaCipherObj.init(Cipher.DECRYPT_MODE, privateKey);
			// decrypt the message
			decryptedOutput = rsaCipherObj.doFinal(encryptedOutput);
			//print to screen the decrypted message byte code.
			System.out.println("Decrypted byte code " + decryptedOutput.toString() + "\n");
			System.out.println("Decrypted string format: " + new String(decryptedOutput)+ "\n");
		}catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | 
				IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
}
