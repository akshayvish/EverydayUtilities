package soulpatch.com.utilities.deleteduplicates;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import soulpatch.com.utilities.deleteduplicates.util.Utils;
/*import org.cmc.music.common.ID3ReadException;
 import org.cmc.music.common.ID3WriteException;
 import org.cmc.music.metadata.IMusicMetadata;
 import org.cmc.music.metadata.MusicMetadataSet;
 import org.cmc.music.myid3.MyID3;
 import org.cmc.music.util.FSTraversal;

 import com.asprise.util.pdf.in;*/

//Class to search a file name and list the path of the filenames
public class FileSearch {
	Scanner scan = new Scanner(System.in);
	SearchFile searchFile = null;
	String userChoice = null;
	static int numOfFiles = 0;
	static int numOfFolders = 0;
	public static String FILE_REGEX = "MyFileSearch";

	// File output = new File(System.getProperty("user.dir") + "/Output.txt");

	public static void main(String args[]) {
		FileSearch fs = new FileSearch();
		// fs.initialize();

		// Utils.excludeDir.add(new MyFile(new File(Utils.directory +
		// "/26 Paying Guest")));
		// Utils.excludeDir.add(new MyFile(new File(Utils.directory +
		// "/25  Jewel Thief")));

		/*if ((Utils.fileName != null) && (!Utils.fileName.isEmpty())
				&& (!Utils.fileName.equalsIgnoreCase(""))) {
			fs.searchFile = new MyFile(new File(Utils.directory + "/"
					+ Utils.fileName + Utils.fileExtension));
			fs.searchFile.setDirectory(Utils.directory);
			fs.searchFile.setFileExtension(Utils.fileExtension);
		} else {
			fs.searchFile = new MyFile(new File(Utils.directory));
		}

		// Utils.allFiles =
		// fs.searchFiles_All(false,fs.searchFiles_All_ExcludeDirectory(Utils.directory));
		// Utils.extFiles = fs.searchExtFiles(Utils.allFiles,
		// Utils.fileExtension);
		// fs.renameFiles(Utils.allFiles, "Dil aaj shair hai.mp3", "1.mp3");
		// ArrayList<MyFile> myList =
		// fs.namePattern(Utils.allFiles,"eStmt_",null);
		// fs.removePattern_Anywhere(Utils.allFiles, "");
		/*
		 * try { fs.audioFileProp(new File(Utils.directory+"/"+"Zinda.mp3")); }
		 * catch (UnsupportedEncodingException e) { e.printStackTrace(); } catch
		 * (ID3WriteException e) { e.printStackTrace(); } catch (IOException e)
		 * { e.printStackTrace(); }
		 */
		// fs.arrangeFolders(Utils.directory);
		// fs.duplicateDirectories(fs.findDirectories(fs.searchDirectories(Utils.directory)));
		// Utils.nameFiles = fs.searchExactFileNames(Utils.allFiles,
		// Utils.fileName);
		// Utils.sizeFiles = fs.searchExactFileSize(Utils.allFiles,
		// Utils.fileSize);
		// ArrayList<MyFile> mylist = fs.findNameDuplicatesList(Utils.allFiles,
		// Utils.extFiles);
		// HashSet<MyFile> mylist = fs.findNameDuplicatesList(Utils.allFiles);
		// HashSet<MyFile> mySecList =
		// fs.findNameDuplicatesList(Utils.allFiles,Utils.extFiles);
		// HashSet<MyFile> mySizelist =
		// fs.findSizeDuplicatesList(Utils.allFiles);
		// HashSet<MyFile> mySecSizeList =
		// fs.findSizeDuplicatesList(Utils.allFiles,Utils.sizeFiles);
		// System.out.println(mylist.size());
		/*
		 * Iterator<MyFile> iter = mylist.iterator(); while(iter.hasNext())
		 * System.out.println((iter.next()).getAbsolutePath()); Iterator<MyFile>
		 * iterSec = mySecList.iterator(); while(iterSec.hasNext())
		 * System.out.println((iterSec.next()).getAbsolutePath());
		 */

		/*
		 * Iterator<MyFile> iter = mySizelist.iterator(); while(iter.hasNext())
		 * System.out.println((iter.next()).getAbsolutePath());
		 */

		/*
		 * Iterator<MyFile> iterSec = mySecSizeList.iterator();
		 * while(iterSec.hasNext())
		 * System.out.println((iterSec.next()).getAbsolutePath());
		 * //fs.writeListToFile(Utils.allFiles, Utils.directory +
		 * "/Duplicates.txt");
		 */

		// for(int i=0;i<myList.size();i++)
		// System.out.println(myList.get(i).getAbsolutePath());

		// Utils.duplicates = fs.findDuplicatesFromList(Utils.extFiles);
		// for(int i=0;i<Utils.extFiles.size();i++)
		// System.out.println("Duplicates : " +
		// Utils.extFiles.get(i).getAbsolutePath());
		/*
		 * System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
		 * ;
		 */for (int i = 0; i < Utils.allFiles.size(); i++)
			System.out.println(Utils.allFiles.get(i).getAbsolutePath());

		System.out.println("************************************************");

		/*
		 * for(int i=0;i<Utils.extFiles.size();i++)
		 * System.out.println(Utils.extFiles.get(i).getName());
		 * 
		 * //fs.deleteDuplicates();
		 * 
		 * //ArrayList<String> arr = fs.searchExactFileName(fs.searchFile,
		 * Utils.fileExtension, Utils.fileName); //System.out.println("Matches "
		 * + arr.size()); /*fs.getUserChoice(fs); switch(fs.userChoice){ case
		 * "1": fs.searchExactFileName(fs); case "2":
		 * fs.searchSimilarFileName(fs); case "3":
		 * fs.searchFileWithExtensions(fs); }
		 */
	}

	public void initialize() {
		System.out.println("Enter the file name you want to search for : ");
		Utils.fileName = scan.nextLine();
		System.out
				.println("Enter the directory in which you want to search : ");
		Utils.directory = scan.nextLine();
		System.out.println("Enter the type of file you want to search for : ");
		Utils.fileExtension = scan.nextLine();
	}

	public void getUserChoice(FileSearch fs) {
		System.out.println("Enter a choice ");
		System.out.println("1. Search files with exact name");
		System.out.println("2. Search files with similar names");
		System.out.println("3. Search all files with extension");
		fs.userChoice = scan.nextLine();
	}

	// List exact file matches given the path, filename and extension
	public ArrayList<SearchFile> common(ArrayList<SearchFile> array1,
			ArrayList<SearchFile> array2) {
		ArrayList<SearchFile> matches = new ArrayList<SearchFile>();
		for (int i = 0; i < array1.size(); i++) {
			for (int j = 0; j < array2.size(); j++) {
				if (!array1.get(i).getName().isEmpty()
						&& array1.get(i).equals(array2.get(j)))
					matches.add(array1.get(i));
			}
		}
		return matches;
	}

	// List similar file names given file name, directory and extension
	/*
	 * public void searchSimilarFileName(FileSearch fs){
	 * 
	 * if(fs.searchFile.exists()){ if(fs.searchFile.isDirectory()){ //It's a
	 * folder / directory MyFile[] fileList = fs.searchFile.getFileList(new
	 * FileExtension(Utils.fileExtension)); for(int i=0;i<fileList.length;i++){
	 * System.out.println(fileList[i].getAbsolutePath()); } }else
	 * if(fs.searchFile.isFile()){ //It's a file get the extension if you need
	 * to refine the search Vector<String[]> nameSplit = new Vector<String[]>();
	 * nameSplit.add(fs.searchFile.getName().split("[0-9]"));
	 * nameSplit.add(fs.searchFile.getName().split(" "));
	 * System.out.println("Splitting elements "); for(int
	 * i=0;i<nameSplit.size();i++){ for(int j=0;j<nameSplit.get(i).length;j++)
	 * System.out.println(nameSplit.get(i)[j]); } } } }
	 */

	// Return true if the path is one of the exclusion lists.
	public boolean isExcluded(String dirPath, ArrayList<SearchFile> excludeDir) {
		for (int i = 0; i < excludeDir.size(); i++) {
			if (dirPath.equalsIgnoreCase(excludeDir.get(i)
					.getAbsolutePath()))
				return true;
		}
		return false;
	}

	public void writeListToFile(ArrayList<SearchFile> inputArr, String fileName) {
		try {
			File file = new File(Utils.directory + "/" + fileName);
			if (!file.exists())
				file.createNewFile();
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < inputArr.size(); i++)
				bw.write(inputArr.get(i).getAbsolutePath());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Using jar created and available on the following website
	// http://www.fightingquaker.com/myid3/
	/*
	 * public void audioFileProp(File file) throws UnsupportedEncodingException,
	 * IOException, ID3WriteException{ MusicMetadataSet src_set; IMusicMetadata
	 * metadata; try { src_set = new MyID3().read(file); if (src_set == null) //
	 * perhaps no metadata System.out.println("No Metadata");
	 * 
	 * metadata = src_set.getSimplified(); String artist = metadata.getArtist();
	 * String album = metadata.getAlbum(); String song_title =
	 * metadata.getSongTitle(); Number track_number =
	 * metadata.getTrackNumberNumeric();
	 * 
	 * System.out.println("song_title " + song_title);
	 * System.out.println("artist " + artist); System.out.println("album " +
	 * album); System.out.println("track_number " + track_number);
	 * 
	 * metadata.setArtist("Bob Marley"); File newFile = new
	 * File(file.getAbsolutePath());
	 * 
	 * new MyID3().write(file, newFile, src_set, metadata); } catch
	 * (ID3ReadException e1) { e1.printStackTrace(); } catch (IOException e1) {
	 * e1.printStackTrace(); } // read metadata }
	 */

}