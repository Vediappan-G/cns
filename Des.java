/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des;

import java.util.Scanner;


public class DES {



 public static String convert_plaintxt_bin(String str)
    {
        String result = "";
        for(int i=0;i<str.length();i++)
        {
            String temp = Integer.toBinaryString((int)(str.charAt(i)));
            for(int j=0;j<(8-temp.length());j++)
                result += "0";
            result += temp;
        }
        return result;
    }
    public static String convert_bin_plaintxt(String str)
    {
        String result = "";
        for(int i=0;i<str.length();i+=8)
        {
            String sub_str = str.substring(i,i+8);
            int ascii = Integer.parseInt(sub_str,2);
            if(ascii>10)
                result += ((char)ascii);
        }
        return result;
    }
    public static String convert_bin_hex(String str)
    {
        String result = "";
        for(int i=0;i<str.length();i+=64)
        {
            String sub_str = str.substring(i,i+64);
            String temp = Long.toHexString(Long.parseUnsignedLong(sub_str,2));
            for(int j=0;j<(16-temp.length());j++)
                result += "0";
            result += temp;
        }
        return result;
    }
    public static String convert_hex_bin(String str)
    {
        String result = "";
        for(int i=0;i<str.length();i+=16)
        {
            String sub_str = str.substring(i,i+16);
            String temp = Long.toBinaryString(Long.parseUnsignedLong(sub_str,16));
            for(int j=0;j<(64-temp.length());j++)
                result += "0";
            result += temp;
        }
        return result;
    }
    public static String[] keygen(String key)
    {
     String pc1_result="";
     String[] key_arr = new String[17];
     int[][] pc1 = {{57, 49, 41, 33, 25, 17, 9}, {1, 58, 50, 42, 34, 26, 18},
         {10, 2, 59, 51, 43, 35, 27}, {19, 11, 3, 60, 52, 44, 36},
         {63, 55, 47, 39, 31, 23, 15},{ 7, 62, 54, 46, 38, 30, 22},
         {14, 6, 61, 53, 45, 37, 29},{ 21, 13, 5, 28, 20, 12, 4}};
     for(int i=0;i<8;i++)
         for(int j=0;j<7;j++)
         {
             pc1_result+=key.charAt(pc1[i][j]-1);
         }
     String[] C = new String[17];
     String[] D = new String[17];
     C[0] = pc1_result.substring(0, pc1_result.length()/2);
     D[0] = pc1_result.substring(pc1_result.length()/2);
     int[] left_shift = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
     for(int i=1;i<=16;i++)
     {
         C[i] = C[i-1].substring(left_shift[i-1]) + C[i-1].substring(0,left_shift[i-1]);
         D[i] = D[i-1].substring(left_shift[i-1]) + D[i-1].substring(0,left_shift[i-1]);
     }
     int[][] pc2 = {{14, 17, 11, 24, 1, 5}, {3, 28, 15, 6, 21, 10},
         {23, 19, 12 ,4, 26, 8}, {16, 7, 27, 20, 13, 2},
         {41, 52, 31, 37, 47, 55},{30, 40, 51, 45, 33, 48},
         {44, 49, 39, 56, 34, 53},{46, 42, 50, 36, 29, 32}};
     for(int i=1;i<=16;i++)
     {
         String temp = C[i] + D[i];
         String key_temp = "";
         for(int j=0;j<8;j++)
             for(int k=0;k<6;k++)
             {
                 key_temp += temp.charAt(pc2[j][k]-1);
             }
         key_arr[i] = key_temp;
     }
     return key_arr;
    }
    public static String xor(String str1,String str2)
    {
        String result = "";
        for(int i=0;i<str1.length();i++)
        {
            if(str1.charAt(i)==str2.charAt(i))
                result += "0";
            else
                result += "1";
        }
        return result;
    }
    public static String selection(int row,int col,int sbox)
    {
        int S1[][] = {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                     {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                       {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                       {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};
        int S2[][] = {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                        {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                        {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};
  int S3[][] = {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
  {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
  {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
  {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};
 int S4[][] = {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
  {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
  {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
  {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};
  int S5[][] = {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
  {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
  {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
  {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};
  int S6[][] = {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
  {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
  {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
  {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};
  int S7[][] = {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
  {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
  {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
  {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};
  int S8[][] = {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
  {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
  {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
  {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};
 
        int value=0;
        switch(sbox)
        {
            case 1:
                value = S1[row][col];
                break;
            case 2:
                value = S2[row][col];
                break;
            case 3:
                value = S3[row][col];
                break;
            case 4:
                value = S4[row][col];
                break;
            case 5:
                value = S5[row][col];
                break;
            case 6:
                value = S6[row][col];
                break;
            case 7:
                value = S7[row][col];
                break;
            case 8:
                value = S8[row][col];
                break;
        }
        String temp = Integer.toBinaryString(value);
        String res = "";
        for(int i=0;i<4-temp.length();i++)
            res += "0";
        res += temp;
        return res;
    }
    public static String feistel_function(String R,String key)
    {
        int[][] e = {{32, 1, 2, 3, 4, 5}, {4, 5, 6, 7, 8, 9},
         {8, 9, 10, 11, 12, 13}, {12, 13, 14, 15, 16, 17},
         {16, 17, 18, 19, 20, 21},{20, 21, 22, 23, 24, 25},
         {24, 25, 26, 27, 28, 29},{28, 29, 30, 31, 32 ,1}};
        String e_result = "";
        for(int i=0;i<8;i++)
          {
              for(int j=0;j<6;j++)
              {
                  e_result+=R.charAt(e[i][j]-1);
              }
          }
        String xor_res = xor(e_result,key),select_res="",result="";
        int start=0;
        for(int i=0;i<8;i++)
        {
            String temp = xor_res.substring(start,start+6);
            int row = Integer.parseInt(temp.charAt(0)+""+temp.charAt(5),2);
            int col = Integer.parseInt(temp.substring(1,5),2);
            String select = selection(row,col,i+1);
            select_res += select;
            start += 6;
        }
        int[][] p = {{16, 7, 20, 21}, {29, 12, 28, 17},
         {1, 15, 23, 26}, {5, 18, 31, 10},
         {2, 8, 24, 14},{32, 27, 3, 9},
         {19, 13, 30, 6},{22, 11, 4, 25}};
        for(int i=0;i<8;i++)
            for(int j=0;j<4;j++)
                result += select_res.charAt(p[i][j]-1);
        return result;
    }
    public static String encryption(String plainTxt,String[] key_arr)
    {
        String cipherTxt = "";
        for(int block=0;block<plainTxt.length()/64;block++)
        {
            int[][] ip={{58, 50, 42, 34, 26, 18, 10, 2}, {60, 52, 44, 36, 28, 20 ,12, 4},
            {62, 54, 46, 38, 30, 22, 14, 6}, { 64, 56, 48, 40, 32, 24, 16, 8},
            {57, 49, 41, 33, 25, 17, 9,1},{ 59, 51, 43, 35, 27, 19, 11, 3},
            {61, 53, 45, 37, 29, 21, 13, 5},{ 63, 55, 47, 39, 31, 23, 15, 7}};
            String sub_plainTxt = plainTxt.substring(block*64, block*64+64);
            String ip_result = "";
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                    ip_result += sub_plainTxt.charAt(ip[i][j]-1);
            String[] L = new String[17];
            String[] R = new String[17];
            L[0] = ip_result.substring(0, ip_result.length()/2);
            R[0] = ip_result.substring(ip_result.length()/2);
            for(int i=1;i<=16;i++)
            {
                L[i] = R[i-1];
                String temp = feistel_function(R[i-1],key_arr[i]);
                R[i] = xor(L[i-1],temp);
            }
            String LR_combined = R[16] + L[16],ip_inv_result = "";
            int[][] ip_inv={{40, 8, 48, 16, 56, 24, 64,32}, {39, 7, 47, 15, 55, 23, 63, 31},
            {38, 6, 46, 14, 54, 22, 62, 30}, {37, 5, 45, 13,53, 21, 61, 29},
            {36, 4, 44, 12, 52, 20, 60, 28},{35, 3, 43, 11, 51, 19, 59, 27},
            {34, 2, 42, 10, 50, 18, 58, 26},{ 33, 1, 41, 9, 49, 17, 57, 25}};
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                    ip_inv_result += LR_combined.charAt(ip_inv[i][j]-1);
            //System.out.println(ip_inv_result);
            cipherTxt += ip_inv_result;
        }
        return cipherTxt;
    }
    public static String decryption(String cipherTxt,String[] key_arr)
    {
        String[] new_key_arr = new String[17];
        new_key_arr[0] = key_arr[0];
        for(int i=1;i<=16;i++)
            new_key_arr[i] = key_arr[17-i];
        return encryption(cipherTxt,new_key_arr);
    }
public static void main(String[] args) {
   
        String plain_txt="";
        String key="";
        String key_Arr[]=new String[17];
        int ch=1;
       
        Scanner s=new Scanner(System.in);
        while(ch!=6)
        {
        System.out.println("Enter 1)keyGeneration\t2)Encryption\t3)Decryption\t4)Double DES\t5)Triple DES\t6)exit");
       
        ch=s.nextInt();
         
        if(ch==1)
        {
            System.out.println("Enter the key:");
            s.nextLine();
            key=s.nextLine();  
            if(key.length()!=8)
            {
                System.out.println("Invalid key..key length is 8");
                System.exit(0);
            }
            key = convert_plaintxt_bin(key);
            key_Arr= keygen(key);

            System.out.println("key Array:");
             for(int i=1;i<=16;i++)
            {
                System.out.println("k"+i+": "+key_Arr[i]);    
                }
        }
 
        if(ch==2)
        {  
            System.out.println("Enter the plaintxt:");
            s.nextLine();
            plain_txt=s.nextLine();
            plain_txt=convert_plaintxt_bin(plain_txt);
            String new_plain_txt = "";
            int len=plain_txt.length();
            while(len%64!=0)
            {
                plain_txt +="0";
                len++;
            }
            new_plain_txt += plain_txt;
            //System.out.println(new_plain_txt);
            String cipher_text=encryption(new_plain_txt,key_Arr);
            cipher_text = convert_bin_hex(cipher_text);
            System.out.println("Cipher Text: "+cipher_text);
        }
       
        if(ch==3)
        {  
            System.out.println("Enter cipher_text(hex):");
            s.nextLine();
            String cipher_txt_hex=s.nextLine();
            String result = decryption(convert_hex_bin(cipher_txt_hex),key_Arr);
            System.out.println(convert_bin_plaintxt(result));
           
        }
       /*
        if(ch==4)
        {
            String key1[]=new String[17];
            String key2[]=new String[17];
            System.out.println("Enter plain_txt:");
            s.nextLine();
            plain_txt=s.nextLine();
            System.out.println("Enter 2 keys:");
            key1=key_gen(hex_to_binstring(stringtohex(s.nextLine())));
            key2=key_gen(hex_to_binstring(stringtohex(s.nextLine())));
           
            System.out.println("Encryption:");
            String enc1=encryption(conv_hex_plaintxt_bin(plain_txt), key1);
            String enc2=encryption(enc1,key2);
            System.out.println("Cipher_Text: "+enc2);
           
            System.out.println("decryption:");
           
            String dec1=decryption(enc2,key2);
            String dec2=decryption(dec1, key1);
            System.out.println("Plaintxt(hex): "+dec2);
            System.out.println("Plaintxt:"+conv_hex__plaintxt_str(dec2));
           
           
        }
       
        if(ch==5)
        {
            String key1[]=new String[17];
            String key2[]=new String[17];
            String key3[]=new String[17];
            System.out.println("Enter plain_txt:");
            s.nextLine();
            plain_txt=s.nextLine();
            System.out.println("Enter 3 keys:");
            key1=key_gen(hex_to_binstring(stringtohex(s.nextLine())));
            key2=key_gen(hex_to_binstring(stringtohex(s.nextLine())));
            key3=key_gen(hex_to_binstring(stringtohex(s.nextLine())));
           
            System.out.println("Encryption:");
            String enc1=encryption(conv_hex_plaintxt_bin(plain_txt), key1);
            String enc2=decryption(enc1, key2);
            String enc3=encryption(enc2, key3);
            System.out.println("Cipher Text: "+enc3);

           
            System.out.println("Decryption");
            String dec1=decryption(enc3, key3);
            String dec2=encryption(dec1, key2);
            String dec3=decryption(dec2, key1);
           
            System.out.println("Plain_text:(hex)"+dec3);
            System.out.println("Plain_Text: "+conv_hex__plaintxt_str(dec3));
     
        }
       */
}

}
 }

