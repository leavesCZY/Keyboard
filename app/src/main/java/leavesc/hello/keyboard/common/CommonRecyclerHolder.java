package leavesc.hello.keyboard.common;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 通用ViewHolder
 * Created by ZY on 2017/6/3.
 */
public class CommonRecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public interface onClickCommonListener {

        void onClick(int position);

        void onLongClick(int position);

    }

    private onClickCommonListener clickCommonListener;

    //用来存放View以减少findViewById的次数
    private SparseArray<View> viewSparseArray;

    CommonRecyclerHolder(View itemView) {
        super(itemView);
        viewSparseArray = new SparseArray<>();
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    void setClickCommonListener(onClickCommonListener clickCommonListener) {
        this.clickCommonListener = clickCommonListener;
    }

    @Override
    public void onClick(View view) {
        if (clickCommonListener != null) {
            clickCommonListener.onClick(getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (clickCommonListener != null) {
            clickCommonListener.onLongClick(getAdapterPosition());
        }
        return true;
    }

    /**
     * 根据 ID 来获取 View
     *
     * @param viewId viewID
     * @param <T>    泛型
     * @return 将结果强转为 View 或 View 的子类型
     */
    private <T extends View> T getView(@IdRes int viewId) {
        // 先从缓存中找，找到的话则直接返回
        // 如果找不到则findViewById，再把结果存入缓存中
        View view = viewSparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public CommonRecyclerHolder setText(@IdRes int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public CommonRecyclerHolder setImageResource(@IdRes int viewId, @DrawableRes int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    public CommonRecyclerHolder setImageResource(@IdRes int viewId, Bitmap bitmap) {
        ImageView imageView = getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public CommonRecyclerHolder setViewVisibility(@IdRes int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }

}
