package com.kuloud.android.widget.countdown;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;


/**
 * @author kuloud
 */
public class CountDownImpl {
    private static final int MSG_TIME_COUNTING = 1989;

    private CountDownCallback mCallback;
    CharSequence mHint;
    CharSequence mFormat;

    int count;

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_TIME_COUNTING:
                    if (mCallback != null) {
                        mCallback.onCountDown(msg.arg1);
                    }
                    count = msg.arg1;
                    if (msg.arg1 > 0) {
                        Message m = obtainMessage(MSG_TIME_COUNTING);
                        m.arg1 = msg.arg1 - 1;
                        m.obj = msg.obj;
                        sendMessageDelayed(m, 1000L); // loop every 1s
                    }
                    break;
                default:
                    break;

            }
        }
    };

    public void countDown(int second) {
        if (mCallback == null) {
            throw new IllegalStateException("count down need setup a callback first");
        }
        count = second;
        onDestory();
        Message msg = mHandler.obtainMessage(MSG_TIME_COUNTING);
        msg.arg1 = second;
        msg.obj = mCallback;
        msg.sendToTarget();
    }

    public void setFormat(CharSequence hint, CharSequence format) {
        this.mHint = hint;
        if (TextUtils.isEmpty(format)) {
            format = "%d"; // default format
        }
        this.mFormat = format;
    }

    public void setCallback(CountDownCallback callback) {
        this.mCallback = callback;
    }

    public void onDestory() {
        mHandler.removeMessages(MSG_TIME_COUNTING, mCallback);
    }
}
