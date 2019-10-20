package ink.chengcan.weixin.fragment;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import ink.chengcan.base.provider.IMainProvider;

/**
 * Demo class
 *
 * @author hp
 * @date 2019/10/18
 */
@Route(path = "/weixin/main", name = "weixin")
public class WeixinProvider implements IMainProvider {
    @Override
    public Fragment getMainFragment() {
        return new MainWeixinFragment();
    }

    @Override
    public void init(Context context) {

    }
}
