import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * Created by SteveGreen on 06/06/15.
 */
public class PBKDF {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {

        char[] password = new char[0];
        byte[] salt = new byte[0];

        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec ks = new PBEKeySpec(password, salt, 10000, 128);
        SecretKey s = f.generateSecret(ks);
        Key k = new SecretKeySpec(s.getEncoded(), "AES");
    }
}
