package com.example.l.yeardemo1.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.l.yeardemo1.R;
import com.example.l.yeardemo1.fragment.CartFragment;
import com.example.l.yeardemo1.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mHomeFragment;
    /**
     * 首页
     */
    private TextView mTxtHome;
    /**
     * 购物车
     */
    private TextView mTxtCart;
    private FragmentManager mManager;
    private HomeFragment homeFragment;
    private CartFragment cartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        mManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        cartFragment = new CartFragment();
        //开启事务
        FragmentTransaction transaction = mManager.beginTransaction();
        // 添加和默认展示Fragment
        transaction.add(R.id.home_fragment, homeFragment); // 添加首页的Fragment
        transaction.add(R.id.home_fragment, cartFragment); // 添加购物车Fragment
        transaction.hide(cartFragment); // 默认显示首页，隐藏掉购物车
        transaction.commit(); // 最后提交
    }

    private void initView() {

        mHomeFragment = findViewById(R.id.home_fragment);

        mTxtHome = findViewById(R.id.txt_home);
        mTxtHome.setOnClickListener(this);

        mTxtCart = findViewById(R.id.txt_cart);
        mTxtCart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_home:
                mManager.beginTransaction().show(homeFragment).hide(cartFragment).commit();
                mTxtHome.setBackgroundResource(R.drawable.bg_selected);
                mTxtCart.setBackgroundResource(R.drawable.bg_unselected);
                break;
            case R.id.txt_cart:
                mManager.beginTransaction().show(cartFragment).hide(homeFragment).commit();
                mTxtHome.setBackgroundResource(R.drawable.bg_unselected);
                mTxtCart.setBackgroundResource(R.drawable.bg_selected);
                break;
        }
    }
}
