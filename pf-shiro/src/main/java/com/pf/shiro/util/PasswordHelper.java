package com.moqu.manage.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
	private static final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private static final String algorithmName = "md5";
	private static final int hashIterations = 2;

	public static String[] encryptPassword(String pass, String account) {
		String salt = randomNumberGenerator.nextBytes().toHex();
		String newPassword = new SimpleHash(algorithmName, pass, ByteSource.Util.bytes(account + salt), hashIterations).toHex();
		return new String[]{newPassword, salt};
	}
	
	public static void main(String[] args) {
		//String newPassword = new SimpleHash(algorithmName, "e10adc3949ba59abbe56e057f20f883e", ByteSource.Util.bytes("huangguangwen" + "dc49e92b40486e1680ec9640ab93d175"), hashIterations).toHex();
		//System.out.println(newPassword);
		
		String[] str = encryptPassword("6fb42da0e32e07b61c9f0251fe627a9c", "huangguangwen");
//		
		System.out.println(str[0] + "   " + str[1]);
//		System.out.println(encryptPassword("000000", "manager", "7de776d77ec8f3b77f163d619f66d159"));
//		System.out.println(ByteSource.Util.bytes("manager" + "7de776d77ec8f3b77f163d619f66d159"));
	}
	
	public static String encryptPassword(String pass, String account, String salt){
		String newPassword = new SimpleHash(algorithmName, pass, ByteSource.Util.bytes(account + salt), hashIterations).toHex();
		return newPassword;
	}
}
