package com.wenge.api.config;

import com.wenge.api.test.RequestUtils;
import net.sf.json.JSONObject;

/**
 * @author lzk
 * @description
 * @date 2019/7/15
 */
public class GetToken {
    public String getAnalysisToken(){
        String path = "/openAPI/search/getAccessToken";
        String method = "POST";
        // 示例   d0u4oE0n4iFu8911D39A0C_1579680932844
        String authen_origion_code = "";// + System.currentTimeMillis();
        System.out.println("【客户端认证信息】----" + authen_origion_code);
        // AES加密规则
        String result = RequestUtils.request("WH_Search_ECRule", path, method,authen_origion_code);
        System.out.println(result);
        JSONObject outObject2 = JSONObject.fromObject(result);
        String aToken = "";
        if(outObject2 != null){
            aToken = outObject2.get("access_token").toString();
        }
        return aToken;
    }
}
