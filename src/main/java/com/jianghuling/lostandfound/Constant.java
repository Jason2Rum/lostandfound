package com.jianghuling.lostandfound;

public class Constant {
    public static final int SUCCESS = 1;
    public static final int FAIL = 0;
    public static final int FIRST_LOG_IN = 2;
    public static final String APPID = "wx8d809cd9272a9b4d";
    public static final String SECRET = "a1b108b1bc6cad90a671b1194068f4af";
    public static final byte HAS_CLAIMED = new Byte("1");
    public static final byte NO_CLAIM = new Byte("0");
    public static final byte CANCEL = new Byte("2"); //取消发布
    public static final String UPLOAD_IMG_LOC = "/home/lostandfound/itemimages/";
//    public static final String UPLOAD_IMG_LOC = "C:\\Users\\Winter\\Desktop\\";

    public static final String PIC_ACCESS_PREFIX = "http://119.23.203.8/lostimages/";

}
