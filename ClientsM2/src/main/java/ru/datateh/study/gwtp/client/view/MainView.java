package ru.datateh.study.gwtp.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Tab;
import com.gwtplatform.mvp.client.TabData;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import java.util.ArrayList;
import java.util.List;
import ru.datateh.study.gwtp.client.presenter.MainViewPresenter;
import ru.datateh.study.gwtp.client.presenter.MainViewPresenter.MyView;
import ru.datateh.study.gwtp.client.ui.TabImpl;

public class MainView extends ViewImpl implements MyView {

	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
	
	@UiField TabBar tabBar;
	@UiField DeckPanel tabContainer;
	private final PlaceManager placeManager;
	
	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}
	
	private final Widget widget;
	private final List<TabImpl> tabs = new ArrayList<TabImpl>();

	@Inject
	public MainView(PlaceManager placeManager) {
		widget = uiBinder.createAndBindUi(this);
		this.placeManager = placeManager;
	}
	
	@UiHandler("tabBar")
	void onTabBarBeforeSelection(BeforeSelectionEvent<Integer> event) {
		placeManager.revealPlace(new PlaceRequest(tabs.get(event.getItem()).getHistoryToken()), true);
		event.cancel();
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == MainViewPresenter.TYPE_SetTabContent) {
			final int widgetCount = tabContainer.getWidgetCount();
			if (content != null) {
				int idx = tabContainer.getWidgetIndex(content);
				if (idx < 0) {
					idx = widgetCount;
					tabContainer.add(content);
				}
				tabContainer.showWidget(idx);
			}
			if (widgetCount > 0) {
				tabContainer.remove(0);
			}
		} else {
			super.setInSlot(slot, content);
		}
	}
	
	@Override
    public Tab addTab(TabData tabData, String historyToken) {
        final int size = tabs.size();
		final float priority = tabData.getPriority();
		int beforeIndex;
		for (beforeIndex = 0; beforeIndex < size; beforeIndex++) {
			if (priority < tabs.get(beforeIndex).getPriority()) {
				break;
			}
		}
		final TabImpl tab = new TabImpl(tabData);
		tab.setTargetHistoryToken(historyToken);
		tabs.add(beforeIndex, tab);
		tabBar.insertTab(tabData.getLabel(), beforeIndex);
		return tab;
    }

    @Override
    public void removeTab(Tab tab) {
		final int idx = getTabIndex(tab);
		tabs.remove(idx);
		tabBar.removeTab(idx);
    }

    @Override
    public void removeTabs() {
		while (tabBar.getTabCount() > 0) {
			tabBar.removeTab(0);
		}
		tabs.clear();
    }

    @Override
    public void setActiveTab(Tab tab) {
        final int idx = getTabIndex(tab);
		tabBar.selectTab(idx, false);
    }

    @Override
    public void changeTab(Tab tab, TabData tabData, String historyToken) {
		tab.setText(tabData.getLabel());
		tab.setTargetHistoryToken(historyToken);
		tabBar.setTabText(getTabIndex(tab), tabData.getLabel());
    }

	private int getTabIndex(Tab tab) {
		return tabs.indexOf(tab);
	}
}
