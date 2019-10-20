package ink.chengcan.pattern.fragment;

import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

import ink.chengcan.base.ui.BaseFragment;
import ink.chengcan.base.ui.recylerview.QuickAdapter;
import ink.chengcan.pattern.R;


import static android.widget.LinearLayout.VERTICAL;

/**
 * Demo class
 *
 * @author chengcan
 * @date 2019/10/18
 */
public class MainPatternFragment extends BaseFragment {

    private static String[] data = new String[]{"mvvm"};

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

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.pattern_fragment_main;
    }
}
