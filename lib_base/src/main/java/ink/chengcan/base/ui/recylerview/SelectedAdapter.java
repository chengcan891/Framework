package ink.chengcan.base.ui.recylerview;

import android.util.SparseBooleanArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 可选择的万能适配器
 *
 * @author chengcan
 * @date 2019/9/28
 */
public class SelectedAdapter extends RecyclerView.Adapter<QuickAdapter.VH> {
    private RecyclerView.Adapter<QuickAdapter.VH> mAdapter;

    private SparseBooleanArray selectList;

    /**
     * SelectedAdapter不是最外层的adapter,使用SelectedAdapter是没有办法更新界面的，所以这里需要指定最外层的adpter,用来更新界面
     */
    private RecyclerView.Adapter<QuickAdapter.VH> notifyAdapter;

    public SelectedAdapter(RecyclerView.Adapter<QuickAdapter.VH> adapter) {
        this.mAdapter = adapter;
        this.selectList = new SparseBooleanArray();
    }

    public void setNotifyAdapter(RecyclerView.Adapter<QuickAdapter.VH> notifyAdapter) {
        this.notifyAdapter = notifyAdapter;
    }

    @NonNull
    @Override
    public QuickAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull QuickAdapter.VH holder, int position) {
        mAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount();
    }

    public boolean isSelected(int position) {
        return selectList.get(position);
    }

    public void toggleItem(int position) {
        selectList.put(position, !selectList.get(position));
        if (notifyAdapter != null) {
            notifyAdapter.notifyItemChanged(position);
        } else {
            mAdapter.notifyItemChanged(position);
        }
    }

    public void notifyItemChanged(int position, boolean selected) {
        selectList.put(position, selected);
        if (notifyAdapter != null) {
            notifyAdapter.notifyItemChanged(position);
        } else {
            mAdapter.notifyItemChanged(position);
        }
    }

    /**
     * 选择所有
     */
    public void selectAll() {
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            selectList.put(i, true);
        }
        if (notifyAdapter != null) {
            notifyAdapter.notifyDataSetChanged();
        } else {
            mAdapter.notifyDataSetChanged();
        }

    }

    /**
     * 取消选择所有
     */
    public void cancelSelect() {
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            selectList.put(i, false);
        }
        if (notifyAdapter != null) {
            notifyAdapter.notifyDataSetChanged();
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    public int getSelectedNum() {
        int count = 0;
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            if (selectList.get(i)) {
                count++;
            }
        }
        return count;
    }

    public boolean isSelectedAll() {
        return mAdapter.getItemCount() == getSelectedNum();
    }

    public void toggle() {
        if (isSelectedAll()) {
            cancelSelect();
        } else {
            selectAll();
        }
    }


}