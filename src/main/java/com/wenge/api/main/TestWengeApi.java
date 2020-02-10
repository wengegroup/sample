package com.wenge.api.main;


import com.alibaba.fastjson.JSONObject;
import com.wenge.api.config.RequestUtils;

public class TestWengeApi {

	/**程序主入口，调用闻海api*/
	public static void main(String[] args) {
		testGetDataBySearch();
		testGetDataByAnalysis();
	}

	/**
	 * 检索接口：获取数据，在condition里面配置检索条件
	 */
	public static void testGetDataBySearch() {
		// 定义好请求的json格式
		String path = "/openAPI/search/remoteSearchInfosByConditions";
		String method = "POST";
		JSONObject obj = RequestUtils.getSearchTokenAccessTokenJson();
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

	/**
	 * 分析接口：1. 信息列表组件，在condition里面配置检索条件
	 */
	public static void testGetDataByAnalysis() {
		// 定义好请求的json格式
		String path = "/openAPI/analysis/remoteSearchInfosByConditions";
		String method = "POST";
		JSONObject obj = RequestUtils.getAnalysisTokenAccessTokenJson();
		//检索条件
		String condition = "{" +
				" \"startTime\":\"2019-10-01 00:00:00\"," +
				"\"endTime\":\"2019-10-18 23:59:59\"," +
				"'api_access_token':'用户在闻海上配置的api access token',"+
				"\"pageNo\":1," +
				"\"pageSize\":10," +
				"}";
		obj.put("data", condition);
		String result = RequestUtils.request("WH_Analys_ECRule", path, method, obj.toJSONString());
		System.out.println(result);
	}

}
