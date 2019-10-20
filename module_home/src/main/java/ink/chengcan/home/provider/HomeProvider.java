package ink.chengcan.home.provider;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import ink.chengcan.base.provider.IHomeProvider;
import ink.chengcan.base.provider.IMainProvider;
import ink.chengcan.home.fragment.MainHomeFragment;

/**
 * Demo class
 *
 * @author hp
 * @date 2019/10/18
 */
@Route(path = "/home/main",name = "home")
public class HomeProvider implements IMainProvider {
    @Override
    public Fragment getMainFragment() {
        return new MainHomeFragment();
    }

    @Override
    public void init(Context context) {

    }
}
