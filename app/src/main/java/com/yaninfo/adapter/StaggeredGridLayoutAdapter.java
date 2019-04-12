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

import java.util.ArrayList;
import java.util.List;

/**
 * 瀑布流适配器
 */
public class StaggeredGridLayoutAdapter extends RecyclerView.Adapter<StaggeredGridLayoutAdapter.ViewHolder>{

    private static final String TAG = "FruitAdapter";
    private Context mContext;
    private List<Girl> mFruitList;

    /**
     * 定义模拟的高度
     */
    private List<Integer> mHeight;

    /**
     * 定义ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            fruitImage = view.findViewById(R.id.fruit_image);
            fruitName = view.findViewById(R.id.fruit_name);
        }
    }

    public StaggeredGridLayoutAdapter(List<Girl> fruitList) {
        this.mFruitList = fruitList;

        // 随机生成高度
        mHeight = new ArrayList<>();
        for ( int i = 0;  i < mFruitList.size(); i++) {
             mHeight.add((int) (100 + Math.random()*500));
        }
    }

    /**
     * 创建ViewHolder实例
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * 对RecycleView子项数据赋值
     * 设置动态三个高度
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Girl girl = mFruitList.get(position);
        holder.fruitName.setText(girl.getName());
        //Glide可以防止图片内存泄露
        Glide.with(mContext).load(girl.getGirlId()).into(holder.fruitImage);

        // 动态高度
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeight.get(position);
        holder.itemView.setLayoutParams(layoutParams);

        }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

}
