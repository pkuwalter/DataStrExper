package com.ccnu.datastr.model;

import java.util.HashMap;
import java.util.List;

public class DataSet extends HashMap<String, Data> {

	private static final long serialVersionUID = -3409566572284996227L;
	private List<String> timeList;
	private List<String> tagList;

	public String getStartTime() {
		return timeList.get(0);
	}

	public String getEndTime() {
		return timeList.get(timeList.size() - 1);
	}

	public List<String> getTimeList() {
		return timeList;
	}

	public void setTimeList(List<String> timeList) {
		this.timeList = timeList;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

}
