package com.ccnu.datastr.filecenter;

import java.util.Observable;

import com.ccnu.datastr.util.FileUtil;

/**
 * 
 * @author Walter
 *
 */
public class FileRecognizer extends Observable {

	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		if (this.filePath == null || !this.filePath.equals(filePath)) {
			this.filePath = filePath;
			setChanged();
		}
		notifyObservers();
	}
	

	/**
	 * Here use filename extension to make assumptions about the file type.
	 * 
	 * @return
	 */
	public int getFileType() {
		if (filePath.endsWith(".txt")) {
			return FileUtil.FileType.TXT;
		}else if(filePath.endsWith(".acc")){
			return FileUtil.FileType.ACC;
		}else{
			return FileUtil.FileType.ELSE;
		}
	}

}
