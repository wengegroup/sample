package com.wenge.api.config;

public class GetToken {

    /**分析接口，获取token**/
    public void getAnalysisToken(){
        String path = "/openAPI/analysis/getAccessToken";
        String method = "POST";
        String authen_origion_code = "用户的user Code" + "_" + System.currentTimeMillis();
        String result = RequestUtils.request("WH_Analys_ECRule", path, method,authen_origion_code);
        System.out.println("result is"+result);

    }

    /**搜索接口，获取token**/
    public void getSearchToken(){
        String path = "/openAPI/search/getAccessToken";
        String method = "POST";
        String authen_origion_code = "用户的user Code" + "_" + System.currentTimeMillis();
        String result = RequestUtils.request("WH_Search_ECRule", path, method,authen_origion_code);
        System.out.println(result);
    }
}
