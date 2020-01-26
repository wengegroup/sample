package com.wenge.api.config;

/**
 * @author lzk
 * @description
 * @date 2019/7/15
 */
public class RequestConfig {
    //请求域名
    public static final String ONLINE_HOST = "http://wenhai.wengegroup.com/wenhaiFt-api";   // official
    public static final String ONLINE_HOST_test = "http://ft.wengetech.com:10001/wenhaiFt-api-test"; //test
    public static final String HOST = "http://192.168.10.193:8010/wenhaiFt-api";
    public static final String LOCAL_HOST = "http://localhost:8090";

    //用户使用code请求的access token
    public static final String ANALYSIS_ACCESS_TOKEN_INFO = "240b850930799b7389ef4ce5089e4018";

    public static final String SEARCH_ACCESS_TOKEN_INFO = "ace8d6fdd1aa178091c2577fe7210ae3";

    public static final String YANGSHI_ACCESS_TOKEN_INFO = "4b2f9c7f9eecc8384b79a08e75ce7957";

}
