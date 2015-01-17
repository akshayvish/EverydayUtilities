package soulpatch.com.utilities.fileextensions;

import java.io.File;
import java.io.FilenameFilter;

public class FileExtensionFilter implements FilenameFilter{

	@Override
	public boolean accept(File dir, String name) {
		return false;
	}
}
