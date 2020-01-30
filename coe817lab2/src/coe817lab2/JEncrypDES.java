package coe817lab2;
import java.util.Scanner;

import java.security.*;
import javax.crypto.*;



public class JEncrypDES {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] userInput, encryptedOutput, decryptedOutput;
		Scanner scan;
		KeyGenerator keyGenerator;
		SecretKey desKey;
		Cipher desCipherObj = null;
		
		System.out.println("Please enter the message \"No body can see me\".");
		scan = new Scanner(System.in);
		userInput = scan.nextLine().getBytes();
		scan.close();
		
		try {
			// A key generator is used to generate secret keys for symmetric algorithms.
			keyGenerator = KeyGenerator.getInstance("DES");
			desKey = keyGenerator.generateKey();
			// instantiate the Cipher object passing the algorithm used.
			desCipherObj = Cipher.getInstance("DES");
			// set the cipher object to encrypt operation mode and initialize.
			desCipherObj.init(Cipher.ENCRYPT_MODE, desKey);
			// encrypt the message
			encryptedOutput = desCipherObj.doFinal(userInput);
			//print to screen the encrypted message byte code.
			System.out.println("Encrypted byte code " + encryptedOutput.toString());
			System.out.println("Encrypted string format: " + new String(encryptedOutput));
			// set the cipher object to decrypt operation mode and reinitialize.
			desCipherObj.init(Cipher.DECRYPT_MODE, desKey);
			// decrypt the message
			decryptedOutput = desCipherObj.doFinal(encryptedOutput);
			//print to screen the decrypted message byte code.
			System.out.println("Decrypted byte code " + decryptedOutput.toString());
			System.out.println("Decrypted string format: " + new String(decryptedOutput));
		}catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | 
				IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

}

