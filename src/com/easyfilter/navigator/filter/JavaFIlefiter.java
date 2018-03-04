package com.easyfilter.navigator.filter;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.internal.resources.WorkspaceRoot;
import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceFilterDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.easyfilter.navigator.util.Log;

public class JavaFIlefiter extends ViewerFilter {
	private WorkspaceRoot root;
	private IProject[] projects;
	private Map<IProject, Path> hidePathMap = new HashMap<IProject, Path>();

	public JavaFIlefiter() {
	}

	@Override
	public Object[] filter(Viewer viewer, Object parent, Object[] elements) {
		// TODO Auto-generated method stub
		// Log.printMessage("filter");
		if (parent instanceof WorkspaceRoot) {
			this.root = (WorkspaceRoot) parent;
			this.projects = root.getProjects();
		}
		IClasspathEntry[] jreLibrary = PreferenceConstants.getDefaultJRELibrary();
		for (Object el : elements) {
			if (el instanceof IProject) {
				IProject project = (IProject) el;
				try {
					IBuildConfiguration[] projects = project.getBuildConfigs();
					for (IBuildConfiguration conf : projects) {
						Log.printMessage(conf.getName());
					}
					IResourceFilterDescription[] filters = project.getFilters();
					for (IResourceFilterDescription filter : filters) {
						Log.printMessage(filter.getResource().getName());
					}
					if (project.hasNature("org.eclipse.jdt.core.javanature")) {
						IJavaProject javaProject = JavaCore.create(project);
						IPackageFragmentRoot[] packageRoots =javaProject.getAllPackageFragmentRoots();
						for(IPackageFragmentRoot packageRoot:packageRoots){
							Log.printMessage("out:" + packageRoot.getElementName());
						}
						IClasspathEntry[] classpathEntiy = javaProject.getRawClasspath();
						for (IClasspathEntry classpath : classpathEntiy) {
							if (classpath.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
								IPath outPath = classpath.getOutputLocation();
								if (outPath == null) {
									outPath = javaProject.getOutputLocation();
								}
								Log.printMessage("out:" + outPath.toOSString());
								Log.printMessage("path:" + classpath.getPath().toOSString());
							}
						}
					}
					for (IBuildConfiguration conf : projects) {
						Log.printMessage(conf.getName());
					}
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.printMessage(project);
			}
		}
		return super.filter(viewer, parent, elements);
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		Log.printMessage(parentElement + " " + element);
		return true;
	}

}
