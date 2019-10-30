
package rsa;

import java.util.Random;
import java.util.Scanner;
import java.math.BigInteger;

public class Rsa {

    public static void main(String[] args) {
        // TODO code application logic here
        String str;
        Scanner input=new Scanner(System.in);
        System.out.println("Enter Text:\t");
        str=input.nextLine();
        String str2="1";
        for(int i=0; i<str.length(); i++){
            int num=((int)str.charAt(i))-64;
            if(num<10){
                str2+="0";
            }
            str2+=num;
        }
        System.out.println("Message="+str2);
            BigInteger p;//15487039//15487049
            BigInteger q;
            Random rand = new Random();
            p=BigInteger.probablePrime(2048, rand);
        q=p.nextProbablePrime();
        BigInteger n = p.multiply(q);
        System.out.println("p="+p);
        System.out.println("q="+q);
        System.out.println("n="+n);
       
       
       
        BigInteger phi = new BigInteger("1");
        phi = (p.subtract(phi)).multiply(q.subtract(phi));
        System.out.println("phi="+phi);
       
       
        BigInteger e =BigInteger.probablePrime(1024, rand);
       
       
        while(phi.gcd(e).compareTo(BigInteger.ONE)>0 && e.compareTo(phi)<0){
            e.add(BigInteger.ONE);
        }
       
       
        System.out.println("e="+e);
        BigInteger d=e.modInverse(phi);
        System.out.println("d="+d);
       
        BigInteger m=new BigInteger(str2);
        BigInteger et,dt;
        et=m.modPow(e, n);
       
        System.out.println("et="+et);
        dt=et.modPow(d, n);
        System.out.println("dt="+dt);
        String str3=dt.toString();
        for(int i=1; i<str3.length(); i+=2){
            char c=(char) (Integer.parseInt(str3.substring(i, i+2))+64);
            System.out.print(c);
        }
     System.out.print("\n");   
    }
   

    
}
