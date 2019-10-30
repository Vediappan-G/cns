/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptcipher;

/**
 *
 * @author VEDI.G
 */   import java.io.*;
import java.util.*;
import java.lang.*;

public class Cryptcipher {



	static Scanner scanner;
	static String plainText;
	static String cipherText;
	static int k,i,j;
	static int temp;
	static String ch;
	static int opt;
	static String[] dictionary;
	static String s;

	public static void main(String[] args){
		scanner = new Scanner(System.in);
		dictionary = new String[]{"hello","world","goodbye","shreyas"};

		System.out.println("Enter the cipher text: ");
		cipherText = scanner.nextLine();

		for(k=0;k<26;k++)
		{ 
                     //assingn plaintext =null //
			plainText = "";
			for(i=0; i<cipherText.length();i++)
			{
				if(cipherText.charAt(i) == ' ')
				{
					plainText = plainText + ' ';
				}
				else
				{
                                      //temp is ***** ( int ) *****
					temp = cipherText.charAt(i);
					if(Character.isUpperCase(cipherText.charAt(i)))
						temp = ((temp-65+k)%26)+65;
					else
						temp = ((temp-97+k)%26)+97;
					plainText = plainText + (char)temp;
				}
			}

			for(j=0;j<dictionary.length;j++)
			{
				if(plainText.contains(dictionary[j]))
				{
					System.out.println(plainText);
				}
			}
		}
	}

    
}
