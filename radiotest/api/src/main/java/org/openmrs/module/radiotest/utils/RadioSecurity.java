package org.openmrs.module.radiotest.utils;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import org.apache.xerces.impl.dv.util.Base64;

public class RadioSecurity {
    
   private static final String ALGO = "AES";
   private static final byte[] keyValue = 
       new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't',
'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

   public static String encrypt(String Data) throws Exception {
       Key key = generateKey();
       Cipher c = Cipher.getInstance(ALGO);
       c.init(Cipher.ENCRYPT_MODE, key);
       byte[] encVal = c.doFinal(Data.getBytes());
       new Base64();
       String encryptedValue = Base64.encode(encVal);
       return encryptedValue;
   }

   public static String decrypt(String encryptedData) throws Exception {
       Key key = generateKey();
       Cipher c = Cipher.getInstance(ALGO);
       c.init(Cipher.DECRYPT_MODE, key);
       new Base64();
       byte[] decordedValue = Base64.decode(encryptedData);
       byte[] decValue = c.doFinal(decordedValue);
       String decryptedValue = new String(decValue);
       return decryptedValue;
   }
   
   private static Key generateKey() throws Exception {
       Key key = new SecretKeySpec(keyValue, ALGO);
       return key;
}

}