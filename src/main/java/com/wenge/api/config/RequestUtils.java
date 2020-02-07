package com.wenge.api.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import wg.product.utils.AESEncrptUtils3;
import wg.product.utils.HttpUtilsV2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RequestUtils {

	public static JSONObject getRequestJson(String key, Object value) {
		JSONObject result = new JSONObject();
		result.put(key, value);
		return result;
	}

	public static JSONObject getAnalysisTokenAccesstokenJson() {
		JSONObject obj = RequestUtils.getRequestJson("access_token", RequestConfig.ANALYSIS_ACCESS_TOKEN_INFO);
		return obj;
	}

	public static String request(String encodeRules, String path, String requestMethod, String authorization) {
		String encontent = "";
		try {
			encontent = AESEncrptUtils3.encryptByAes(authorization, encodeRules);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Map<String, String> headers = new HashMap<String, String>();

		headers.put("accept", "*/*");
		headers.put("connection", "Keep-Alive");
		headers.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		headers.put("Authorization", encontent);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("a", "aaa");
		String bodys = "";
		try {
			HttpResponse response = HttpUtilsV2.doPost(RequestConfig.ONLINE_HOST, path, requestMethod, headers, querys,
					bodys);
			System.out.println(response);
			return EntityUtils.toString(response.getEntity(), "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}



}
