package br.net.jassa.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	
	/* quando um parametro string vem com espaços ele codifica os espaços em branço. P. ex.
	 * TV LED --> TV%LED
	 */
	public static String decodeParame(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static List<Integer> decodeIntList(String s) {
		/*String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (String numero : vet) {
			list.add(Integer.parseInt(numero));
		}
		return list;*/
		return Arrays.asList(s.split(","))
				     .stream()
				     .map(x -> Integer.parseInt(x))
				     .collect(Collectors.toList());
	}
}
