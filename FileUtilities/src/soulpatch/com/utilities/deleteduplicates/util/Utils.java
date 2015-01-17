package soulpatch.com.utilities.deleteduplicates.util;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Utils {
	
	public static String fileName = null;
	public static String directory = "//mnt//sdcard//Pictures//";
	public static String fileExtension = null;
	public static double fileSize = 3412229.0;
	public static boolean hidden = false;
	public static boolean exactName = false;
	
	public static JLabel directoryLabel = new JLabel("Directory: ", JLabel.TRAILING);
	public static JLabel fileLabel = new JLabel("File: ", JLabel.TRAILING);
	public static JLabel extensionLabel = new JLabel("Extension: ", JLabel.TRAILING);
	public static JLabel sizeLabel = new JLabel("Size: ", JLabel.TRAILING);
	
	public static JTextField directoryText = new JTextField();
	public static JTextField fileText = new JTextField();
	public static JTextField extensionText = new JTextField();
	public static JTextField sizeText = new JTextField();

	/*public static ArrayList<MyFile> allFiles = new ArrayList<>(); //list of all files in the directory 
	public static ArrayList<MyFile> hiddenFiles = new ArrayList<>(); //List of all hidden files 
	public static ArrayList<MyFile> extFiles = new ArrayList<>(); //List of all files with the given extension
	public static ArrayList<MyFile> nameFiles = new ArrayList<>(); // List of all files with the given names
	public static ArrayList<MyFile> sizeFiles = new ArrayList<>(); // List of all files given the size of the files
	public static ArrayList<MyFile> duplicates = new ArrayList<>(); // List of all duplicate files.
	*/
	public static String REGEX_NUMERIC = "[0-9]";
	public static String REGEX_ALPHABETS_UPPERCASE = "[A-Z]";
	public static String REGEX_ALPHABETS_LOWERCASE = "[a-z]";
}
