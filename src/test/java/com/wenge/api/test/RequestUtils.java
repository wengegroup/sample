package com.wenge.api.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;
import com.wenge.api.config.GetToken;
import com.wenge.api.config.RequestConfig;
import wg.product.utils.AESEncrptUtils3;
import wg.product.utils.HttpUtilsV2;

/**
 * @author lzk
 * @description
 * @date 2019/7/15
 */
public class RequestUtils {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdfms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public static JSONObject getRequestJson(Map<String, Object> data) {
		return new JSONObject(data);
	}

	public static JSONObject getRequestJson(String key, Object value) {
		JSONObject result = new JSONObject();
		result.put(key, value);
		return result;
	}

	public static JSONObject getAnalysisTokenAccesstokenJson() {
		GetToken gToken = new GetToken();
		JSONObject obj = RequestUtils.getRequestJson("access_token", gToken.getAnalysisToken());
		return obj;
	}

	public static JSONObject getYangshiTokenAccesstokenJson() {
		JSONObject obj = RequestUtils.getRequestJson("access_token", RequestConfig.YANGSHI_ACCESS_TOKEN_INFO);
		return obj;
	}

	/**
	 * 统一请求方法
	 * 
	 * @param path
	 * @param requestMethod
	 * @param authorization
	 */
	public static String request(String encodeRules, String path, String requestMethod, String authorization) {
		// AES加密规则
		// String encodeRules = "";
		String encontent = "";
		try {
			encontent = AESEncrptUtils3.encryptByAes(authorization, encodeRules);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("【加密后的客户端Authorization认证信息】----" + encontent);

		Map<String, String> headers = new HashMap<String, String>();

		headers.put("accept", "*/*");
		headers.put("connection", "Keep-Alive");
		headers.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		System.out.println("cccc  额鹅鹅鹅");
		headers.put("Authorization", encontent);
		// headers.put("Content-Type", "application/json; charset=UTF-8");
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

	/**
	 * 时间戳转换成日期格式字符串
	 * 
	 * @param seconds
	 *            精确到秒的字符串
	 * @param format
	 * @return
	 */
	public static String timeStamp2Date(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty())
			format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}

	/*
	 * 由于Java是基于Unicode编码的，因此，一个汉字的长度为1，而不是2。
	 * 但有时需要以字节单位获得字符串的长度。例如，“123abc长城”按字节长度计算是10，而按Unicode计算长度是8。
	 * 为了获得10，需要从头扫描根据字符的Ascii来获得具体的长度。如果是标准的字符，Ascii的范围是0至255，如果是汉字或其他全角字符，
	 * Ascii会大于255。 因此，可以编写如下的方法来获得以字节为单位的字符串长度。
	 */
	public static int getWordCount(String s) {
		int length = 0;
		for (int i = 0; i < s.length(); i++) {
			int ascii = Character.codePointAt(s, i);
			if (ascii >= 0 && ascii <= 255)
				length++;
			else
				length += 2;
		}
		return length;
	}

}
