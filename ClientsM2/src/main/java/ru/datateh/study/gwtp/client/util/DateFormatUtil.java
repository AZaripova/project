/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.datateh.study.gwtp.client.util;

import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;

/**
 *
 * @author rshamsutdinov
 */
public class DateFormatUtil {
	
	private static final String PATTERN = "dd-MM-yyyy";

	public static String format(Date date) {
		return DateTimeFormat.getFormat(PATTERN).format(date);
	}

	public static Date parse(String date) {
		return DateTimeFormat.getFormat(PATTERN).parse(date);
	}
}
