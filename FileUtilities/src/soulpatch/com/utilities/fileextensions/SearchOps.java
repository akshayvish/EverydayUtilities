package soulpatch.com.utilities.fileextensions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import soulpatch.com.utilities.deleteduplicates.FileSearch;
import soulpatch.com.utilities.deleteduplicates.pojo.SearchFile;

public class SearchOps {
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public String searchFiles_All(String path) {
		String files = "";
		File dir = new File(path);
		File[] list = dir.listFiles();
		for (int i = 0; i < list.length; i++) {
			if (list[i].isFile())
				files = files + FileSearch.FILE_REGEX
				+ list[i].getAbsolutePath();
			else if (list[i].isDirectory())
				files = files + FileSearch.FILE_REGEX
				+ searchFiles_All(list[i].getAbsolutePath());
		}
		return files;
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public String searchDirectories_All(String path) {
		String files = "";
		File dir = new File(path);
		File[] list = dir.listFiles();
		for (int i = 0; i < list.length; i++) {
			if (list[i].isDirectory()) {
				files = files + FileSearch.FILE_REGEX
						+ list[i].getAbsolutePath()
						+ searchDirectories_All(list[i].getAbsolutePath());
			}
		}
		return files;
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public String searchFiles_All_ExcludeDirectory(ArrayList<SearchFile> excludeDir, String path) {
		String files = "";
		File dir = new File(path);
		File[] list = dir.listFiles();
		for (int i = 0; i < list.length; i++) {
			if (list[i].isFile())
				files = files + FileSearch.FILE_REGEX
				+ list[i].getAbsolutePath();
			else if (list[i].isDirectory()) {
				boolean flag = false;
				// See if it is in the exclusion list or not.
				for (int j = 0; j < excludeDir.size(); j++) {
					if (list[i].getAbsolutePath().equals(
							// Do not proceed with the search if it's in the exclusion list.
							excludeDir.get(j).getAbsolutePath())) 
						flag = true;
				}
				if (!flag)
					files = files
					+ FileSearch.FILE_REGEX
					+ searchFiles_All_ExcludeDirectory(excludeDir, list[i].getAbsolutePath());
			}
		}
		return files;
	}

	/**
	 * Get file list and put it in an ArrayList and return.
	 * 
	 * @param hidden
	 * @param fileNameList
	 * @return
	 */
	public ArrayList<SearchFile> searchFiles_All(boolean hidden, String fileNameList) {
		ArrayList<SearchFile> fileList = new ArrayList<SearchFile>();
		String[] str = fileNameList.split(FileSearch.FILE_REGEX);
		for (int i = 0; i < str.length; i++) {
			if (!str[i].isEmpty())
				fileList.add(new SearchFile(str[i]));
		}
		return fileList;
	}

	/**
	 * Search files with the type or extension
	 * 
	 * @param allFiles
	 * @param extension
	 * @return
	 */
	public ArrayList<SearchFile> searchFiles_FileExtension(
			ArrayList<SearchFile> allFiles, String extension) {
		ArrayList<SearchFile> extList = new ArrayList<SearchFile>();
		for (int i = 0; i < allFiles.size(); i++) {
			if (allFiles.get(i).isFileWithExt(extension))
				extList.add(allFiles.get(i));
		}
		return extList;
	}

	/**
	 * Search files with given name
	 * 
	 * @param allFiles
	 * @param name
	 * @return
	 */
	public ArrayList<SearchFile> searchFiles_ExactNames(ArrayList<SearchFile> allFiles,
			String name) {
		ArrayList<SearchFile> nameList = new ArrayList<SearchFile>();
		for (int i = 0; i < allFiles.size(); i++) {
			if (allFiles.get(i).getName().contains(name))
				nameList.add(allFiles.get(i));
		}
		return nameList;
	}

	/**
	 * Search files with given size
	 * 
	 * @param allFiles
	 * @param size
	 * @return
	 */
	public ArrayList<SearchFile> searchFiles_ExactSize(ArrayList<SearchFile> allFiles,
			double size) {
		ArrayList<SearchFile> sizeList = new ArrayList<SearchFile>();
		for (int i = 0; i < allFiles.size(); i++) {
			if (allFiles.get(i).length() == size)
				sizeList.add(allFiles.get(i));
		}
		return sizeList;
	}

	/**
	 * 
	 * @param allFiles
	 * @param size
	 * @return
	 */
	public ArrayList<SearchFile> searchFiles_SizeRange(ArrayList<SearchFile> allFiles,
			double size) {
		ArrayList<SearchFile> sizeList = new ArrayList<SearchFile>();
		for (int i = 0; i < allFiles.size(); i++) {
			if (allFiles.get(i).length() == size)
				sizeList.add(allFiles.get(i));
		}
		return sizeList;
	}

	/**
	 * Finds files of the same size from within the list.
	 * 
	 * @param inputArr
	 * @return
	 */
	public HashSet<SearchFile> findSizeDupsList(ArrayList<SearchFile> inputArr) {
		HashSet<SearchFile> sizeDups = new HashSet<SearchFile>();
		for (int i = 0; i < inputArr.size() - 2; i++) {
			for (int j = i; j < inputArr.size() - 1; j++) {
				if ((inputArr.get(i).isSameSize(inputArr.get(j)))) {
					if (!inputArr.get(i).equals(inputArr.get(j))) {
						sizeDups.add(inputArr.get(i));
						sizeDups.add(inputArr.get(j));
					}
				}
			}
		}
		return sizeDups;
	}

	/**
	 * Finds and lists files of the same size from the given lists.
	 * 
	 * @param inputArr1
	 * @param inputArr2
	 * @return
	 */
	public HashSet<SearchFile> findSizeDupList(ArrayList<SearchFile> inputArr1,
			ArrayList<SearchFile> inputArr2) {
		HashSet<SearchFile> sizeDups = new HashSet<SearchFile>();
		for (int i = 0; i < inputArr1.size() - 1; i++) {
			for (int j = 0; j < inputArr2.size() - 1; j++) {
				if ((inputArr1.get(i).isSameSize(inputArr2.get(j)))) {
					if (!inputArr1.get(i).equals(inputArr2.get(j))) {
						sizeDups.add(inputArr1.get(i));
						sizeDups.add(inputArr2.get(j));
					}
				}
			}
		}
		return sizeDups;
	}

	/**
	 * File.jpg and File.mp3 and File.avi all are considered similar here.
	 * 
	 * @param inputArr
	 * @return
	 */
	public HashSet<SearchFile> findNameDupList(ArrayList<SearchFile> inputArr) {
		HashSet<SearchFile> nameDups = new HashSet<SearchFile>();
		for (int i = 0; i < inputArr.size() - 2; i++) {
			// System.out.println(inputArr.get(i).getPath());
			for (int j = i; j < inputArr.size() - 1; j++) {
				if ((inputArr.get(i).isSameName(inputArr.get(j)))) {
					if (!inputArr.get(i).equals(inputArr.get(j))) {
						nameDups.add(inputArr.get(i));
						nameDups.add(inputArr.get(j));
					}
				}
			}
		}
		return nameDups;
	}

	/**
	 * File.jpg and File.mp3 and File.avi are considered different here.
	 * 
	 * @param inputArr1
	 * @param inputArr2
	 * @return 
	 */
	public HashSet<SearchFile> findNameDupWExt(ArrayList<SearchFile> inputArr1,
			ArrayList<SearchFile> inputArr2) {
		HashSet<SearchFile> nameDups = new HashSet<SearchFile>();
		for (int i = 0; i < inputArr1.size() - 1; i++) {
			for (int j = 0; j < inputArr2.size() - 1; j++) {
				if ((inputArr1.get(i).isSameType(inputArr2.get(j))) && (inputArr1.get(i).isSameName(inputArr2.get(j)))) {
					if (!inputArr1.get(i).equals(inputArr2.get(j))) {
						nameDups.add(inputArr1.get(i));
						nameDups.add(inputArr2.get(j));
					}
				}
			}
		}
		return nameDups;
	}
	
	/**
	 * 
	 * @param inputArr1
	 * @param inputArr2
	 * @return
	 */
	public HashSet<SearchFile> findNameDupWOExt(ArrayList<SearchFile> inputArr1,
			ArrayList<SearchFile> inputArr2) {
		HashSet<SearchFile> nameDups = new HashSet<SearchFile>();
		for (int i = 0; i < inputArr1.size() - 1; i++) {
			for (int j = 0; j < inputArr2.size() - 1; j++) {
				if ((inputArr1.get(i).isSameType(inputArr2.get(j))) && (inputArr1.get(i).isSameName(inputArr2.get(j)))) {
					if (!inputArr1.get(i).equals(inputArr2.get(j))) {
						nameDups.add(inputArr1.get(i));
						nameDups.add(inputArr2.get(j));
					}
				}
			}
		}
		return nameDups;
	}

	/**
	 * list of files that have different names in the given directory. Opposite
	 * of findDups
	 * 
	 * @param inputArr - array of files to look into
	 * @return diffNameFiles - List of files with dissimilar names
	 */
	public HashSet<SearchFile> differentNameFiles(ArrayList<SearchFile> inputArr) {
		// Follow same pattern as findDups, only add the element when
		// they're not equal instead of otherwise.
		HashSet<SearchFile> diffNameFiles = new HashSet<SearchFile>();
		for (int i = 0; i < inputArr.size() - 2; i++) {
			for (int j = i; j < inputArr.size() - 1; j++) {
				if (!(inputArr.get(i).isSameName(inputArr.get(j)))) {
					diffNameFiles.add(inputArr.get(i));
					diffNameFiles.add(inputArr.get(j));
				}
			}
		}
		return diffNameFiles;
	}

	/**
	 * Identify files that start/end with a particular pattern. e.g 01-Let it
	 * be.mp3, 02-Help!.mp3 etc. So pick files that start/end with a particular
	 * pattern defined by a character sequence or regex.
	 * 
	 * @param inputArr - array of files to look into
	 * @param startPattern - Pattern we're looking for in the beginning of the file name
	 * @param endPattern - Pattern we're looking for at the end of the file name
	 * @return fileList - List of files with similar name patterns
	 */
	public ArrayList<SearchFile> namePattern(ArrayList<SearchFile> inputArr,
			String startPattern, String endPattern) {
		ArrayList<SearchFile> fileList = new ArrayList<SearchFile>();
		for (int i = 0; i < inputArr.size(); i++) {
			if (startPattern != null) {
				String fileName = inputArr.get(i).getName();
				if (fileName.startsWith(startPattern))
					fileList.add(inputArr.get(i));
			}

			// We could be looking for a file starting with the startPattern and ending with the endPattern both.
			if (endPattern != null) {
				if (inputArr.get(i).getName().endsWith(endPattern))
					fileList.add(inputArr.get(i));
			}
		}
		return fileList;
	}

}
