import java.util.Base64;
import java.util.HashMap;

/**
 * Created by Basil on 07/09/2015.
 */

public class CryptoUtils {

    /**
     * ETAOIN SHRDLU - most common characters in the English language, in that order
     */
    final public static String[] ETAOIN_SHRDLU = {"e", "t", "a", "o", "i", "n", " ", "s", "h", "r", "d", "l", "u"};
    final public static String[] ETA = {"e", "t", "a", " "};

    /**
     * Popular words in the English language
     */
    final public static String[] KEYWORDS = {"the", "be", "to", "of", "and", "a", "in", "that", "have", "it",
            "for", "not", "on", "with", "he", "as", "you", "do", "at", "this",
            "but", "his", "by", "from", "they", "we", "say", "her", "she", "or",
            "an", "will", "my", "one", "all", "would", "there", "their", "what",
            "so", "up", "out", "if", "about", "who", "get", "which", "go", "me",
            "when", "make", "can", "like", "time", "no", "just", "him", "know",
            "take", "people", "into", "year", "your", "good", "some", "could",
            "them", "see", "other", "than", "then", "now", "look", "only", "come",
            "its", "over", "think", "also", "back", "after", "use", "two", "how",
            "our", "work", "first", "well", "way", "even", "new", "want", "because",
            "any", "these", "give", "day", "most", "us"};

    public static String[] hexAlphabet() {

        String key = "00";
        String[] s = new String[127];

        for (int i = 0; i < 127; i++) {

            s[i] = key;

            // increment key

            int value = Integer.parseInt(key, 16);
            value++;
            key = Integer.toHexString(value);

            if (key.length() == 1)
                key = "0" + key;

        }

        return s;

    }

    /* ************************************************** */
    /* ********  B Y T E W I S E  M E T H O D S  ******** */
    /* ************************************************** */
    /* methods performed directly on bytes ************** */
    /* ************************************************** */

    /**
     * converts hexidecimal byte to java primitive type byte
     * @param hexByte to be converted
     * @return byte value
     */
    public static byte hexByteToByte(String hexByte) {
        return (byte) Integer.parseInt(hexByte, 16);
    }

    /**
     * converts hexidecimal byte to String containing binary
     * byte
     * @param hexByte
     * @return
     */
    public static String hexByteToBinary(String hexByte) {

        int i = Integer.parseInt(hexByte, 16);
        String bin = Integer.toBinaryString(i);

        while(bin.length() < 8) {

            bin = "0" + bin;

        }

        return bin;
    }

    /**
     * Performs the bitwise XOR operation on two hexidecimal bytes
     * @param hexByte1
     * @param hexByte2
     * @return
     */
    public static String xorHexBytes(String hexByte1, String hexByte2) {

        StringBuilder xor = new StringBuilder();

        for (int i = 0; i < hexByte1.length(); i++) {

            int r = Character.getNumericValue(hexByte1.charAt(i)) ^ Character.getNumericValue(hexByte2.charAt(i));

            xor.append(r);

        }

        return xor.toString();
    }

    /* ************************************************** */
    /* **********  S T R I N G  M E T H O D S  ********** */
    /* ************************************************** */
    /* methods performed on strings ********************* */
    /* ************************************************** */


    /**
     *
     * @param base64
     * @return
     */
    public static String base64StringToHex(String base64) {
        StringBuilder sb = new StringBuilder();

        byte[] b = Base64.getDecoder().decode(base64);
        for (int i = 0; i < b.length; i++) {
            String s = "" + Integer.toHexString(b[i]);
            if (s.length() < 2)
                s = "0" + s;
            sb.append(s);
        }

        return sb.toString();
    }


    /**
     *
     * @param hex
     * @return
     */
    public static String hexStringToBinary(String hex) {

        StringBuilder bin = new StringBuilder();
        String byte1;

        for (int i = 0; i < hex.length() / 2; i++) {

            byte1 = hex.substring(2*i, 2*i + 2);

            bin.append(hexByteToBinary(byte1));

        }

        return bin.toString();

    }

    /**
     *
     * @param bin
     * @return
     */
    public static String binaryStringToHex(String bin) {

        StringBuilder hex = new StringBuilder();

        for (int i = 0; i < bin.length() / 8; i++) {

            String s = Integer.toHexString(Integer.parseInt(bin.substring(8*i, 8*i + 8), 2));

            if (s.length() < 2)
                s = "0" + s;

            hex.append(s);

        }

        return hex.toString();

    }

    /**
     *
     * @param hex1
     * @param hex2
     * @return
     */
    public static String xorHexStrings(String hex1, String hex2) {

        // string declarations

        String bin1, bin2, binXor;

        // convert hex strings to binary strings

        bin1 = hexStringToBinary(hex1);
        bin2 = hexStringToBinary(hex2);

        // perform xor operations

        binXor = xorHexBytes(bin1, bin2);

        // convert binary result to hex string

        return binaryStringToHex(binXor);

    }

    /**
     *
     * @param hex
     * @return
     */
    public static String hexStringToBase64(String hex) {
        byte[] data = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length() / 2; i++) {
            data[i] = hexByteToByte(hex.substring(2 * i, 2 * i + 2));
        }

        return new String(Base64.getEncoder().encode(data));
    }



    /* ************************************************** */
    /* ****^^****  O T H E R  M E T H O D S  ************ */
    /* ************************************************** */
    /* ************************************************** */
    /* ************************************************** */


    /**
     *
     * @param s
     * @return
     */
    public static HashMap characterFrequencyMap(String s) {

        HashMap<String,Integer> map = new HashMap<String,Integer>();

        for(int i = 0; i < s.length() / 2; i++){
            String c = s.substring(2 * i, 2 * i + 2);
            Integer val = map.get(c);
            if(val != null){
                map.put(c, new Integer(val + 1));
            }else{
                map.put(c,1);
            }
        }

        return map;

    }


    public static String decipherHexStringWithKey(String cipherText, String key) {

        // declare return string

        StringBuilder plainText = new StringBuilder();

        // loop through bytes of hex string

        for (int j = 0; j < cipherText.length() / 2; j++) {

            String hexByte = cipherText.substring(2 * j, 2 * j + 2);

            plainText.append((char) Integer.parseInt(xorHexStrings(hexByte, key), 16));

        }

        return plainText.toString();

    }

    public static HashMap<String,Integer> getScoredKeys(HashMap<String,Integer> charFreq) {

        HashMap<String,Integer> scoredKeys = new HashMap<String,Integer>();

        for (String c : charFreq.keySet()) {

            // if the character appears more than once

            if (charFreq.get(c) > 1) {

                // check the character against all possible keys

                for (String k : CryptoUtils.hexAlphabet()) {

                    for (String s : CryptoUtils.ETAOIN_SHRDLU) {
                    //for (String s : CryptoUtils.ETA) {

                        // if the xor results in a common character

                        if (CryptoUtils.decipherHexStringWithKey(c, k).equals(s)) {

                            // add key to map

                            Integer val = scoredKeys.get(k);
                            if(val != null){
                                scoredKeys.put(k, new Integer(val + 1));
                            }else{
                                scoredKeys.put(k,1);
                            }

                        }

                    }

                }

            }

        }

        return scoredKeys;
    }

    public static boolean containsKeyword(String s) {

        boolean contains = false;

        for (String word : KEYWORDS) {

            if (s.contains(word))
                contains = true;

        }

        return contains;

    }

}
