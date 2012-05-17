/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.datateh.study.gwtp.client.util;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;

/**
 *
 * @author rshamsutdinov
 */
public class DialogBoxUtil {
	
	public static void error(String errDesc, Throwable caught) {
		final DialogBox dialogBox = new DialogBox(true);
		dialogBox.setHTML("<center>Ошибка</center>");
		final StringBuilder sb = new StringBuilder(caught.toString());
		while ((caught = caught.getCause()) != null) {
			sb.append("<br>caused by ").append(caught);
		}
		dialogBox.add(new HTML("<b>" + errDesc + ":</b><br>" + sb.toString()));
		dialogBox.center();
	}
}
