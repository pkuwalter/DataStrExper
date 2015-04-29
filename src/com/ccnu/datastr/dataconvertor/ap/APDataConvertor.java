package com.ccnu.datastr.dataconvertor.ap;

import java.util.ArrayList;
import java.util.List;

import com.ccnu.datastr.dataconvertor.DataHelper;
import com.ccnu.datastr.model.DataSet;
import com.ccnu.datastr.model.ap.APData;
import com.ccnu.datastr.model.ap.APDataSet;
import com.ccnu.datastr.util.DataFactory;

public class APDataConvertor implements DataHelper {

	private String rawData;
	private APDataSet apDataSet;
	private String[] title;
	private int ssidPosition;

	@Override
	public void setRawData(String rawData) {
		this.rawData = rawData;
		doConvert();
	}

	@Override
	public DataSet getDataSet() {
		return apDataSet;
	}

	private void doConvert() {
		apDataSet = new APDataSet();
		List<String> timeList = new ArrayList<String>();
		List<String> tagList = new ArrayList<String>();
		List<String> tagTimeList = new ArrayList<String>();

		String[] records = getRecords();
		title = getTitle(records);

		for (int i = 0; i < title.length; i++) {
			if (APData.SSID.equals(title[i])) {
				ssidPosition = i;
				break;
			}
		}

		for (int i = 1; i < records.length; i++) {
			String[] fields = getFields(records[i]);
			String ssid = fields[1];

			if (!tagList.contains(fields[4])) {
				tagList.add(fields[4]);
				tagTimeList.add(fields[0]);
			}

			if (!timeList.contains(fields[0])) {
				timeList.add(fields[0]);
			}

			if (!apDataSet.containsKey(ssid)) {
				apDataSet.put(ssid, buildAPData());
				updateAPData(ssid, fields);
			} else {
				updateAPData(ssid, fields);
			}
		}

		apDataSet.setTimeList(timeList);
		apDataSet.setTagList(tagTimeList);
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

	private APData buildAPData() {
		APData apData = new APData();

		for (int i = 0; i < title.length; i++) {
			if (i == ssidPosition) {
				continue;
			}

			List<String> fieldList = DataFactory.getList();
			apData.put(title[i], fieldList);
		}

		return apData;
	}

	private void updateAPData(String ssid, String[] fields) {
		for (int i = 0; i < fields.length; i++) {
			if (i == ssidPosition) {
				continue;
			}

			apDataSet.get(ssid).get(title[i]).add(fields[i]);
		}
	}
}
