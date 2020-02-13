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
import java.net.*;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
public class ServerSide extends Thread
{
    
    public static void main(String [] args)
    {
        int port = 5001;
        String id = "RESPONDER B";
        String km = "NETWORK SECURITY";
        String ks = "RYERSON ";
        ServerSocket serverSocket;
        byte[] keyBytes, encryptedOutput, encryptedInput = null, decryptedOutput;
        SecretKey desKey;
        Cipher desCipherObj = null;
        SecretKeyFactory factory;
        String plaintext;
        FileUtility fu = new FileUtility();
        try
        { 
            System.out.println("SERVER");
            // reserve socket and set timeout to ensure socket is closed.
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(100000);
            // display waiting message for server side.
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");

            // Print when client connects to socket.
            Socket server = serverSocket.accept();
            // Print when client connects to socket.
            System.out.println("Connected to "
                    + server.getRemoteSocketAddress()+ "\n");

            // Print recieved input stream from socket.
            DataInputStream in =
                    new DataInputStream(server.getInputStream());
            String clientID = in.readUTF();
            System.out.println("ID recieved: " + clientID+ "\n");
            
// SEND ENCRYPTED SESSION KEY USING THE KEY NETWORK SECURITY-------------------
            // Create DES key for encryption using Km = "NETWORK SECURITY".
            // This uses the DESKeySpec to create a key from text.
            keyBytes = km.getBytes();
            factory = SecretKeyFactory.getInstance("DES");
            desKey = factory.generateSecret(new DESKeySpec(keyBytes));

            //create plaintext version of message to be encrypted. 
            plaintext = ks + "|" + clientID + "|" + id;
            System.out.println("Encrypting and sending the following: " +
                    plaintext);
            
            encryptedOutput = plaintext.getBytes();
            // get cipher instance.
            desCipherObj = Cipher.getInstance("DES/ECB/PKCS5Padding");
            // encrypt message for client.
            desCipherObj.init(Cipher.ENCRYPT_MODE, desKey);
            encryptedOutput = desCipherObj.doFinal(encryptedOutput);

            //Display on server side the encrypted Cipher.
            System.out.println("Sending Encrypted byte code " + 
                    encryptedOutput.toString());
            System.out.println("Sending Encrypted string format: " + 
                    new String(encryptedOutput)+ "\n");

            // send length of ciphertext in bytes, followed by ciphertext.
            DataOutputStream out =
                new DataOutputStream(server.getOutputStream());
            out.writeInt(encryptedOutput.length);
            out.write(encryptedOutput);
            
 //----------------RECIEVE CLIENT'S CIPHER WHEN SESSION KEY IS USED-------------
            // recieve the cipher text. 
            int duration = in.readInt();
            // initialize byte array to contain incoming byte stream.
            if(duration > 0) encryptedInput = new byte[duration];
            in.read(encryptedInput, 0, duration);
            System.out.println("The following cipher was recieved: ");
            System.out.println("Recieved cipher byte code: " + 
                    encryptedInput.toString());
            System.out.println("Recieved cipher string format: " + 
                    new String(encryptedInput) + "\n");
            
            // use session key RYERSON to unlock client's cipher.
            keyBytes = ks.getBytes();
            factory = SecretKeyFactory.getInstance("DES");
            desKey = factory.generateSecret(new DESKeySpec(keyBytes));
            
            // set mode to decryption.
            desCipherObj.init(Cipher.DECRYPT_MODE, desKey);
           System.out.println("Decrypting Recieced Cipher ...");
            // decrypt the message
            decryptedOutput = desCipherObj.doFinal(encryptedInput);
            //print to screen the decrypted message byte code.
            System.out.println("Decrypted byte code " + 
                    decryptedOutput.toString());
            System.out.println("Decrypted string format: " + 
                    new String(decryptedOutput) + "\n");
            
            in.close();
            out.close();
            server.close();
        }catch(SocketTimeoutException s){
            System.out.println("Socket timed out!");

        }catch (IOException | NoSuchAlgorithmException | 
                NoSuchPaddingException | InvalidKeyException | 
                InvalidKeySpecException | IllegalBlockSizeException 
                | BadPaddingException e) {  
           System.out.println("Error related to crypto library! ");
           e.printStackTrace();
        }
    }
}