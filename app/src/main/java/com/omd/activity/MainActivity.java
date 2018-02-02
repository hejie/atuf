package com.omd.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.omd.R;
import com.omd.adapter.TabPagerAdapter;
import com.omd.base.BaseActivity;
import com.omd.views.tab.NavigationController;
import com.omd.views.tab.PageNavigationView;
import com.omd.views.tab.item.BaseTabItem;
import com.omd.views.tab.item.NormalItemView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_horizontal);

        PageNavigationView tab = (PageNavigationView) findViewById(R.id.tab);

        NavigationController navigationController = tab.custom()
                .addItem(newItem(R.drawable.ic_restore_gray_24dp, R.drawable.ic_restore_teal_24dp, "首页"))
                .addItem(newItem(R.drawable.ic_favorite_gray_24dp, R.drawable.ic_favorite_teal_24dp, "收藏"))
                .addItem(newItem(R.drawable.ic_nearby_gray_24dp, R.drawable.ic_nearby_teal_24dp, "我的"))
                .build();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), navigationController.getItemCount()));

        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(viewPager);

        //设置消息数
        navigationController.setMessageNumber(1, 8);

        //设置显示小圆点
        navigationController.setHasMessage(0, true);
    }

    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(Color.GRAY);
        normalItemView.setTextCheckedColor(0xFF009688);
        return normalItemView;
    }
}
