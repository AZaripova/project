/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.datateh.study.gwtp.client.ui;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.datepicker.client.DatePicker;
import java.util.Date;
import ru.datateh.study.gwtp.client.util.DateFormatUtil;

/**
 *
 * @author rshamsutdinov
 */
public class BirthdateEditor extends Composite implements LeafValueEditor<String> {
	
	private final DatePicker datePicker;
	
	public BirthdateEditor() {
		datePicker = new DatePicker();
		initWidget(datePicker);
	}

	@Override
	public void setValue(String value) {
		datePicker.setValue((value == null) ? new Date() : DateFormatUtil.parse(value));
	}

	@Override
	public String getValue() {
		return DateFormatUtil.format(datePicker.getValue());
	}

	
}
