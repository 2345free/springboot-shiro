package cn.luoxx.shiro.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * json工具类
 *
 * @author luoxx
 */
public class JsonUtils {

    /**
     * 错误Key
     */
    private static final String ERROR_KEY = "msg";

    /**
     * 成功Key
     */
    private static final String SUCCESS_KEY = "success";

    /**
     * 返回系统成功JSON
     *
     * @param msg msg
     * @return String
     */
    public static String getSuccess(String msg) {
        return result(true, msg);
    }

    /**
     * 返回系统成功JSON
     *
     * @return String
     */
    public static String getSuccess() {
        return result(true, "");
    }

    /**
     * 返回系统成功JSONObject
     *
     * @return JSONObject
     */
    public static JSONObject getSuccessJson() {
        return JSONObject.parseObject(getSuccess(""));
    }

    /**
     * @param msg msg
     * @return
     * @since 1.0
     */
    public static JSONObject getSuccessJson(String msg) {
        return JSONObject.parseObject(getSuccess(msg));
    }

    /**
     * 返回系统失败JSON
     *
     * @param msg msg
     * @return String
     */
    public static String getFailure(String msg) {
        return result(false, msg);
    }


    /**
     * 返回系统成功JSONObject
     *
     * @param msg msg
     * @return JSONObject
     */
    public static JSONObject getFailureJson(String msg) {
        return JSONObject.parseObject(getFailure(msg));
    }


    /**
     * 封装通用JSON返回结果
     *
     * @param success success
     * @param msg     msg
     * @return String
     */
    public static String result(boolean success, String msg) {
        JSONObject result = new JSONObject();
        result.put(SUCCESS_KEY, success);
        result.put(ERROR_KEY, msg);
        return result.toString();
    }
}
