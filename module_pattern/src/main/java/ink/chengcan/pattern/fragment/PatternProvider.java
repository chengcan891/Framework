package ink.chengcan.pattern.fragment;

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
@Route(path = "/pattern/main", name = "pattern")
public class PatternProvider implements IMainProvider {
    @Override
    public Fragment getMainFragment() {
        return new MainPatternFragment();
    }

    @Override
    public void init(Context context) {

    }
}
