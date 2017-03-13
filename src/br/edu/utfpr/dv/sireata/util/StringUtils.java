package br.edu.utfpr.dv.sireata.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.caelum.stella.inwords.InteiroSemFormato;
import br.com.caelum.stella.inwords.NumericToWordsConverter;

public class StringUtils {

	public static String generateMD5Hash(String s){
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			
			m.update(s.getBytes(),0,s.length());
		    
			return new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			
			return "";
		}
	    
	}
	
	public static String getExtenso(int numero){
		NumericToWordsConverter converter;  
		converter = new NumericToWordsConverter(new InteiroSemFormato());
		return converter.toWords(numero);  
	}
	
	public static String getExtensoOrdinal(int numero, boolean feminino){
		String[] unidades = {"primeir", "segund", "terceir", "quart", "quint", "sext", "s�tim", "oitav", "non"};
		String[] dezenas = {"d�cim", "vig�sim", "trig�sim", "quadrag�sim", "quinquag�sim", "sextag�sim", "septuag�sim", "octag�sim", "nonag�sim"};
		int unidade = (numero % 10), dezena = (numero / 10);
		String retorno = "";
		
		if(numero >= 100){
			return "N�mero muito grande";
		}
		
		if(dezena > 0){
			retorno = dezenas[dezena - 1] + (feminino ? "a " : "o ");
		}
		
		if(unidade > 0){
			retorno += unidades[unidade - 1] + (feminino ? "a " : "o ");
		}
		
		return retorno.trim();
 	}
	
}
