package security;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encrypter {
	public static String getEncrypted(String plaintext){
        String ALGORITHM = "RSA";
        byte[] encrypted;

        try {
            KeyPair kp  = KeyPairGenerator.getInstance(ALGORITHM).genKeyPair();

            //encryption
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, kp.getPublic());
            encrypted = c.doFinal(plaintext.getBytes());
            
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        
        return "nan";
        
    }
}
