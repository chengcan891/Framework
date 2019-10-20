package ink.chengcan.home.toolbar;

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

import static android.widget.LinearLayout.VERTICAL;

/**
 * Demo class
 *
 * @author hp
 * @date 2019/10/19
 */
public class ToolbarActivity extends BaseActivity {

    private static String[] data = new String[]{"Toolbar"};

    @Override
    public int getLayoutResource() {
        return R.layout.home_activity_toolbar;
    }

    @Override
    public void initView() {
        super.initView();
        initRecycleView();
    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        QuickAdapter<String> adapter = new QuickAdapter<String>(Arrays.asList(data)) {
            @Override
            public int getLayoutId(int viewType) {
                return android.R.layout.simple_list_item_1;
            }

            @Override
            public void convert(VH holder, String data, int position) {
                holder.setText(android.R.id.text1, data);
            }
        };
        adapter.setOnItemClickListener(new QuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(ToolbarActivity.this, ExtendToolbarActivity.class));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        recyclerView.setAdapter(adapter);
    }


}
