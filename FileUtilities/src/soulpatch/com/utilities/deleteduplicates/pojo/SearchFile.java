package soulpatch.com.utilities.deleteduplicates.pojo;

import java.io.File;

/**
 * Extends the java.io.File class to implement new methods.
 * functionalities.
 * 
 * @author Akshay Viswanathan
 * 
 */
public class SearchFile extends File {

	private static final long serialVersionUID = 1L;
	private final String fileName;
	private final String fileExtension;
	
	public SearchFile(String pathname) {
		super(pathname);
		if(pathname.contains("\\/") || pathname.contains("\\")){
			int index = pathname.lastIndexOf("\\/") > 0? pathname.lastIndexOf("\\/") : pathname.lastIndexOf("\\");
			pathname = pathname.substring(index);
			fileName = fileNameWOExtension(pathname);
			fileExtension = fileExtension(pathname);
		}else{
			
		}
	}

	/**
	 * True if the names without extensions are an exact match.
	 * 
	 * @param myFile
	 * @return true if same name false otherwise
	 */
	public boolean isSameName(SearchFile givenFile) {
		String current = fileNameWOExtension(getName());
		String given = fileNameWOExtension(givenFile.getName());
		if (current != null && given != null && current.equalsIgnoreCase(given)){
			return true;
		}
		return false;
	}

	/**
	 * Checks the file extension to see if the files are of the same type
	 * @param myFile
	 * @return 
	 */
	public boolean isSameType(SearchFile myFile) {
		if (fileExtension(getName()).equalsIgnoreCase(fileExtension(myFile.getName()))){
			return true;
		}
		return false;
	}

	/**
	 * True if the sizes of the files are exactly same. (Will be later modified to
	 * have a range)
	 * 
	 * @param myFile
	 * @return
	 */
	public boolean isSameSize(SearchFile myFile, Sizes size) {
		if (getFileSize(size) == myFile.getFileSize(size)){
			return true; // Will later have a range
		}
		return false;
	}
	
	/**
	 * True if the files have the same name, type and size
	 * 
	 * @param myFile2
	 * @return
	 */
	public boolean isDuplicate(SearchFile myFile) {
		if (isSameName(myFile) && isSameSize(myFile, Sizes.KB) && isSameType(myFile)){
			return true;
		}
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
	public boolean isOfFileType(String extension) {
		return getName().endsWith(extension);
	}

	/**
	 * Gets the size of the file in the units demanded
	 * 
	 * @return
	 */
	public double getFileSize(Sizes sizes) {
		double fileSize = 0;

		switch(sizes){
		case KB:
			fileSize = length()/SizeUnit.KB.getValue();
		case MB:
			fileSize = length()/SizeUnit.MB.getValue();
		case GB:
			fileSize = length()/SizeUnit.GB.getValue();
		case TB:
			fileSize = length()/SizeUnit.TB.getValue();
		default:
			length();
		}
		return fileSize;
	}
}

enum Sizes{
	KB,MB,GB,TB;
}

enum SizeUnit{
	KB(1024), MB(1048576), GB(1073741824), TB(1099511627775.96);
	private double sizeValue;

	private SizeUnit(double sizeValue){
		this.sizeValue = sizeValue;
	}
	public double getValue(){
		return sizeValue;
	}
}