package br.com.rnascimento.api.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {
	
	/**
	 * Faz a criptografia do texto passado por parametro.
	 * 
	 * @param text
	 * @return
	 */
	public static String getSecureHash(String text) {
		String hash = DigestUtils.sha256Hex(text);
		return hash;
	}

}
