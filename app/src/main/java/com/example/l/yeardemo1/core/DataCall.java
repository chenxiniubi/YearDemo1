package com.example.l.yeardemo1.core;


import com.example.l.yeardemo1.bean.Result;

public interface DataCall<T> {

    void success(T data);

    void fail(Result result);


}
