/**
 * Created by Basil on 07/09/2015.
 */
public class CryptoMain {

    public static void main(String[] args) {new CryptoMain().launch();}

    private void launch() {

        set1Challenge1();

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

}
