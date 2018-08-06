package com.saq.loginapp.util;

import org.apache.commons.codec.binary.Base64;

public class SaqUtils {

	public static String decryptAES(String strPassword, String strPassKey) {
		String decryptedPassword = new String(Base64.decodeBase64(strPassword.getBytes()));
		String[] arrTemp = decryptedPassword.split("::");
		AesUtil aesUtil = new AesUtil(128, 1000);
		if (decryptedPassword != null && arrTemp.length == 3) {
			String salt = arrTemp[1];
			String iv = arrTemp[0];
			String cipherText = arrTemp[2];
			strPassword = aesUtil.decrypt(salt, iv, strPassKey, cipherText);
		}
		return strPassword;
	}
}