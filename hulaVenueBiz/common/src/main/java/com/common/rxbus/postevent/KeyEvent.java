package com.common.rxbus.postevent;

/**
 * @desc:         用于RxBus传递标志量
 * @author:       Leo
 * @date:         2017/1/6
 */
public class KeyEvent
{
    private Object value;
    private String key;

    /** 条形码录入方式 */
    public static final String CODE_START_MODE = "CODE_START_MODE";

    /** 优惠图标位置记录 */
    public static final String DISCOUNT_POSITION = "DISCOUNT_POSITION";

    /** 开启条形码扫描 */
    public static final String CODE_START_QR = "CODE_START_QR";
    /** 开启条形码输入 */
    public static final String CODE_START_IN = "CODE_START_IN";

    public static final String USER_NAME = "USER_NAME";

    public KeyEvent(String key, Object value) {
        this.value = value;
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}
