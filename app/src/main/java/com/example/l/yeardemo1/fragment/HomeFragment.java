package com.example.l.yeardemo1.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.l.yeardemo1.R;
import com.example.l.yeardemo1.adapter.HomeDataAdapter;
import com.example.l.yeardemo1.bean.Data;
import com.example.l.yeardemo1.bean.Result;
import com.example.l.yeardemo1.core.DataCall;
import com.example.l.yeardemo1.presenter.HdataPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class HomeFragment extends Fragment implements DataCall<List<Data>>, XRecyclerView.LoadingListener {

    private View view;
    private XRecyclerView mHomeXRecyclerView;
    //实例化p层
    private HdataPresenter mDataPresenter = new HdataPresenter(this);
    private HomeDataAdapter mHomeDataAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mHomeXRecyclerView = view.findViewById(R.id.home_x_recycler_view);
        //p层请求数据
        mDataPresenter.requestData();
        //适配器
        mHomeDataAdapter = new HomeDataAdapter(getContext());
        mHomeXRecyclerView.setAdapter(mHomeDataAdapter);
        //瀑布流布局管理器
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
                (2, StaggeredGridLayoutManager.VERTICAL);
        mHomeXRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        //必须调用刷新
        mHomeXRecyclerView.refresh();
        return view;
    }

    //view接口实现的成功方法
    @Override
    public void success(List<Data> data) {
        //必须调用
        mHomeXRecyclerView.refreshComplete();
        mHomeXRecyclerView.loadMoreComplete();

        mHomeDataAdapter.clearList();
        mHomeDataAdapter.addAll(data);

        //刷新适配器
        mHomeDataAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(Result result) {
        Toast.makeText(getContext(), result.getCode() + "  " + result.getMsg(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
