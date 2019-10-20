package ink.chengcan.base.ui.recylerview;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 添加头角的万能适配器
 * 注意如果要使用getItemCount获取item的数量是包换头和脚的，所以和其他adapter使用的时候，如果其他的adpter使用了getItemCount方法，会产生错误
 * 所以做好把HeaderFooterAdapter放到最外层
 *
 * @author chengcan
 * @date 2019/9/28
 */
public class HeaderFooterAdapter extends RecyclerView.Adapter<QuickAdapter.VH> {
    private RecyclerView.Adapter<QuickAdapter.VH> mAdapter;

    enum ITEM_TYPE {
        HEADER(1),
        FOOTER(2),
        NORMAL(3);

        private int value;

        ITEM_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    private View mFooterView;
    private View mHeaderView;

    public HeaderFooterAdapter(RecyclerView.Adapter<QuickAdapter.VH> adapter) {
        this.mAdapter = adapter;
    }

    public void addFooterView(View view) {
        this.mFooterView = view;
    }

    public void addHeaderView(View view) {
        this.mHeaderView = view;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null) {
            return ITEM_TYPE.NORMAL.getValue();
        }

        if (mHeaderView == null) {
            if (position == mAdapter.getItemCount()) {
                return ITEM_TYPE.FOOTER.getValue();
            } else {
                return ITEM_TYPE.NORMAL.getValue();
            }
        }

        if (mFooterView == null) {
            if (position == 0) {
                return ITEM_TYPE.HEADER.getValue();
            } else {
                return ITEM_TYPE.NORMAL.getValue();
            }
        }

        //都不是空
        if (position == 0) {
            return ITEM_TYPE.HEADER.getValue();
        } else if (position == mAdapter.getItemCount() + 1) {
            return ITEM_TYPE.FOOTER.getValue();
        } else {
            return ITEM_TYPE.NORMAL.getValue();
        }
    }

    @Override
    public QuickAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.HEADER.getValue()) {
            return new QuickAdapter.VH(mHeaderView) {
            };
        } else if (viewType == ITEM_TYPE.FOOTER.getValue()) {
            return new QuickAdapter.VH(mFooterView) {
            };
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(QuickAdapter.VH holder, int position) {
        if (mHeaderView == null && mFooterView == null) {
            mAdapter.onBindViewHolder((QuickAdapter.VH) holder, position);
            return;
        }

        if (mHeaderView == null) {
            if (position == mAdapter.getItemCount()) {
                return;
            }
            mAdapter.onBindViewHolder(holder, position);
            return;
        }

        if (mFooterView == null) {
            if (position == 0 || position == mAdapter.getItemCount() + 1) {
                return;
            }
            mAdapter.onBindViewHolder(holder, position - 1);
            return;
        }


        if (position == 0 || position == mAdapter.getItemCount() + 1) {
            return;
        }
        mAdapter.onBindViewHolder(holder, position - 1);
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null) {
            return mAdapter.getItemCount();
        }

        if (mHeaderView == null || mFooterView == null) {
            return mAdapter.getItemCount() + 1;
        }
        return mAdapter.getItemCount() + 2;
    }

}