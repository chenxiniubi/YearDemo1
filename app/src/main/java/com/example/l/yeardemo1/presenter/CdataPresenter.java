package com.example.l.yeardemo1.presenter;

import com.example.l.yeardemo1.bean.Result;
import com.example.l.yeardemo1.core.DataCall;
import com.example.l.yeardemo1.model.CdataModel;

public class CdataPresenter extends BasePresenter{
    public CdataPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Result getData(Object... args) {
        Result result = CdataModel.goodsList();//调用网络请求获取数据
        return result;
    }
}
