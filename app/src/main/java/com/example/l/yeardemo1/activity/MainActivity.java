package com.example.l.yeardemo1.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.l.yeardemo1.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 八维电商
     */
    private TextView mTxtView;
    /**
     * 开始体验
     */
    private Button mBtnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTxtView = findViewById(R.id.txt_view);
        mBtnStart = findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                //自身大小的1倍变为3倍自身大小的1倍变为3倍
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(mTxtView, "scaleX", 1f, 3f, 1f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(mTxtView, "scaleY", 1f, 3f, 1f);
                //透明度由1.0变为0.8
                ObjectAnimator alpha = ObjectAnimator.ofFloat(mTxtView, "alpha", 1, 0.8f);
                //文字验证X轴执行翻转
                ObjectAnimator rotationX = ObjectAnimator.ofFloat(mTxtView, "rotationX", new float[]{0f, -360f});
                AnimatorSet as = new AnimatorSet();
                //同时执行
                as.play(scaleX).with(scaleY);
                as.playSequentially(alpha, rotationX);
                as.setDuration(2000);
                as.start();

                //动画监听
                as.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        finish();
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        //动画结束之后  进行跳转
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                break;
        }
    }
}
