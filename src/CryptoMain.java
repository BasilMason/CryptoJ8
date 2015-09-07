import java.util.HashMap;

/**
 * Created by Basil on 07/09/2015.
 */
public class CryptoMain {

    public static void main(String[] args) {new CryptoMain().launch();}

    private void launch() {

        //set1Challenge1();
        //set1Challenge2();
        set1Challenge3();

    }

    private void set1Challenge1() {

        /**
         * Challenge 1
         * Convert hex string into base64 string
         */

        String hex = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
        String base64 = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t";

        System.out.println("Hex string:\t\t\t" + hex);
        System.out.println("Base64 string:\t\t" + base64);
        System.out.println("Hex converted:\t\t" + CryptoUtils.hexStringToBase64(hex));
        System.out.println("Base64 converted:\t" + CryptoUtils.base64StringToHex(base64));

    }

    private void set1Challenge2() {

        /**
         * Challenge 2
         * Fixed XOR
         */

        String hex1 = "1c0111001f010100061a024b53535009181c";
        String hex2 = "686974207468652062756c6c277320657965";
        String xor = "746865206b696420646f6e277420706c6179";

        System.out.println("First string:\t\t" + hex1);
        System.out.println("Second string:\t\t" + hex2);
        System.out.println("XOR result:\t\t\t" + CryptoUtils.xorHexStrings(hex1, hex2));
        System.out.println("Expected result:\t" + xor);

    }

    private void set1Challenge3 () {

        /**
         * Challenge 3
         * Single-byte XOR cipher
         * Key: 0x58
         * Plaintext: Cooking MC's like a pound of bacon
         *
         * Three methods of attack:
         *      1. Brute force, try all possible single-keys
         *      2. Guess individual keys
         *      3. Use character frequency analysis to narrow choices of keys
         */

        String cipherText = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
        int counter = 0;

        /* 1. B R U T E   F O R C E */

        System.out.println("Cracking ciphertext with Brute Force.\n");

        for (String k : CryptoUtils.hexAlphabet()) {

            counter++;
            System.out.println("Key: " + k + " gives: \t" + CryptoUtils.decipherHexStringWithKey(cipherText, k) + " END");

        }

        System.out.println("\nBrute force requires " + counter + " guesses.\n");

        /* 2. G U E S S   K E Y */

        System.out.println("Cracking ciphertext with key 0x58.");

        String key = "58";

        System.out.println(CryptoUtils.decipherHexStringWithKey(cipherText, key));
        System.out.println();

        /* 3. C H A R A C T E R   A N A L Y S I S */

        System.out.println("Breaking ciphertext with character frequency analysis.\n");

        counter = 0;

        HashMap<String,Integer> keys = CryptoUtils.getScoredKeys(CryptoUtils.characterFrequencyMap(cipherText));

        for (String k : keys.keySet()) {

            String plain = CryptoUtils.decipherHexStringWithKey(cipherText, k);

            if (CryptoUtils.containsKeyword(plain)) {
                counter++;
                System.out.println("Key: " + k + " gives: \t" + plain + " END");
            }

        }

        System.out.println("\nCharacter frequency requires " + counter + " guesses.\n");

    }
}
