package ink.chengcan.home.retrofit;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

import ink.chengcan.base.ui.BaseActivity;
import ink.chengcan.base.ui.recylerview.QuickAdapter;
import ink.chengcan.home.R;
import ink.chengcan.home.toolbar.ToolbarActivity;

import static android.widget.LinearLayout.VERTICAL;

/**
 * Demo class
 *
 * @author hp
 * @date 2019/10/19
 */
public class RetrofitActivity extends BaseActivity {
    @Override
    public int getLayoutResource() {
        return R.layout.home_activity_retrofit;
    }


}
