package soulpatch.com.utilities.deleteduplicates.pojo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Provides extra methods to compare directories
 * @author akshayviswanathan
 *
 */
public class Directory extends File{
	
	private static final long serialVersionUID = 1L;

	public Directory(String pathname) {
		super(pathname);
		File dir = new File(pathname);
		if(!dir.isDirectory()){
			throw new IllegalArgumentException(pathname + " is not a directory. Please provide the path of a directory");
		}
	}
	
	public String getPath(){
		return getParent();
	}
	
	public String getName(){
		return getParent();
	}
	
	/**
	 * Compares the path name of this directory with the one 
	 * @param directory
	 * @return
	 */
	public boolean isSameDirectory(Directory directory){
		return getParent().equalsIgnoreCase(directory.getName());
	}
	
	/**
	 * Returns the number of files of the given type
	 * @param fileExtension Type of the file searching for
	 * @return number of files of the given type
	 */
	public int fileCountOfType(String fileExtension){
		ArrayList<File> fileList = (ArrayList<File>) Arrays.asList(listFiles());
		int count = 0;
		for(File file: fileList){
			if(((SearchFile)file).isOfFileType(fileExtension)){
				count++;
			}
		}
		return count;
	}
}
