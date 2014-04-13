package soulpatch.com.utilities.fileextensions;

import java.io.File;

/**
 * Extends the java.io.File class to implement new methods.
 * functionalities.
 * 
 * @author Akshay Viswanathan
 * 
 */
public class MyFile extends File {

	private static final long serialVersionUID = 1L;
	
	public MyFile(String pathname) {
		super(pathname);
	}

	/**
	 * True if the names without extensions are an exact match.
	 * 
	 * @param myFile
	 * @return
	 */
	public boolean isSameName(MyFile myFile) {
		String name1 = fileNameWOExtension(getName());
		String name2 = fileNameWOExtension(myFile.getName());
		if (name1 != null && name2 != null)
			if (name1.equalsIgnoreCase(name2))
				return true;
		return false;
	}

	/**
	 * Checks the file extension to see if the files are of the same type
	 * @param myFile
	 * @return
	 */
	public boolean isSameType(MyFile myFile) {
		if (fileExtension(getName()).equalsIgnoreCase(fileExtension(myFile.getName())))
			return true;
		return false;
	}

	/**
	 * True if the sizes of the files are exactly same. (Will be later modified to
	 * have a range)
	 * 
	 * @param myFile
	 * @return
	 */
	public boolean isSameSize(MyFile myFile) {
		if (getFileSize() == myFile.getFileSize())
			return true; // Will later have a range
		return false;
	}

	/**
	 * True if the files have the same name, type and size
	 * 
	 * @param myFile2
	 * @return
	 */
	public boolean isDuplicate(MyFile myFile) {
		if (isSameName(myFile) && isSameSize(myFile) && isSameType(myFile))
			return true;
		return false;
	}

	/**
	 * Returns the file type extension of the file.
	 * 
	 * @param fileName - Full file name
	 * @return extension - File extension
	 */
	public static String fileExtension(String fileName) {
		String extension = null;
		int len = fileName.length() - 1;
		for (int i = len; i > 0; i--) {
			if (fileName.charAt(i) == '.') {
				extension = fileName.substring(i);
			}
		}
		return extension;
	}

	/**
	 * Returns the filename without the file type extension
	 * without the extension. "Flute.mp3" will give "Flute"
	 * 
	 * @param fileName - Full file name with extension
	 * @return fileNameOnly - File name without extension
	 */
	public static String fileNameWOExtension(String fileName) {
		String fileNameOnly = null;
		if (fileName != null && !fileName.startsWith(".")) {
			String extension = fileExtension(fileName);
			fileNameOnly = fileName.substring(0,
					fileName.length() - extension.length());
		}
		return fileNameOnly;
	}
	/**
	 * Returns true if the file is of extension type
	 * 
	 * @param extension
	 * @return
	 */
	public boolean isFileWithExt(String extension) {
		return getName().endsWith(extension);
	}

	/**
	 * Gets the size of the file in KBs (File size divided by 1024)
	 * 
	 * @return
	 */
	public double getFileSize() {
		double size = length() / 1024; // FileSize in kilobytes
		return size;
	}
}
