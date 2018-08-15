package br.com.cadastroprodutocliente.util;

public final class SiteUtil {

	public static boolean emptyOrNull(Object object) {
		if (object == null) {
			return true;
		}
		if (object.toString().trim().isEmpty()) {
			return true;
		}
		return false;
	}
	
}
