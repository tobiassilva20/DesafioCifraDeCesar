package challange;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class implements the method to decode message and to generate a hash SHA-1
 * @see java.security.MessageDigest
 * @see java.lang.String
 */
public class CaesarCipher {
	
	//Character map
	public final char [] cara = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' ','?','.',','};
	int x = 0;
	String decoded = "";
	
	/**
	 * The method {@code decode} is responsable for decode the message and return a string
	 * @param cipher
	 * @param shift
	 * @return message decoded
	 */
	public String decode(String cipher, int shift) {
		
		
		for(int i=0; i < cipher.length(); i++) {
			
			//search for the character on the map and get its index
			while(cipher.charAt(i) != cara[x]) {
				x++;
			};	
				//Case of negative index
				if(x < shift) {
					x = x + 26;
					decoded = decoded + cara[x-shift];
				}else {
					//Case the character isn't a letter
					if(x >= 26) {
						decoded = decoded + cara[x];
					}else {
						decoded = decoded + cara[x - shift];
					}
				}
			x = 0;	
		}
		return decoded;
	}
	
	/**
	 * The method {@code crateHashCode} generates a HASH type SHA-1 for a given message
	 * @param message
	 * @return hash generated
	 */
	public String createHashSha1(String message){
		String hash = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(message.getBytes("utf8"));
			hash = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hash;
	}
}
