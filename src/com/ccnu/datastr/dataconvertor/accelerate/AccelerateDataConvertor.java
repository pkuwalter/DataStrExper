package com.ccnu.datastr.dataconvertor.accelerate;

import java.util.ArrayList;
import java.util.List;

import com.ccnu.datastr.dataconvertor.DataHelper;
import com.ccnu.datastr.model.Data;
import com.ccnu.datastr.model.DataSet;
import com.ccnu.datastr.model.accelerate.AccData;
import com.ccnu.datastr.model.accelerate.AccDataSet;
import com.ccnu.datastr.util.DataFactory;


public class AccelerateDataConvertor implements DataHelper{

	private String rawData;
	private AccDataSet accelerateDataSet;
	private String[] title;
	private int startPosition;

	@Override
	public void setRawData(String rawData) {
		this.rawData = rawData;
		doConvert();
	}

	@Override
	public DataSet getDataSet() {
		// TODO Auto-generated method stub
		return accelerateDataSet;
	}
	private void doConvert(){
		accelerateDataSet = new AccDataSet();
		List<String> xAxisValue = new ArrayList<String>();
		List<String> yAxisValue = new ArrayList<String>();
		List<String> zAxisValue = new ArrayList<String>();
		List<String> difTime = new ArrayList<String>();

		String[] records = getRecords();
		title = getTitle(records);

		for (int i = 0; i < title.length; i++) {
			if (AccData.startTime.equals(title[i])) {
				startPosition = i;
				break;
			}
		}

		for (int i = 1; i < records.length; i++) {
			String[] fields = getFields(records[i]);
			String start = fields[0];

			if (!xAxisValue.contains(fields[1])) {
				xAxisValue.add(fields[1]);
			}

			if(!yAxisValue.contains(fields[2])){
				yAxisValue.add(fields[2]);
			}

			if(!zAxisValue.contains(fields[3])){
				zAxisValue.add(fields[3]);
			}
			
			if(!difTime.contains(fields[4])){
				difTime.add(fields[4]);
			}
			
			if (!accelerateDataSet.containsKey(start)) {
				accelerateDataSet.put(start, buildAccDataSet());
				updateAccDataSet(start, fields);
			} else {
				updateAccDataSet(start, fields);
			}
		}

	}

	private String[] getRecords() {
		return rawData.split("\r\n");
	}

	private String[] getTitle(String[] records) {
		return getFields(records[0]);
	}

	private String[] getFields(String record) {
		return record.split("\t");
	}

	private Data buildAccDataSet() {
		AccData accelerateData = new AccData();

		for (int i = 0; i < title.length; i++) {
			if (i == startPosition) {
				continue;
			}

			List<String> fieldList = DataFactory.getList();
			accelerateData.put(title[i], fieldList);
		}

		return accelerateData;
	}

	private void updateAccDataSet(String start, String[] fields) {
		for (int i = 0; i < fields.length; i++) {
			if (i == startPosition) {
				continue;
			}
			accelerateDataSet.get(start).get(title[i]).add(fields[i]);
		}
	}
}
