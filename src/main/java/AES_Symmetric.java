/**
 * Created by SteveGreen on 06/06/15.
 */
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;


public class AES_Symmetric {

    public byte[] encryptAMessageWithAes(String message, SecretKey key, IvParameterSpec iv)
            throws Exception{
        ByteArrayOutputStream out = new  ByteArrayOutputStream();
        Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aes.init(Cipher.ENCRYPT_MODE, key, iv);
        CipherOutputStream cipherOut = new CipherOutputStream(out, aes);
        OutputStreamWriter writer = new OutputStreamWriter(cipherOut);

        try{
            writer.write(message);
        }finally{
            writer.close();
        }

        return out.toByteArray();
    }

    public String decryptAMessageWithAes(byte[] cipherText, SecretKey jey, IvParameterSpec iv)
        throws Exception{

        ByteArrayInputStream in = new ByteArrayInputStream(cipherText);
        Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aes.init(Cipher.DECRYPT_MODE, jey, iv);
        CipherInputStream cipherInputStream = new CipherInputStream(in, aes);
        InputStreamReader reader = new InputStreamReader(cipherInputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        try{
            return bufferedReader.readLine();
        }finally{
            bufferedReader.close();
        }
    }

}
