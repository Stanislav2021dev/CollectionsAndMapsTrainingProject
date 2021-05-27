package com.example.colmaps;

import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class mFr_1 extends Fragment1 {

    public static class MyHandler extends Handler {

        private final WeakReference<mFr_1> mFragment;

        public MyHandler(mFr_1 fragment)
        {
            mFragment=new WeakReference<mFr_1>(fragment);
        }

        public void handleMsg (Message msg){
            mFr_1 fragment = mFragment.get();
            if (fragment!=null)
            {

            }
        }

        @Override
        public void publish(LogRecord record) {

        }

        @Override
        public void flush() {

        }

        @Override
        public void close() throws SecurityException {

        }
    }
    private MyHandler mHandler= new MyHandler(this);
}
