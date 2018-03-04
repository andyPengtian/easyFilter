package com.easyfilter.navigator.provider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.easyfilter.PluginImage;

public class ItecLabelProvider extends LabelProvider{

//	@Override
//	public Image getImage(Object element) {
//		if(element instanceof IProject){
//			return PluginImage.PROJECTS;
//		}
//		if(element instanceof IFolder){
//			return PluginImage.NEW_FOLDER;
//		}
//		return null;
//	}

	@Override
	public String getText(Object element) {
		if(element instanceof IProject){
			IProject project = (IProject) element;
			return project.getName();
		}
		if(element instanceof IFolder){
			IFolder folder = (IFolder) element;
			return folder.getName();
		}
		if(element instanceof IFile){
			IFile file = (IFile) element;
			return file.getName();
		}
		return "";
	}

}
