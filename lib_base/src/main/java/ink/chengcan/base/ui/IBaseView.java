package ink.chengcan.base.ui;

import android.content.Context;

import androidx.annotation.LayoutRes;

/**
 * Demo class
 *
 * @author hp
 * @date 2019/10/19
 */
public interface IBaseView {

    @LayoutRes
    int getLayoutResource();

    void initView();

    void initListener();

    void initData();

    void finishActivity();

}
