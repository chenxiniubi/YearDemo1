package com.example.l.yeardemo1.model;

import com.example.l.yeardemo1.bean.Data;
import com.example.l.yeardemo1.bean.Result;
import com.example.l.yeardemo1.http.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class HdataModel {

    public static Result goodList() {

        String resultString = OkHttpUtils.get("http://www.zhaoapi.cn/product/getProducts?pscid=1");

        try {
            Gson gson = new Gson();
            Type type = new TypeToken<Result<List<Data>>>() {
            }.getType();
            Result result = gson.fromJson(resultString, type);
            return result;
        } catch (Exception e) {

        }
        Result result = new Result();
        result.setCode(-1);
        result.setMsg("数据解析异常");
        //        Log.e("ljt","==============================="+result);
        return result;
}
}
