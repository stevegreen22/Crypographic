import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import java.security.SecureRandom;

import static org.junit.Assert.assertEquals;

/**
 * Created by SteveGreen on 06/06/15.
 */
public class AES_Test {


    @Test
    public void generateARandomKey() throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey key = keyGenerator.generateKey();

        assertEquals("AES", key.getAlgorithm());
        assertEquals(32, key.getEncoded().length);
    }

    @Test
    public void encryptAMessageWithAes() throws Exception{
        String message = "I know the secret!";

        //instance
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();

        //IV
        SecureRandom random = new SecureRandom();
        byte[] buffer = new byte[16];
        random.nextBytes(buffer);
        IvParameterSpec iv = new IvParameterSpec(buffer);

        byte[] cipherText = AES_Symmetric.encryptAMessageWithAes(message, key, iv);
        String actualMessage = AES_Symmetric.decryptAMessageWithAes(cipherText, key, iv);

        assertEquals(message, actualMessage);
    }
}
