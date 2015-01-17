package soulpatch.com.utilities.fileextensions;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;

import soulpatch.com.utilities.deleteduplicates.FileExtensions;
import soulpatch.com.utilities.deleteduplicates.FileSearch;
import soulpatch.com.utilities.deleteduplicates.pojo.Directory;
import soulpatch.com.utilities.deleteduplicates.pojo.SearchFile;
import soulpatch.com.utilities.deleteduplicates.util.Utils;

public class DirectoryOps extends File{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String START_PATTERN;


	/**
	 * 
	 * @param fileExt
	 * @return
	 */
	public SearchFile[] getFileList(FileExtensions fileExt) {
		SearchFile[] fileList = null;
		if (isDirectory()) {
			FilenameFilter 
			File files[] = listFiles(fileExt);
			fileList = new SearchFile[file.length];
			for (int i = 0; i < fileList.length; i++){
				fileList[i] = (SearchFile) file[i];
			}
		}
		return fileList;
	}

	/**
	 * 
	 * @return
	 */
	public SearchFile[] getFileList() {
		SearchFile[] fileList = null;
		if (isDirectory()) {
			File file[] = listFiles();
			fileList = new SearchFile[file.length];
			for (int i = 0; i < fileList.length; i++)
				fileList[i] = (SearchFile) file[i];
		}
		return fileList;
	}

	/**
	 * 
	 * @param directory
	 */
	public void arrangeFolders(String directory, Directory directory) {

		File yr = null, mnth = null, day = null;
		if (directory.isDirectory()) {
			File[] directories = directory.listFiles();
			for (int i = 0; i < directories.length; i++) {
				// for each folder check if the name follows a pattern.
				String dirName = directories[i].getName();
				if (dirName.startsWith(START_PATTERN)) {
					String[] params = dirName.split("_");
					if (params.length >= 3) {
						yr = new File(Utils.directory + "/" + params[0]);
						mnth = new File(yr.getAbsolutePath() + "/" + params[1]); // Sub-directory structure is defined here
						day = new File(mnth.getAbsolutePath() + "/" + params[2]);

						if (!yr.exists())
							yr.mkdir();
						if (!mnth.exists())
							mnth.mkdir();
						if (!day.exists())
							day.mkdir();
					}
					File[] fileList = directories[i].listFiles();
					for (int j = 0; j < fileList.length; j++) {
						File tempFile = new File(day.getAbsolutePath());
						directories[i].renameTo(tempFile);
						System.out.println("Exists" + tempFile.exists());
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param files
	 * @return
	 */
	public ArrayList<SearchFile> findDirectories(String files) {
		ArrayList<SearchFile> fileList = new ArrayList<SearchFile>();
		String[] str = files.split(FileSearch.FILE_REGEX);
		for (int i = 0; i < str.length; i++) {
			if (!str[i].isEmpty()) {
				fileList.add(new SearchFile(str[i]));
			}
		}
		return fileList;
	}

	/**
	 * 
	 * @param inputArr
	 * @return
	 */
	public HashSet<Directory> duplicateDirectories(ArrayList<Directory> inputArr) {
		HashSet<Directory> nameDuplicates = new HashSet<Directory>();
		for (int i = 0; i < inputArr.size() - 2; i++) {
			for (int j = i; j < inputArr.size() - 1; j++) {
				if ((inputArr.get(i).getName()
						.equalsIgnoreCase((inputArr.get(j).getName())))) {
					if (!inputArr.get(i).equals(inputArr.get(j))) {
						// I need to save the paths of both the directories which have similar names.
						nameDuplicates.add(inputArr.get(i));
						nameDuplicates.add(inputArr.get(j));// It's a set, so duplicates within the set are taken care of.
						System.out.println(inputArr.get(i).getAbsolutePath());
						System.out.println("***** " + inputArr.get(j).getAbsolutePath());
					}
				}
			}
		}
		return nameDuplicates;
	}	
}
