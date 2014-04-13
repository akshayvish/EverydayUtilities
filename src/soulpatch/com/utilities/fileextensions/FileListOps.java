package soulpatch.com.utilities.fileextensions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * File operations for MyFile object.
 * @author Akshay Viswanathan
 *
 */
public class FileListOps {

	/**
	 * Delete files in the given array.
	 * 
	 * @param delArray
	 */
	public static void deleteFiles(ArrayList<MyFile> fileList) {
		for(int i=0;i<fileList.size();i++)
			if (fileList.get(i).exists())
				fileList.get(i).delete();
	}

	/**
	 * Renames a file. This can be used to rename a set of similar named files
	 * to something new.
	 * 
	 * @param inputArr
	 * @param fromFileName
	 * @param toFileName
	 */
	public static void renameFiles(ArrayList<MyFile> fileList, String fromFileName, String toFileName) {
		for(int i=0;i<fileList.size();i++){
			File newFile;
			newFile = new File(fileList.get(i).getParent() + "/" + toFileName);
			fileList.get(i).renameTo(newFile);
		}
	}

	/**
	 * renames or removes patterns from the file names starting with a certain
	 * pattern. Like Files starting with Track1.mp3 Track2.mp3
	 * 
	 * @param inputArr
	 * @param startPattern
	 * @param endPattern
	 */
	public static void removePatterns_Start(ArrayList<MyFile> fileList, String startPattern, String endPattern) {
		File newFile;
		for(int i=0;i<fileList.size();i++){
			String fileName = fileList.get(i).getName();
			if ((fileName.startsWith(startPattern))) {
				String split[] = fileName.split(startPattern);
				newFile = new File(fileList.get(i).getParent() + "/" + split[1]);
				fileList.get(i).renameTo(newFile);
			}
		}
	}

	/**
	 * renames or removes patterns from the file names ending with a certain
	 * pattern. Like Files starting with 01_Track.mp3 02_Track.mp3
	 * 
	 * @param inputArr
	 * @param endPattern
	 */
	public static void removePatterns_End(ArrayList<MyFile> fileList, String endPattern) {
		File newFile;
		for(int i=0;i<fileList.size();i++){
			String fileName = fileList.get(i).getName();
			if ((fileName.endsWith(endPattern))) {
				String split[] = fileName.split(endPattern);
				newFile = new File(fileList.get(i).getParent() + "/" + split[1]);
				fileList.get(i).renameTo(newFile);
			}
		}
	}

	/**
	 * removes patterns from the file names found anywhere in the name. Like
	 * Files with 01_Track.mp3 02_Track_1.mp3 02_Track.mp3
	 * 
	 * @param inputArr
	 * @param pattern
	 */
	public static void removePattern_Anywhere(ArrayList<MyFile> fileList, String pattern) {
		for(int i=0;i<fileList.size();i++){
			String fileName = fileList.get(i).getName();
			File newFile;
			if (fileName.contains(pattern)) {
				String str = fileName.replace(pattern, "");
				newFile = new File(fileList.get(i).getParent() + "/" + str);
				fileList.get(i).renameTo(newFile);
			}
		}
	}

	/**
	 * 
	 * @param inputArr
	 * @param pattern
	 * @throws IOException 
	 */
	public static void readFile4Pattern(ArrayList<MyFile> inputArr, String pattern) throws IOException {
		for (int i = 0; i < inputArr.size(); i++) {
			FileReader fr;
			BufferedReader br = null;
			try {
				fr = new FileReader(inputArr.get(i));
				br = new BufferedReader(fr);
				String line;
				while ((line = br.readLine()) != null) {
					if (line.contains(pattern))
						System.out.println(line);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				br.close();
			}
		}
	}
}
