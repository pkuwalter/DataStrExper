package com.ccnu.datastr;

import com.ccnu.datastr.dataconvertor.DataConvertor;
import com.ccnu.datastr.filecenter.FileManager;
import com.ccnu.datastr.filecenter.FileRecognizer;
import com.ccnu.datastr.model.DataSet;

/**
 * 
 * @author Walter
 *
 */
public class Test {
	
	public static void main(String[] args){
		FileRecognizer fileRecognizer;
		fileRecognizer = new FileRecognizer();
		FileManager manager = new FileManager(fileRecognizer);
		DataConvertor convertor = new DataConvertor(fileRecognizer);
	
		String path = "C:/Users/Walter/Desktop/数据/SMVS__20140424 174352.txt";
		
		fileRecognizer.setFilePath(path);

		convertor.setRawData(manager.getContent());
		DataSet dataset = convertor.getDataSet();
		
		System.out.println(dataset.keySet());
		
	}

}
