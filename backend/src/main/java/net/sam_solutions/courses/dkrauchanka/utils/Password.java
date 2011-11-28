package net.sam_solutions.courses.dkrauchanka.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
    public static final String SALT = "A7C678D1E45A";
    
    public static String hashPassword(String pass){
        char[] chars = pass.toCharArray();
        byte[] bytes = new byte[chars.length];
        for(int i = 0; i < chars.length; i ++){
            bytes[i] = (byte)chars[i];
        }
        try{
            MessageDigest alg = MessageDigest.getInstance("SHA-1");
            alg.update(bytes);
            byte[] hash = alg.digest();
            String result = "";
            for(int i = 0; i < hash.length; i ++){
                result += String.format("%02X",hash[i]);
            }
            return result;
        }
        catch(NoSuchAlgorithmException e){
            return null;
        }
    }
}
