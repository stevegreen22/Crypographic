import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.security.*;

/**
 * Created by SteveGreen on 06/06/15.
 */
public class RSA_Asymmetric {

    public  KeyPair generateRSAKey() throws NoSuchAlgorithmException{

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public  byte[] encryptWithRsa(PublicKey publicKey, SecretKey secretKey)
        throws Exception{

        Cipher rsa = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256ANDMGF1Padding");
        rsa.init(Cipher.ENCRYPT_MODE, publicKey);
        return rsa.doFinal(secretKey.getEncoded());
    }

    public byte[] decryptWithRsa(PrivateKey privateKey, byte[] encryptedKey)
        throws Exception{

        Cipher rsa = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256ANDMGF1Padding");
        rsa.init(Cipher.DECRYPT_MODE, privateKey);
        return rsa.doFinal(encryptedKey);
    }

    public byte[] signMessage(PrivateKey privateKey, byte[] messagebytes) throws Exception{

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(messagebytes);
        return signature.sign();
    }

    public boolean verifySignature(PublicKey publicKey, byte[] messagebytes, byte[] signatureBytes)
        throws Exception{

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(messagebytes);


        return signature.verify(signatureBytes);
    }
}
