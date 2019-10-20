package ink.chengcan.hybrid.fragment;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import ink.chengcan.base.provider.IHybridProvider;
import ink.chengcan.base.provider.IMainProvider;
import ink.chengcan.base.provider.IViewProvider;

/**
 * Demo class
 *
 * @author hp
 * @date 2019/10/18
 */
@Route(path = "/hybrid/main", name = "hybrid")
public class HybridProvider implements IMainProvider {
    @Override
    public Fragment getMainFragment() {
        return new MainHybridFragment();
    }

    @Override
    public void init(Context context) {

    }
}
