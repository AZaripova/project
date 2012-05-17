/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.datateh.study.gwtp.client.ui;

import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.Tab;
import com.gwtplatform.mvp.client.TabData;

/**
 *
 * @author MrDarK_AngeL
 */
public class TabImpl implements Tab {

	private final float priority;
	private String historyToken;
	
	public TabImpl(TabData tabData) {
		this.priority = tabData.getPriority();
	}

	@Override
	public float getPriority() {
		return priority;
	}

	@Override
	public void setTargetHistoryToken(String historyToken) {
		this.historyToken = historyToken;
	}

	public String getHistoryToken() {
		return historyToken;
	}

	@Override
	public void activate() {
		throw new UnsupportedOperationException("Use TabBar for tabs organization");
	}

	@Override
	public Widget asWidget() {
		throw new UnsupportedOperationException("Use TabBar for tabs organization");
	}

	@Override
	public void deactivate() {
		throw new UnsupportedOperationException("Use TabBar for tabs organization");
	}

	@Override
	public String getText() {
		throw new UnsupportedOperationException("Use TabBar for tabs organization");
	}

	@Override
	public void setText(String text) {
		throw new UnsupportedOperationException("Use TabBar for tabs organization");
	}
	
}
