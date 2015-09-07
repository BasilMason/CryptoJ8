import java.util.Base64;

/**
 * Created by Basil on 07/09/2015.
 */

public class CryptoUtils {

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
}
