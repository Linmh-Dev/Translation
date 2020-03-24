package com.example.myokhttp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/*
 var r = function(e) {
        var t = n.md5(navigator.appVersion)
          , r = "" + (new Date).getTime()
          , i = r + parseInt(10 * Math.random(), 10);
        return {

            ts: r,
            bv: t,
            salt: i,
            sign: n.md5("fanyideskweb" + e + i + "Nw(nmmbP%A-r6U3EUn]Aj")
        }
 */

public class Myhttp extends AsyncTask<String, Void, String> {
    public static String[] translationtext;

    @Override

    protected String doInBackground(String... strings) {
        String query = strings[0];
        Date date = new Date();

        long time = date.getTime();
        long salt = time + 10 * time;
        long ts = time;
        String User_Agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36";
        String sign = "";
        String bv = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(User_Agent.getBytes());
            bv = gethex(digest);
            String saltss = String.valueOf(salt);
            sign = "fanyideskweb" + query + saltss + "Nw(nmmbP%A-r6U3EUn]Aj";
            sign = gethex(md5.digest(sign.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("i", query)
                .add("client", "fanyideskweb")
                .add("from", "AUTO")
                .add("to", "AUTO")
                .add("smartresult", "dict")
                .add("doctype", "json")
                .add("version", "2.1")
                .add("keyfrom", "fanyi.web")
                .add("action", "FY_BY_REALTlME")
                .add("salt", String.valueOf(salt))
                .add("sign", sign)
                .add("ts", String.valueOf(ts))
                .add("bv", bv)
                .build();
        Request request = new Request.Builder().url("http://fanyi.youdao.com/translate_o?smartresult=dict&smartresult=rule")
                .post(requestBody)
                .header("Cookie", "OUTFOX_SEARCH_USER_ID=1474458966@10.169.0.102; JSESSIONID=aaaKVlv4kCidg5fNvLaex; OUTFOX_SEARCH_USER_ID_NCOO=1264177066.6226926; ___rl__test__cookies=1584852732402")
                .header("Referer", "http://fanyi.youdao.com/")
                .header("User-Agent", User_Agent)
                .build();
        Log.d("lin", ts + "\n" + bv + "\n" + salt + "\n" + sign);
        Response executea = null;
        try {
            executea = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            // Log.d("lin","exception");
        }
        String responsed = null;
        try {
            responsed = executea.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

         Log.d("lin", responsed);
       return soleString(responsed);
      //  return responsed;

    }

    @Override
    protected void onPostExecute(String s) {

       String content = s;
       translationtext=content.split(",");

    }


    private String gethex(byte[] bytes) {
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    private String soleString(String string) {
        //String string = "{\"translateResult\":[[{\"tgt\":\"实体\",\"src\":\"entity\"}]],\"errorCode\":0,\"type\":\"en2zh-CHS\",\"smartResult\":{\"entries\":[\"\",\"n. 实体；存在；本质\\r\\n\"],\"type\":1}}";
        char[] chars = string.toCharArray();
        int flag = 0;
        int end = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{' && flag == 0) {
                flag = i;
            }
            if (chars[i] == '}' && end == 0) {
                end = i;
            }
        }
        string = string.substring(flag+1, end).replace("\":\"", ",").replace("\",\"", ",").replace("\"","");
        // String replace = substring.replace("\":\"", ",").replace("\",\"", ",").replace("\"","");
       // String[] split = string.split(",");
       // System.out.println(split[1]+"---"+split[3]);
        return string;
    }

}



