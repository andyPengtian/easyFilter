package com.easyfilter;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editor = layout.getEditorArea();
		
		layout.addView("com.easyfilter.view.ItecNavigatorView", IPageLayout.LEFT, 0.25f, editor);
	}
}
