package ink.chengcan.home.toolbar;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

import ink.chengcan.base.ui.BaseActivity;
import ink.chengcan.base.ui.recylerview.QuickAdapter;
import ink.chengcan.home.R;

import static android.widget.LinearLayout.VERTICAL;

/**
 * toolbar可以变化
 *
 * @author hp
 * @date 2019/10/19
 */
public class ExtendToolbarActivity extends BaseActivity {

    @Override
    public int getLayoutResource() {
        return R.layout.home_activity_extend_toolbar;
    }

    @Override
    public void initView() {
        super.initView();
        Toolbar toolbar = findViewById(R.id.toolbar1);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
//        toolbar.setTitle("Extend");
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        toolbar.setLongClickable(false);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

}
