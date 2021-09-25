package DBWork;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PasswordEncoder {

    final String PEPPER = "3hju.65()Ukwkjd";

    public String encodePassword(String password, String salt){
        return gettingPasswordHash(password, salt);
    }

    public String getSalt(){
        return generateString(new Random(), 10);
    }

    private String gettingPasswordHash(String password, String salt){
        byte[] passHash = new byte[0];
        try {
            MessageDigest md2 = MessageDigest.getInstance("MD2");
            passHash = md2.digest((PEPPER + password + salt).getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String str = new String(passHash);
//        String str = bytesToStringUTFCustom(passHash);
        return str;
    }

    public static String generateString(Random rng, int length)
    {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()|?";
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

//    public static String bytesToStringUTFCustom(byte[] bytes) {
//
//        char[] buffer = new char[bytes.length >> 1];
//
//        for(int i = 0; i < buffer.length; i++) {
//
//            int bpos = i << 1;
//
//            char c = (char)(((bytes[bpos]&0x00FF)<<8) + (bytes[bpos+1]&0x00FF));
//
//            buffer[i] = c;
//
//        }
//
//        return new String(buffer);
//    }
}
