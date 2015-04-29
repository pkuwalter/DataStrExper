package com.ccnu.datastr.filecenter.acc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.ccnu.datastr.filecenter.FileHelper;

public class ACCFileManager implements FileHelper{

	private FileInputStream fis;
	@Override
	public String getContent(String filePath) {
		String content = null;
		File file = new File(filePath);

		try {
			fis = new FileInputStream(file);
			byte[] buffer = new byte[(int) file.length()];
			fis.read(buffer);
			content = new String(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			fis = null;
		}

		return content;
	}
}
