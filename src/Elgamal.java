import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Elgamal {
    long p,a,XA,k;
    Random rand = new Random();
    public void selectP(){              // selecting random value for p that should be prime and greater than the message that will be encrypted
        do {
            p = rand.nextLong(129, 100000);
        } while (!isPrime(p));
    }
    public void selecta(){              // selecting random alpha < p
        a = rand.nextLong(2, p);
    }

    public void selectXA(){             // selecting random XA < p-1
        XA = rand.nextLong(2,p-1);
    }

    public void selectk(){              // selecting random k < p
        k = rand.nextLong(2,p);
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
        ArrayList<long[]> Result = new ArrayList<>();
        long YA;
        selectP();
        selecta();
        selectXA();
        YA = square_Multiply(a,XA,p);
        System.out.println("Public Key {p,a,YA} = {" + p + "," + a + "," + YA + "}");
        System.out.println("Private Key {XA} = {" + XA + "}");
        long[] pub = {p,a,YA};
        long[] pri = {XA};
        Result.add(pub);
        Result.add(pri);
        return Result;
    }

    public ArrayList<long[]> encrypt(String text, long[] publicKey){
        long K,c1,c2;
        ArrayList<long[]> cipher = new ArrayList<>();
        selectk();
        K = square_Multiply(publicKey[2],k,p);
        c1 = square_Multiply(a,k,p);
        for (int i = 0; i < text.length(); i++){
            c2 = square_Multiply(K * text.charAt(i),1,p);       // c2 = KM mod p
            cipher.add(new long[]{c1, c2});
        }
        return cipher;
    }
    public long[] decrypt(ArrayList<long[]> cipher, long[] privateKey){
        long[] plainText = new long[cipher.size()];
        long Kinv = square_Multiply(cipher.get(0)[0],privateKey[0],p);                                 // K = c1^XA mod p
        Kinv = EEA(Kinv,p);
        for (int i = 0; i < cipher.size(); i++){
            plainText[i] = square_Multiply(cipher.get(i)[1] * Kinv,1,p);              // m = c2 * K^-1 mod p
        }
        return plainText;
    }
    public static void main(String[] args) {
        /* **************************************************************Input_Handling************************************************************** */
        System.out.println("Enter Plaintext to encrypt:");
        Scanner input = new Scanner(System.in);
        String plainText = input.nextLine();
        /* **************************************************************Key_Generation************************************************************** */
        Elgamal elgamal = new Elgamal();
        ArrayList<long[]> keys = elgamal.keyGeneration();
        long[] publicKey = keys.get(0);
        long[] privateKey = keys.get(1);
        /* ****************************************************************Encryption**************************************************************** */
        ArrayList<long[]> encrypted = elgamal.encrypt(plainText,publicKey);
        System.out.println("Encryption:\n" + Arrays.deepToString(encrypted.toArray()));
        /* ****************************************************************Decryption**************************************************************** */
        long[] decrypted = elgamal.decrypt(encrypted,privateKey);
        char letter;
        String text = "";
        for (long l : decrypted) {
            letter = (char) l;
            text = text.concat(String.valueOf(letter));
        }
        System.out.println("Decryption:\n" + text);
    }
}