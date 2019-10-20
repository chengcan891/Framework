package ink.chengcan.base.provider;


import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Demo class
 *
 * @author chengcan
 * @date 2019/10/18
 */
public interface IHybridProvider extends IProvider {
    Fragment getMainHybridFragment();
}
