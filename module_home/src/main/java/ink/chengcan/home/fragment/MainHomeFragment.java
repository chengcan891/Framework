package ink.chengcan.home.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

import ink.chengcan.base.ui.BaseFragment;
import ink.chengcan.base.ui.recylerview.QuickAdapter;
import ink.chengcan.home.R;
import ink.chengcan.home.retrofit.RetrofitActivity;
import ink.chengcan.home.toolbar.ToolbarActivity;

import static android.widget.LinearLayout.VERTICAL;

/**
 * Demo class
 *
 * @author chengcan
 * @date 2019/10/18
 */
public class MainHomeFragment extends BaseFragment {

    private static String[] data = new String[]{"Toolbar","Android"};

    @Override
    public void initView() {
        super.initView();
        initRecycleView();
    }

    private void initRecycleView() {
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
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
                        startActivity(new Intent(getContext(), ToolbarActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), RetrofitActivity.class));
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

    @Override
    public int getLayoutResource() {
        return R.layout.home_fragment_home;
    }
}
