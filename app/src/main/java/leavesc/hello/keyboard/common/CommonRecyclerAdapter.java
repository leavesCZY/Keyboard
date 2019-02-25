package leavesc.hello.keyboard.common;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 通用RecyclerView Adapter
 * Created by ZY on 2017/6/3.
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<CommonRecyclerHolder> {

    //多布局支持
    public interface MultiTypeSupport<T> {

        int getLayoutId(T item, int position);

    }

    private MultiTypeSupport<T> multiTypeSupport;

    private LayoutInflater layoutInflater;

    private List<T> dataList;

    @LayoutRes
    private int layoutId;

    private CommonRecyclerHolder.onClickCommonListener clickCommonListener;

    /**
     * 私有构造函数
     *
     * @param context  上下文
     * @param dataList 数据集合
     */
    private CommonRecyclerAdapter(Context context, List<T> dataList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }

    /**
     * 适用于：列表所有的子项都使用相同的布局文件，且不需要监听点击事件
     *
     * @param context  上下文
     * @param dataList 数据集合
     * @param layoutId 布局文件ID
     */
    protected CommonRecyclerAdapter(Context context, List<T> dataList, @LayoutRes int layoutId) {
        this(context, dataList);
        this.layoutId = layoutId;
    }

    /**
     * 适用于：列表的子项使用不同的布局文件，且不需要监听点击事件
     *
     * @param context          上下文
     * @param dataList         数据集合
     * @param multiTypeSupport 支持多个布局文件
     */
    protected CommonRecyclerAdapter(Context context, List<T> dataList, MultiTypeSupport<T> multiTypeSupport) {
        this(context, dataList);
        this.multiTypeSupport = multiTypeSupport;
    }

    /**
     * 适用于：列表所有的子项都使用相同的布局文件，且需要监听点击事件
     *
     * @param context             上下文
     * @param dataList            数据集合
     * @param layoutId            布局文件ID
     * @param clickCommonListener 点击事件监听
     */
    protected CommonRecyclerAdapter(Context context, List<T> dataList, @LayoutRes int layoutId,
                                    CommonRecyclerHolder.onClickCommonListener clickCommonListener) {
        this(context, dataList, layoutId);
        this.clickCommonListener = clickCommonListener;
    }

    /**
     * 适用于：列表的子项使用不同的布局文件，且需要监听点击事件
     *
     * @param context             上下文
     * @param dataList            数据集合
     * @param multiTypeSupport    支持多个布局文件
     * @param clickCommonListener 点击事件监听
     */
    protected CommonRecyclerAdapter(Context context, List<T> dataList, MultiTypeSupport<T> multiTypeSupport,
                                    CommonRecyclerHolder.onClickCommonListener clickCommonListener) {
        this(context, dataList, multiTypeSupport);
        this.clickCommonListener = clickCommonListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (multiTypeSupport != null) {
            return multiTypeSupport.getLayoutId(dataList.get(position), position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public CommonRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (multiTypeSupport != null) {
            layoutId = viewType;
        }
        View view = layoutInflater.inflate(layoutId, parent, false);
        return new CommonRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(CommonRecyclerHolder holder, int position) {
        bindData(holder, dataList.get(position));
        holder.setClickCommonListener(clickCommonListener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    protected abstract void bindData(CommonRecyclerHolder holder, T data);

}

