import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by SteveGreen on 06/06/15.
 */
public class RSA_Test {

    private RSA_Asymmetric RSA;

    @Before
    public void setup(){
        RSA = new RSA_Asymmetric();
    }

    @Test
    public void generateAnRSAKeyPair() throws Exception{
        KeyPair keyPair = RSA.generateRSAKey();

        assertEquals("RSA", keyPair.getPublic().getAlgorithm());
        assertTrue(keyPair.getPublic().getEncoded().length > 2048 / 8);
        assertTrue(keyPair.getPrivate().getEncoded().length > 2048 / 8);
    }

    @Test
    public void encryptASymetricKey() throws Exception{
        KeyPair keyPair = RSA.generateRSAKey();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey key = keyGenerator.generateKey();

        byte[] encryptedKey = RSA.encryptWithRsa(publicKey, key);
        byte[] decryptedKey = RSA.decryptWithRsa(privateKey, encryptedKey);

        assertArrayEquals(key.getEncoded(), decryptedKey);
    }

    @Test
    public void signAMessage() throws Exception{
        KeyPair keyPair = RSA.generateRSAKey();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String message = "A message";
        byte[] messagebytes = message.getBytes();

        byte[] signatureBytes = RSA.signMessage(privateKey, messagebytes);
        boolean verified = RSA.verifySignature(publicKey, messagebytes, signatureBytes);

        assertTrue(verified);
    }



}
