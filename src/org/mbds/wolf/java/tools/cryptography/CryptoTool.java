package org.mbds.wolf.java.tools.cryptography;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author amles_000
 *
 */

public class CryptoTool {
	
	public byte[] encode(byte[] data, PublicKey publicKey, String algo) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance(algo);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] cryptedData = cipher.doFinal(data);
			return cryptedData;
		} catch (Exception e) {
			throw e;
		}
	}

	public byte[] decode(byte[] cryptedData, PrivateKey privateKey, String algo) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance(algo);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] data = cipher.doFinal(cryptedData);
			return data;
		} catch (Exception e) {
			throw e;
		}
	}

	public static byte[] generateHashKey(String password, String algo) throws Exception {
		try {
			byte[] keyStart = password.getBytes("UTF-8");
			KeyGenerator kgen = KeyGenerator.getInstance(algo);
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
			sr.setSeed(keyStart);
			kgen.init(128, sr);
			SecretKey skey = kgen.generateKey();
			return skey.getEncoded();
		} catch (Exception e) {
			throw e;
		}
	}

	public static byte[] encodeWithHashKey(byte[] key, byte[] data, String algo) throws Exception {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(key, algo);
			Cipher cipher = Cipher.getInstance(algo);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] cryptedData = cipher.doFinal(data);
			return cryptedData;
		} catch (Exception e) {
			throw e;
		}
	}

	public static byte[] decodeWithHashKey(byte[] key, byte[] cryptedData, String algo) throws Exception {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(key, algo);
			Cipher cipher = Cipher.getInstance(algo);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] data = cipher.doFinal(cryptedData);
			return data;
		} catch (Exception e) {
			throw e;
		}
	}
}
