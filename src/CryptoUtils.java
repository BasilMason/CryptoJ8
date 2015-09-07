import java.util.Base64;

/**
 * Created by Basil on 07/09/2015.
 */

public class CryptoUtils {

    /**
     * converts hexidecimal byte to java primitive type byte
     * @param hexByte to be converted
     * @return byte value
     */
    public static byte hexByteToByte(String hexByte) {
        return (byte) Integer.parseInt(hexByte, 16);
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
    public static String hexStringToBase64(String hex) {
        byte[] data = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length() / 2; i++) {
            data[i] = hexByteToByte(hex.substring(2*i, 2*i + 2));
        }
        return Base64.getEncoder().encode(data).toString();
    }

}
