package soulpatch.com.utilities.deleteduplicates;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.rmi.CORBA.Util;

import soulpatch.com.utilities.deleteduplicates.pojo.SearchFile;
import soulpatch.com.utilities.deleteduplicates.util.Utils;


public class FileSearch1 {
	
	public static void main(String args[]){
		FileSearch1 fs1 = new FileSearch1();
		Utils.fileName = "Dil aaj shair hai.mp3";
		Utils.directory = "/Users/akshayviswanathan/Music/testbed/Oldies/Devanand hits/24 Guide";
		Utils.fileExtension = ".mp3";
		String list = fs1.searchFile(Utils.directory);
		ArrayList<SearchFile> fileList = fs1.fileList(false, list);
		ArrayList<SearchFile> extList = fs1.searchExtFiles(fileList, Utils.fileExtension);
		ArrayList<SearchFile> nameList = fs1.searchFileNames(fileList, Utils.fileName);
		//for(int i=0;i<extList.size();i++)
			//System.out.println(extList.get(i).getFile().getAbsolutePath());
		//for(int i=0;i<fileList.size();i++)
			//System.out.println(fileList.get(i).getFile().getAbsolutePath());
//		for(int i=0;i<nameList.size();i++){
//			System.out.println(nameList.get(i).getFile().getParentFile());
//		}
	}

	public String searchFile(String path){
		String files = "";
		File dir = new File(path);
		File[] list = dir.listFiles();
		for(int i=0;i<list.length;i++){
			if(list[i].isFile())
				files = files + FileSearch.FILE_REGEX + list[i].getAbsolutePath();
			else if(list[i].isDirectory())
				files = files + FileSearch.FILE_REGEX + searchFile(list[i].getAbsolutePath());
		}
		return files;
	}
	
	public ArrayList<SearchFile> fileList(boolean hidden, String fileNameList){
		ArrayList<SearchFile> fileList = new ArrayList<SearchFile>();
		String[] str = fileNameList.split(FileSearch.FILE_REGEX);
		for(int i=0;i<str.length;i++){
			if(!str[i].isEmpty())
				fileList.add(new SearchFile(str[i]));
		}
		return fileList;
	}
	
	//Search files with the type or extension
	public ArrayList<SearchFile> searchExtFiles(ArrayList<SearchFile> allFiles, String extension){
		ArrayList<SearchFile> extList = new ArrayList<SearchFile>();
		for(int i=0;i<allFiles.size();i++){
//			if(allFiles.get(i).isFileWithExt(extension)){
//				extList.add(allFiles.get(i));
//			}
		}
		return extList;
	}
	
	//Search files with given name
	public ArrayList<SearchFile> searchFileNames(ArrayList<SearchFile> allFiles, String name){
		ArrayList<SearchFile> nameList = new ArrayList<SearchFile>();
		for(int i=0;i<allFiles.size();i++){
//			if(allFiles.get(i).getFile().getName().equalsIgnoreCase(name)){
//				nameList.add(allFiles.get(i));
//			}
		}
		return nameList;
	}
}
