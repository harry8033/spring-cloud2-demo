package com.pf.core.util;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@SuppressWarnings("restriction")
public class AesUtil {

	private static Cipher cipher;
    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM_ECB = "AES/ECB/PKCS5Padding";

    private static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";
    /*  
     * AES/CBC/NoPadding 要求 
     * 密钥必须是16位的；Initialization vector (IV) 必须是16位 
     * 待加密内容的长度必须是16的倍数，如果不是16的倍数，就会出如下异常： 
     * javax.crypto.IllegalBlockSizeException: Input length not multiple of 16 bytes 
     *  
     *  由于固定了位数，所以对于被加密数据有中文的, 加、解密不完整 
     *   
     *  可 以看到，在原始数据长度为16的整数n倍时，假如原始数据长度等于16*n，则使用NoPadding时加密后数据长度等于16*n， 
     *  其它情况下加密数据长 度等于16*(n+1)。在不足16的整数倍的情况下，假如原始数据长度等于16*n+m[其中m小于16]， 
     *  除了NoPadding填充之外的任何方 式，加密数据长度都等于16*(n+1). 
     */  
    private static final String CIPHER_ALGORITHM_CBC_NOPADDING = "AES/CBC/NoPadding";

    private static SecretKey secretKey;

    private static final String IV = "1234567812345678";
          
	public static String encrypt(String data, String key) throws Exception {
        return encrypt(data, key, IV);
    }
    
    public static String decrypt(String data, String key) throws Exception {
        try
        {
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);
             
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC_NOPADDING);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes());
             
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
  
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String encrypt(String data, String key, String iv) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC_NOPADDING);
            int blockSize = cipher.getBlockSize();
 
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
 
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
             
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
 
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
 
            return new sun.misc.BASE64Encoder().encode(encrypted);
 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String decrypt(String data, String key, String iv) throws Exception {
        try
        {
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);
             
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC_NOPADDING);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
             
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
  
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return trimNullChar(originalString);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 去掉解密后字符串中包含的空字符
     */
    private static String trimNullChar(String str){
    	StringBuffer sb = new StringBuffer(str);
		StringBuffer rsb = new StringBuffer();
		for(int i = 0;i < sb.length(); i++){
			if(sb.charAt(i) != '\0'){
				rsb.append(sb.charAt(i));
			}
		}
		return rsb.toString();
    }
	
}
