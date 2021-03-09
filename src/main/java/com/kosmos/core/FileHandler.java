package com.kosmos.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
	protected static String pkgName(Object obj) {
		Package pack = obj.getClass().getPackage();
		String packageName = pack.getName();
		return packageName;
	}

	protected static String formFilePath(String path) {
		String userDirectory = System.getProperty("user.dir");
		StringBuilder tempSB = new StringBuilder(userDirectory);
		for (String s : path.replace("\\", "/").split("/")) {
			tempSB.append(File.separator);
			tempSB.append(s);
		}
		return tempSB.toString();

	}

	/**
	 * returns file stream for a given file path
	 * 
	 * @param filepath
	 * @return
	 * @throws FileNotFoundException
	 */
	public static FileReader readStream(String filepath) {
		FileReader file = null;
		try {
			file = new FileReader(formFilePath(filepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return file;
	}

	public File createFile(String path) {
		File file = new File(formFilePath(path));
		try {
			if (file.createNewFile())
				System.out.println("File has been created");
			else
				System.out.println("File already exists.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

}
