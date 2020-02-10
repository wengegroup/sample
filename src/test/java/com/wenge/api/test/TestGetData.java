package com.wenge.api.test;


import com.alibaba.fastjson.JSONObject;
import com.wenge.api.config.RequestUtils;

public class TestGetData {

	/**程序主入口，获取数据*/
	public static void main(String[] args) {
		testGetData();
	}

	/**
	 * 获取数据，在condition里面配置检索条件
	 */
	public static void testGetData() {
		// 定义好请求的json格式
		String path = "/openAPI/analysis/remoteSearchInfosByConditions";
		String method = "POST";
		JSONObject obj = null;//RequestUtils.getAnalysisTokenAccesstokenJson();
		//检索条件
		String condition = "{" +
				" \"startTime\":\"2019-10-01 00:00:00\"," +
				"\"endTime\":\"2019-10-18 23:59:59\"," +
				"\"pageNo\":1," +
				"\"pageSize\":10," +
				"'searchFormula':['(中国&经济&美国)']," +
				"\"sortField\":4," +
				"\"isExport\":\"true\"}";
		obj.put("data", condition);
		String result = RequestUtils.request("WH_Search_ECRule", path, method, obj.toJSONString());
		System.out.println(result);
	}

}
