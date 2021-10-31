package ustc.nftchainv1.exception;
import lombok.Data;

/**
 * 自定义异常类
 */

@Data
public class MyException extends RuntimeException {
    private String msg;         //异常消息
    private int code;           //错误码

    public MyException(String msg) {
        super(msg);
        this.msg = msg;

    }

    public MyException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public MyException(String msg, int code) {
        super(msg);
        this.code = code;
    }
    public MyException(String msg, int code, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }
}
