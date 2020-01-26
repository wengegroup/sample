package com.wenge.api.test;

import org.junit.Test;

public class TestAnalysisController {

	public static void main(String[] args) {
		final long timeInterval = 300000;
		Runnable runnable = new Runnable() {
			public void run() {
				while (true) {
					// ------- code for task to run
					testGetData();
					// ------- ends here
					try {
						Thread.sleep(timeInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	/**
	 * 查询信息列表
	 */
	public static void testGetData() {
		System.out.println("xxx");
		// 定义好请求的json格式
		String path = "/openAPI/search/remoteSearchInfosByConditions";
		String method = "POST";
		com.alibaba.fastjson.JSONObject obj = RequestUtils.getAnalysisTokenAccesstokenJson();
		com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
		int ctime = (int) (System.currentTimeMillis() / 1000);
		String startStr = RequestUtils.timeStamp2Date(String.valueOf(ctime - 300), null);
		String endStr = RequestUtils.timeStamp2Date(String.valueOf(ctime), null);
		// json.put("startTime","2020-01-24 01:00:00");
		// json.put("endTime","2020-01-25 00:00:00");
		json.put("startTime", startStr);
		json.put("endTime", endStr);
		String[] darr1 = new String[1];
		darr1[0] = "4";
		json.put("dataSource", darr1);
		String[] lnarr1 = new String[1];
		lnarr1[0] = "1";
		json.put("language", lnarr1);
		String[] kwarr1 = new String[1];
		kwarr1[0] = "1";
		json.put("keywordsPosion", kwarr1);
		// json.put("sortField",4);
		json.put("sortField", 1);
		json.put("sortWay", "desc");
		// json.put("api_access_token","509f48feba9458603a91cff9738bacff");
		json.put("pageNo", 1);
		json.put("pageSize", "10");
		String[] arr1 = new String[1];
		arr1[0] = "(确诊&病例)!(辟谣&旅行史&未出现)";
		// arr1[1] = "(中科闻歌)";
		json.put("searchFormula", arr1);
		// String condition = "{ \"startTime\":\"2020-01-01
		// 00:00:00\",\"endTime\":\"2020-01-23
		// 23:59:59\",\"api_access_token\":\"509f48feba9458603a91cff9738bacff\",\"pageNo\":1,\"pageSize\":10,\"sortField\":4,\"isExport\":\"true\",\"keywords\":\"[{\"not\":
		// [],\"must\": [],\"any\": [\"冠状病毒\", \"肺炎\", \"疫情\", \"卫健委\"]}]\"}";
		obj.put("data", json);
		System.out.println(obj);
		// AES加密规则
		String result = RequestUtils.request("WH_Search_ECRule", path, method, obj.toJSONString());
		System.out.println("result : " + result);
	}

	@Test
	public void getFansInfo() {
		// 定义好请求的json格式
		String path = "/openAPI/yangsi/getFansInfo";
		String method = "POST";
		com.alibaba.fastjson.JSONObject obj = RequestUtils.getYangshiTokenAccesstokenJson();
		String condition = "{" + "'startTime':'2019-12-09 08:47:59'," + "'endTime':'2019-12-12 08:59:59',"
				+ "'pageNo':1," + "'pageSize':10," + "'userId':'1092538373'" + "}";
		obj.put("data", condition);
		// AES加密规则
		String result = RequestUtils.request("WH_Yangsi_ECRule", path, method, obj.toJSONString());
		System.out.println("result is " + result);
	}

}
