package com.ccnu.datastr.filecenter;

import java.util.Observable;
import java.util.Observer;

import com.ccnu.datastr.exceptioncenter.ExceptionManager;
import com.ccnu.datastr.filecenter.acc.ACCFileManager;
import com.ccnu.datastr.filecenter.txt.TXTFileManager;
import com.ccnu.datastr.util.FileUtil;

public class FileManager implements Observer {

	private String filePath;
	private FileHelper helper;

	public FileManager(FileRecognizer recognizer) {
		recognizer.addObserver(this);
	}

	@Override
	public void update(Observable subject, Object obj) {
		filePath = ((FileRecognizer) subject).getFilePath();

		switch (((FileRecognizer) subject).getFileType()) {
			case FileUtil.FileType.TXT:
				if (!(helper instanceof TXTFileManager)) {
					helper = new TXTFileManager();
				}
				break;
			case FileUtil.FileType.ACC:
				if (!(helper instanceof ACCFileManager)) {
					helper = new ACCFileManager();
				}
				break;
			default:
				ExceptionManager.throwUnsupportedFileTypeException();
		}
	}

	public String getContent() {
		return helper.getContent(filePath);
	}

}
