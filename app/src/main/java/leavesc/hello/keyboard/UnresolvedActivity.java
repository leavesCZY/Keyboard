package leavesc.hello.keyboard;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import leavesc.hello.keyboard.common.Message;
import leavesc.hello.keyboard.common.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class UnresolvedActivity extends AppCompatActivity {

    private EditText et_inputMessage;

    private LinearLayout ll_rootEmojiPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(leavesc.hello.keyboard.R.layout.activity_unresolved);
        initView();
    }

    private void initView() {
        RecyclerView rv_messageList = (RecyclerView) findViewById(leavesc.hello.keyboard.R.id.rv_messageList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rv_messageList.setLayoutManager(linearLayoutManager);
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message("1"));
        messageList.add(new Message("2"));
        messageList.add(new Message("3"));
        messageList.add(new Message("4"));
        messageList.add(new Message("5"));
        messageList.add(new Message("6"));
        messageList.add(new Message("7"));
        messageList.add(new Message("8"));
        messageList.add(new Message("9"));
        messageList.add(new Message("10"));
        messageList.add(new Message("11"));
        messageList.add(new Message("12"));
        messageList.add(new Message("13"));
        messageList.add(new Message("14"));
        messageList.add(new Message("15"));
        messageList.add(new Message("16"));
        messageList.add(new Message("17"));
        messageList.add(new Message("18"));
        messageList.add(new Message("19"));
        messageList.add(new Message("20"));
        MessageAdapter messageAdapter = new MessageAdapter(this, messageList, leavesc.hello.keyboard.R.layout.item_message);
        rv_messageList.setAdapter(messageAdapter);

        et_inputMessage = (EditText) findViewById(leavesc.hello.keyboard.R.id.et_inputMessage);
        ImageView iv_more = (ImageView) findViewById(leavesc.hello.keyboard.R.id.iv_more);
        ll_rootEmojiPanel = (LinearLayout) findViewById(leavesc.hello.keyboard.R.id.ll_rootEmojiPanel);
        iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ll_rootEmojiPanel.getVisibility() == View.VISIBLE) {
                    showKeyboard();
                } else {
                    hideKeyboard();
                    ll_rootEmojiPanel.setVisibility(View.VISIBLE);
                }
            }
        });
        et_inputMessage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    ll_rootEmojiPanel.setVisibility(View.GONE);
                }
            }
        });
        rv_messageList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    hideKeyboard();
                    ll_rootEmojiPanel.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

    private void showKeyboard() {
        et_inputMessage.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) et_inputMessage.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(et_inputMessage, 0);
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        et_inputMessage.clearFocus();
        inputMethodManager.hideSoftInputFromWindow(et_inputMessage.getWindowToken(), 0);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_UP && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//            if (ll_rootEmojiPanel.getVisibility() == View.VISIBLE) {
//                ll_rootEmojiPanel.setVisibility(View.GONE);
//                return true;
//            }
//        }
        return super.dispatchKeyEvent(event);
    }

}
