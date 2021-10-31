package ustc.nftchainv1.common;


import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一封装返回给客户端的数据
 */
public class R extends HashMap<String, Object> {
    private R() {
        //默认success：200
        this.put("code", HttpStatus.SC_OK);
        this.put("msg", "success");
    }

    /**
     * 成功
     * 工厂类
     * 链式调用
     */
    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    /**
     * 携带数据,token等
     * @param map :
     */
    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    /**
     * @return 默认成功信息
     */
    public static R ok() {
        return new R();
    }

    /**
     * 重写put方法，用R作返回类型，可以链式调用
     */
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * error:要有具体错误类型
     */
    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    /**
     * @return : 无code，默认为500
     */
    public static R error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static R error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知错误");
    }


}

