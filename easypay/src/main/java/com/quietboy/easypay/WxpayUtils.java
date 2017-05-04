package com.quietboy.easypay;


public class WxpayUtils {

    private static WxpayUtils utils;

    public static WxpayUtils newInstance() {
        if (utils == null) {
            utils = new WxpayUtils();
        }
        return utils;
    }

}
