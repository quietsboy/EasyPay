package com.quietboy.easypay;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

public class AlipayUtils {

    private static AlipayUtils utils;
    private Activity context;
    private final int SDK_PAY_FLAG = 1;
    private PayListener payListener;

    /**
     * 支付成功后返回的字段分别为：resultStatus（状态码）、result、memo
     *
     * @param context
     */

    public AlipayUtils(Activity context) {
        this.context = context;
    }

    public static AlipayUtils newInstance(Activity context) {
        if (utils == null) {
            utils = new AlipayUtils(context);
        }
        return utils;
    }

    public AlipayUtils pay(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(context);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
        return this;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    Map<String, String> payResult = (Map<String, String>) msg.obj;
                    Toast.makeText(context, payResult.get("result"), Toast.LENGTH_SHORT).show();
                    if (payListener != null) {
                        if (TextUtils.equals("9000", payResult.get("resultStatus"))) {
                            payListener.onSuccess();
                        } else if (TextUtils.equals("9000", payResult.get("resultStatus"))) {
                            payListener.onCancel();
                        } else {
                            payListener.onFailure(payResult.get("result"));
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    public interface PayListener {

        void onSuccess();

        void onCancel();

        void onFailure(String message);

    }

    public void setPayListener(PayListener payListener) {
        this.payListener = payListener;
    }

}
