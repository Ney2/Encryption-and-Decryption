/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.util.Random;

/**
 *
 * @author userpc
 */
public class OTP {
    public static String generate_pad(int length){
    String pad = "";
    char randomCharacter;
    Random r = new Random();

    for( int i = 0; i < length; i++ ){
        randomCharacter = (char) r.nextInt(256);
        pad += randomCharacter;
        }

    return pad;
    }

/** Encrypts the input text and returns the pad and ciphertext
 * @param text the text you want to encrypt
 * @param pad the one time pad you wan to encrypt the text with
 * @return The ciphertext of the text and pad
 */
public static String encrypt(String text, String pad){

    String ciphertext = "";
    char xoredValue;
    for( int i = 0; i < text.length(); i++ ){
        xoredValue = (char) (text.charAt(i) ^ pad.charAt(i));
        ciphertext += xoredValue;
        }

    return ciphertext;
    }

/** Decrypts the ciphertext using the provided pad
 * @param pad The one time pad you wan to decrypt the ciphertext with
 * @param ciphertext The ciphertext you want to decrypt
 * @return The plaintext decryption of the ciphertext, by using the pad
 */
public static String decrypt( String pad, String ciphertext){
    String plaintext = "";
    char xoredValue;

    for( int i = 0; i < pad.length(); i++ ){
        xoredValue = (char) (ciphertext.charAt(i) ^ pad.charAt(i));

        plaintext += xoredValue;
        }

    return plaintext;
    }

    
}
