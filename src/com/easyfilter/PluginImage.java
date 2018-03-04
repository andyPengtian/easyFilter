package com.easyfilter;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class PluginImage {
	
	public static final Image PROJECTS; 
	public static final Image NEW_FOLDER; 
	
	static{
		String iconPath = "icons/";
		PROJECTS = createImageDescriptor(iconPath + "projects.gif").createImage();
		NEW_FOLDER = createImageDescriptor(iconPath + "new_folder.gif").createImage();
	}
	
	/**
	 * 创建图片
	 * @param path 图片路径
	 * @return 图片
	 */
	private static ImageDescriptor createImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(
				Activator.PLUGIN_ID, path);
	}
	
}
