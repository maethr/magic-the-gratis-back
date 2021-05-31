package com.alolorsus.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringHash {

	public static void main(String[] args) throws IOException {

		String contrasena = "uno";
		System.out.println(getHashString(contrasena));

	}

	public static String getHashString(String contrasena) {

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(contrasena.getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
			for (int i = 0; i < encodedhash.length; i++) {
				String hex = Integer.toHexString(0xff & encodedhash[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			contrasena = hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return contrasena;
	}

}