package com.czy.keyboard.common;


import android.content.Context;
import android.support.annotation.LayoutRes;

import com.czy.keyboard.R;

import java.util.List;

/**
 * 作者：叶应是叶
 * 时间：2017/8/27 14:17
 * 描述：
 */
public class MessageAdapter extends CommonRecyclerAdapter<Message> {

    public MessageAdapter(Context context, List<Message> messageList, @LayoutRes int layoutId) {
        super(context, messageList, layoutId);
    }

    @Override
    protected void bindData(CommonRecyclerHolder holder, Message message) {
        holder.setText(R.id.tv_message, message.getMessage());
    }
}
