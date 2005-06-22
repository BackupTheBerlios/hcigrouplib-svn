package edu.stanford.hci.lib;

import java.io.*;

/**
 * Generally useful methods for system-level operations.
 * 
 * @author ronyeh, srk
 * 
 * <p>
 * This software is distributed under the <a
 * href="http://hci.stanford.edu/research/copyright.txt">Berkeley Software
 * Distribution License</a>.
 * </p>
 * 
 * @author <a href="http://graphics.stanford.edu/~ronyeh">Ron Yeh</a> (
 *         ronyeh(AT)cs.stanford.edu )
 * @author <a href="http://hci.stanford.edu/srk">Scott Klemmer</a> (
 *         srk(AT)cs.stanford.edu )
 */
public class SystemUtils {

	/**
	 * @author ronyeh
	 * 
	 * A Java FileFilter that accepts file extensions as Strings
	 */
	private static class FileFilter extends javax.swing.filechooser.FileFilter
			implements FilenameFilter {

		private boolean acceptDirectories = true;

		private String[] extensions = { "" };

		public FileFilter(String[] exts) {
			this.extensions = new String[exts.length];
			for (int i = 0; i < exts.length; i++) {
				extensions[i] = exts[i].toLowerCase();
			}
		}

		/**
		 * This method is for filechoosers, and makes use of acceptDirectories
		 * 
		 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
		 */
		public boolean accept(File f) {
			return accept(null, f.getName())
					|| (acceptDirectories && f.isDirectory());
		}

		/**
		 * This method does not make use of acceptDirectories...
		 * 
		 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
		 */
		public boolean accept(File fileDir, String name) {
			if (name == null) {
				return false;
			}

			for (int i = 0; i < extensions.length; i++) {
				if (name.toLowerCase().endsWith(extensions[i])) {
					return true;
				}
			}
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.filechooser.FileFilter#getDescription()
		 */
		public String getDescription() {
			StringBuffer buf = new StringBuffer();
			int len = extensions.length;
			for (int i = 0; i < len; i++) {
				buf.append("*" + extensions[i]);
				if (i != len - 1) {
					buf.append(", ");
				}
			}
			return buf.toString();
		}

		public void setAcceptDirectories(boolean acceptDirectories) {
			this.acceptDirectories = acceptDirectories;
		}
	}

	/**
	 * Given a path and a list of file extensions, this method returns all files
	 * that match that extension (case insensitive).
	 * 
	 * @param path
	 * @param extensionFilter
	 * @return
	 */
	public static File[] listFiles(File path, String[] extensionFilter) {
		return path.listFiles(new FileFilter(extensionFilter));
	}

	/**
	 * Print an array to console.
	 * 
	 * @param array
	 */
	public static void printArray(Object[] array) {
		String className = array[0].getClass().toString();
		System.out.print(className.substring(className.lastIndexOf(".") + 1,
				className.length())
				+ " Array: [");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i != array.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}

}
