package ink.chengcan.base.ui.recylerview;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 万能适配器
 *
 * @author chengcan
 * @date 2019/9/28
 */
public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.VH> {
    private List<T> mDatas;

    private OnItemClickListener onItemClickListener;

    public QuickAdapter(List<T> datas) {
        this.mDatas = datas;
    }

    public void updateData(List<T> data) {
        this.mDatas = data;
        notifyDataSetChanged();
    }

    /**
     * 添加新的Item
     *
     * @param position
     * @param data
     */
    public void insert(int position, T data) {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mDatas.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 删除数据
     *
     * @param position
     */
    public void delete(int position) {
        if (mDatas == null || mDatas.isEmpty()) {
            return;
        }
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 设置点击监听器
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public abstract int getLayoutId(int viewType);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return VH.get(parent, getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(final VH holder,final int position) {
        if (position == mDatas.size()) {
            return;
        }

        //③ 对RecyclerView的每一个itemView设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (onItemClickListener != null) {
//                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener != null) {
//                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, position);
                }
                //表示此事件已经消费，不会触发单击事件
                return true;
            }
        });

        convert(holder, mDatas.get(position), position);
    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract void convert(VH holder, T data, int position);

    public static class VH extends RecyclerView.ViewHolder {
        private SparseArray<View> mViews;
        private View mConvertView;

        public VH(View v) {
            super(v);
            mConvertView = v;
            mViews = new SparseArray<>();
        }

        public static VH get(ViewGroup parent, int layoutId) {
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(convertView);
        }

        public <T extends View> T getView(int id) {
            View v = mViews.get(id);
            if (v == null) {
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (T) v;
        }

        public void setText(int id, String value) {
            TextView view = getView(id);
            view.setText(value);
        }
    }

    /**
     * 监听器
     */
    public interface OnItemClickListener {

        /**
         * 单击
         *
         * @param view
         * @param position
         */
        void onItemClick(View view, int position);

        /**
         * 长按
         *
         * @param view
         * @param position
         */
        void onItemLongClick(View view, int position);
    }
}