package br.com.cadastroprodutocliente.util;

import java.math.BigDecimal;

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
	
	public static boolean bigDecimalZeroOrNull(BigDecimal bigDecimal) {
		if (bigDecimal == null) {
			return true;
		}
		if (bigDecimal.equals(BigDecimal.ZERO)) {
			return true;
		}
		return false;
	}
}