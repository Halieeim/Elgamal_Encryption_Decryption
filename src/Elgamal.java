import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Elgamal {
    long p,a,XA;
    Random rand = new Random();
    public long selectP(){
        do {
            p = rand.nextLong(129, (long) (Math.pow(2,64)-1));
        } while (!isPrime(p));
    }
    public long selecta(){
        do {
            a = rand.nextLong(2, p);
        } while (!isPrime(a));
    }
    public long square_Multiply(long a, long b, long n){
        long result = 1;
        while (b != 0){
            if ((b & 1) == 1){
                result = (result * a) % n;
            }
            a = (a * a) % n;
            b = b >> 1;
        }
        return result;
    }
    public boolean fermatTest(long number){
        return (square_Multiply(2,number-1,number) == 1);
    }

    public boolean isPrime(long number){
        return fermatTest(number);
    }
    public long GCD(long a, long b){
        long temp = 0;
        while (b != 0){
            temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public long EEA(long a, long b){
        long inverse, temp = a;
        for (int i = 2;;i++){
            if ((temp % b) != 1){
                temp = a * i;
            } else {
                inverse = (i-1) % b;
                break;
            }
        }
        return inverse;
    }

    public ArrayList<long[]> keyGeneration() {
        long[] res1 = new long[2];
        long[] res2 = new long[2];
        ArrayList<long[]> Result = new ArrayList<>();
        long n, r, e, gcd, d;
        Scanner input = new Scanner(System.in);

        return Result;
    }

    public long[] encrypt(String text, long[] publicKey){
        long key, n;
        long[] cipher = new long[text.length()];

        return cipher;
    }

    public long[] decrypt(long[] cipher, long[] privateKey){
        long key, n, xp, xq, dp, dq, cp, cq;
        long[] plainText = new long[cipher.length];

        return plainText;
    }
    public static void main(String[] args) {
        /* **************************************************************Input_Handling************************************************************** */
        System.out.println("Enter Plaintext to encrypt:");
        Scanner input = new Scanner(System.in);
        String plainText = input.nextLine();
        long[] t = new long[plainText.length()];

        /* **************************************************************Key_Generation************************************************************** */

        /* ****************************************************************Encryption**************************************************************** */

        /* ****************************************************************Decryption**************************************************************** */

    }
}