/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe817lab3;

/**
 *
 * @author t6collin
 */
import java.io.*;
import java.net.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;


public class ClientSide {
	
    public static void main(String[] args)
    {
        byte[] keyBytes, encryptedOutput, decryptedOutput = null, 
                encryptedInput = null;
        String id = "INITIATOR A";
        String host = "localhost";
        String km = "NETWORK SECURITY";
        int port = 5001;
        SecretKey desKey;
        Cipher desCipherObj = null;
        SecretKeyFactory factory;
        String plaintext;
        
        try {
             System.out.println("CLIENT");
            // Create key out of the string "NETWORK SECURITY".
            // This uses the DESKeySpec to create a key from text.
            keyBytes =  km.getBytes();
            factory = SecretKeyFactory.getInstance("DES");
            desKey = factory.generateSecret(new DESKeySpec(keyBytes));
            FileUtility.getFile("");
            // notify client of attempt to connect
            System.out.println("Connecting to " + host
                    + " on port " + port);
            // attempt the connection to socket.
            Socket client = new Socket(host, port);
            //report connection success to client.
            System.out.println("Connected to "
                    + client.getRemoteSocketAddress() +" success!" + "\n" );
            // print ID message sent.
            System.out.println("Sending client ID to host: " + id + "\n");
            // send ID to host.
            DataOutputStream out =
                    new DataOutputStream(client.getOutputStream());
            out.writeUTF(id);
            
//-------------RECIEVE CIPHER FROM HOST THAT CONTAINS SESSION KEY---------------
            //Recieve cipher from host.
            DataInputStream in =
                        new DataInputStream(client.getInputStream());
            // Read in length of incoming bytes.
            int duration = in.readInt();
            // initialize byte array to contain incoming byte stream.
            if(duration > 0) encryptedInput = new byte[duration];
            in.read(encryptedInput, 0, duration);
            System.out.println("The following cipher was recieved: ");
            System.out.println("Recieved cipher byte code: " + 
                    encryptedInput.toString());
            System.out.println("Recieved cipher string format: " + 
                    new String(encryptedInput) + "\n");
            System.out.println("Decrypting Cipher ...");
            // Create DES Cipher instance for encryption/decryption. 
            desCipherObj = Cipher.getInstance("DES/ECB/PKCS5Padding");
            // set mode to decryption.
            desCipherObj.init(Cipher.DECRYPT_MODE, desKey);
           
            // decrypt the message
            decryptedOutput = desCipherObj.doFinal(encryptedInput);
            //print to screen the decrypted message byte code.
            System.out.println("Decrypted byte code " + 
                    decryptedOutput.toString());
            System.out.println("Decrypted string format: " + 
                    new String(decryptedOutput) + "\n");
            
//-----------Using recieved SESSION KEY RYERSON TO SEND TO HOST----------------
            // parse incoming ciphertext for session key using regex.
            String decryptedText = new String(decryptedOutput);
            String[] decryptedArray = decryptedText.split("\\|");
            
            // Create key out of the string recieved session key.
            // This uses the DESKeySpec to create a key from text.
            keyBytes =  (decryptedArray[0]+ " ").getBytes();
            factory = SecretKeyFactory.getInstance("DES");
            desKey = factory.generateSecret(new DESKeySpec(keyBytes));
           
            // get host's ID from the decrpyted text.
            encryptedOutput = decryptedArray[2].getBytes();
      
            desCipherObj.init(Cipher.ENCRYPT_MODE, desKey);
            encryptedOutput = desCipherObj.doFinal(encryptedOutput);
            
            System.out.println("Extracted Session key: " + decryptedArray[0]);
            System.out.println("Extracted host ID: " + decryptedArray[2]);
            System.out.println("Sending Cipher ...\n");
            // send to client the length of cipher in bytes, then the cipher.
            out.writeInt(encryptedOutput.length);
            out.write(encryptedOutput);
            
            in.close();
            out.close();
            client.close();   
         } catch(IOException | InvalidKeyException | NoSuchAlgorithmException |
                InvalidKeySpecException | IllegalBlockSizeException |
               BadPaddingException | NoSuchPaddingException ex) {
            System.out.println("Error has occured.");
            ex.printStackTrace();
        } 
    }
}
