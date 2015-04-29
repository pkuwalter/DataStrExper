package com.ccnu.datastr.dataconvertor;

import java.util.Observable;
import java.util.Observer;

import com.ccnu.datastr.dataconvertor.accelerate.AccelerateDataConvertor;
import com.ccnu.datastr.dataconvertor.ap.APDataConvertor;
import com.ccnu.datastr.filecenter.FileRecognizer;
import com.ccnu.datastr.model.DataSet;
import com.ccnu.datastr.util.FileUtil;

public class DataConvertor implements Observer {

	private DataHelper helper;

	public DataConvertor(FileRecognizer recognizer) {
		recognizer.addObserver(this);
	}

	@Override
	public void update(Observable subject, Object obj) {
		switch (((FileRecognizer) subject).getFileType()) {
			case FileUtil.FileType.TXT:
				if (!(helper instanceof APDataConvertor)) {
					helper = new APDataConvertor();
				}
				break;
			case FileUtil.FileType.ACC:
				if (!(helper instanceof AccelerateDataConvertor)) {
					helper = new AccelerateDataConvertor();
				}
				break;
		}
	}

	public void setRawData(String rawData) {
		helper.setRawData(rawData);
	}

	public DataSet getDataSet() {
		return helper.getDataSet();
	}
}
