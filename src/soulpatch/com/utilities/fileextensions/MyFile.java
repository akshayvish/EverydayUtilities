package soulpatch.com.utilities.fileextensions;

import java.io.File;

/**
 * Extends the java.io.File class to implement file and directory search
 * functionalities.
 * 
 * @author Akshay Viswanathan
 * 
 */
public class MyFile extends File {

	private static final long serialVersionUID = 1L;
	File file = null;
	long fileSize = 0;
	MyFile[] fileList = null;
	boolean isSameName = false;
	boolean isSimilarName = false; // True if the names have any words same.
	boolean isSameSize = false;
	boolean isDuplicate = false;
	String directory = null;
	String extension = null;

	public MyFile(String pathname) {
		super(pathname);
	}

	/**
	 * True if the names are exact match.
	 * 
	 * @param myFile
	 * @return
	 */
	public boolean isSameName(MyFile myFile) {
		String name1 = fileNameWOExtension(getName()); // Extract filenames without extension and then compare the names.
		String name2 = fileNameWOExtension(getName());
		if (name1 != null && name2 != null)
			if (name1.equalsIgnoreCase(name2))
				return true;
		return false;
	}

	/**
	 * 
	 * @param myFile
	 * @return
	 */
	public boolean isSameType(MyFile myFile) {
		if (fileExtension(getName()).equalsIgnoreCase(fileExtension(myFile.getName())))
			return true;
		return false;
	}

	/**
	 * True if the sizes of the files are exactly same. (Will later modify to
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
	 * True if the above three boolean variables for each file combination is
	 * true.
	 * 
	 * @param myFile2
	 * @return
	 */
	public boolean isDuplicate(MyFile myFile) {
		if (isSameName(myFile) && isSameSize(myFile))
			return true;
		return false;
	}

	/**
	 * 
	 * @param myfile1
	 * @return
	 */
	public boolean equals(MyFile myfile1) {
		String file1 = getParent();
		String file2 = myfile1.getParent();

		if (file1.equalsIgnoreCase(file2)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * The file extension is generally the bit after the last '.' in the full
	 * file name. e.g Beatles.Abbey.Road.mp3 has the extension mp3 and the file
	 * name "Beatles.Abbey.Road". The latter is what we want to use to compare
	 * the names of the files. After finding similar named files, we can compare
	 * the extension and ascertain if the files are actually duplicates or just
	 * named same. So album.mp3 , album.jpg both will come up in this search.
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
	 * Takes the output of the File.getName() and returns only the file name
	 * without the extension. "Flute.mp3" will give "Flute"
	 * 
	 * @param fileName
	 *            - Full file name with extension
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
	 * 
	 * @param extension
	 * @return
	 */
	public boolean isFileWithExt(String extension) {
		return getName().endsWith(extension);
	}

	/**
	 * 
	 * @param directory
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/**
	 * 
	 * @param extension
	 */
	public void setFileExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * 
	 * @return
	 */
	public double getFileSize() {
		double size = length() / 1024; // FileSize in kilobytes
		return size;
	}

	/**
	 * 
	 * @param fileSize
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
}
