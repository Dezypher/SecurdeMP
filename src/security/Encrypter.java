package security;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class Encrypter {
	public static String getEncrypted(String plaintext){
        String ALGORITHM = "SHA-256";
        byte[] encrypted;

        try {
        	/*
            KeyPair kp  = KeyPairGenerator.getInstance(ALGORITHM).genKeyPair();

            //encryption
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, kp.getPublic());
            encrypted = c.doFinal(plaintext.getBytes());
            */

            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(plaintext.getBytes());
            
            encrypted = md.digest();
            
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } /*catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } */
        
        return null;
    }
	
	public static String generateSalt() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		return output;
	}
}
