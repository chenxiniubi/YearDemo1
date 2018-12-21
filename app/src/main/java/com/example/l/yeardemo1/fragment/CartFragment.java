package com.example.l.yeardemo1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.l.yeardemo1.R;
import com.example.l.yeardemo1.adapter.CarAdapter2;
import com.example.l.yeardemo1.bean.Result;
import com.example.l.yeardemo1.bean.Shop;
import com.example.l.yeardemo1.core.DataCall;
import com.example.l.yeardemo1.presenter.CdataPresenter;

import java.util.List;

public class CartFragment extends Fragment implements DataCall<List<Shop>>,CarAdapter2.TotalPriceListener{


    private View view;
    private ExpandableListView mListCart;
    /**
     * 全选
     */
    private CheckBox mCheckAll;
    /**
     * 价格：
     */
    private TextView mGoodsSumPrice;

    private CdataPresenter presenter = new CdataPresenter(this);
    private CarAdapter2 carAdapter2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_car, container, false);

        initView(view);

        mCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                carAdapter2.checkAll(isChecked);
            }
        });
        //适配器
        carAdapter2 = new CarAdapter2(getActivity());
        mListCart.setAdapter(carAdapter2);
        carAdapter2.setTotalPriceListener(this);//设置总价回调器
        mListCart.setGroupIndicator(null);

        //让其group不能被点击
        mListCart.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        presenter.requestData();

        return view;

    }

    private void initView(View view) {
        mListCart = view.findViewById(R.id.list_cart);
        mCheckAll = view.findViewById(R.id.check_all);
        mGoodsSumPrice = view.findViewById(R.id.goods_sum_price);
    }

    @Override
    public void success(List<Shop> data) {
        carAdapter2.addAll(data);
        //遍历所有group,将所有项设置成默认展开
        int groupCount = data.size();
        for (int i = 0; i < groupCount; i++) {
            mListCart.expandGroup(i);
        }
        carAdapter2.notifyDataSetChanged();
    }

    @Override
    public void fail(Result result) {
        Toast.makeText(getContext(), result.getCode() + "   " + result.getMsg(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void totalPrice(double totalPrice) {
        mGoodsSumPrice.setText(String.valueOf(totalPrice));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unBindCall();
    }
}
