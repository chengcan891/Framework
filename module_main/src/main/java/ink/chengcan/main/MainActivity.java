package ink.chengcan.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import ink.chengcan.base.MainChannel;
import ink.chengcan.base.provider.IMainProvider;


public class MainActivity extends AppCompatActivity {

    @Autowired(name = "/home/main")
    IMainProvider homeProvider;

    @Autowired(name = "/pattern/main")
    IMainProvider patternProvider;

    @Autowired(name = "/hybrid/main")
    IMainProvider hybridProvider;

    @Autowired(name = "/weixin/main")
    IMainProvider weixinProvider;

    private Fragment homeFragment;
    private Fragment patternFragment;
    private Fragment hybridFragment;
    private Fragment weixinFragment;
    private Fragment mCurrFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.navigation_home) {
                switchContent(mCurrFragment, homeFragment, MainChannel.HOME.name);
                mCurrFragment = homeFragment;
                return true;
            } else if (i == R.id.navigation_hybrid) {
                switchContent(mCurrFragment, hybridFragment, MainChannel.VIEW.name);
                mCurrFragment = hybridFragment;
                return true;
            } else if (i == R.id.navigation_framework) {
                switchContent(mCurrFragment, patternFragment, MainChannel.VIEW.name);
                mCurrFragment = patternFragment;
                return true;
            } else if (i == R.id.navigation_weixin) {//                    mTextMessage.setText(R.string.title_notifications);
                switchContent(mCurrFragment, weixinFragment, MainChannel.VIEW.name);
                mCurrFragment = weixinFragment;
                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ARouter.getInstance().inject(this);

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (homeProvider != null) {
            homeFragment = homeProvider.getMainFragment();
        }

        if (hybridProvider != null) {
            hybridFragment = hybridProvider.getMainFragment();
        }

        if (patternProvider != null) {
            patternFragment = patternProvider.getMainFragment();
        }
        if (weixinProvider != null) {
            weixinFragment = weixinProvider.getMainFragment();
        }


        mCurrFragment = homeFragment;
        if (homeFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, homeFragment, MainChannel.HOME.name).commit();
        }
    }

    public void switchContent(Fragment from, Fragment to, String tag) {
        if (from == null || to == null) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            transaction.hide(from).add(R.id.frame_content, to, tag).commit();
        } else {
            transaction.hide(from).show(to).commit();
        }
    }

}
