package com.yaninfo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.yaninfo.entity.Girl;
import com.yaninfo.R;
import java.util.List;

/**
 * 女孩适配器
 */
public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.MyViewHolder>{

    private static final String TAG = "FruitAdapter";
    private Context mContext;
    private List<Girl> mFruitList;

    /**
     * 定义ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            fruitImage = view.findViewById(R.id.fruit_image);
            fruitName = view.findViewById(R.id.fruit_name);
        }
    }

    public GirlAdapter(List<Girl> fruitList) {
        this.mFruitList = fruitList;
    }

    /**
     * 创建ViewHolder实例
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * 对RecycleView子项数据赋值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Girl girl = mFruitList.get(position);
        holder.fruitName.setText(girl.getName());
        // Glide可以防止图片内存泄露
        Glide.with(mContext).load(girl.getGirlId()).into(holder.fruitImage);

        // List监听
        if (mOnItemClickListener != null) {

        // 短按监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 实时更新position
                int layoutPosition = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(holder.itemView, layoutPosition);
            }
        });

        // 长按监听
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                // 实时更新position
                int layoutPosition = holder.getLayoutPosition();
                mOnItemClickListener.onItemLongClick(holder.itemView, layoutPosition);
                return false;
            }
        });

        }
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    /**
     * 添加女孩
     * @param position
     */
    public void addGirl(int position) {
            mFruitList.add(position, new Girl("Girl", R.drawable.meizi));
            notifyItemInserted(position);
    }

    /**
     * 删除女孩
     * @param position
     */
    public void deleteGirl(int position) {
        if (mFruitList.size() >= 0 ){
            mFruitList.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * 短按长按 监听接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private GirlAdapter.OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener (GirlAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
