package com.example.l.yeardemo1.presenter;

import com.example.l.yeardemo1.bean.Result;
import com.example.l.yeardemo1.core.DataCall;
import com.example.l.yeardemo1.model.HdataModel;

public class HdataPresenter extends BasePresenter {


//    private int page = 1;
//    private boolean isRefresh = true;


    public HdataPresenter(DataCall dataCall) {
        super(dataCall);
    }


    @Override
    protected Result getData(Object... args) {
        Result result = HdataModel.goodList();
        return result;
        //是否需要刷新
//        isRefresh = (Boolean) args[0];
        //刷新
//        if (isRefresh) {
//            page = 1;
//        } else {
//            //页数增加
//            page++;
//        }

    }

//    public boolean isResresh() {
//        return isRefresh;
//    }


}
