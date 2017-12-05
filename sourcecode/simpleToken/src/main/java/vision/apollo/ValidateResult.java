package vision.apollo;

/**
 * token校验结果
 */
public class ValidateResult {
    private int status;
    private String message;

    public static final int STATUS_ERROR = 1;
    public static final int STATUS_EXPIRED = 2;
    public static final int STATUS_SUCCESS = 0;

    public static final String MESSAGE_ERROR = "TOKEN发生错误";
    public static final String MESSAGE_EXPIRED = "TOKEN过期";
    public static final String MESSAGE_SUCCESS = "TOKEN验证成功";

    private ValidateResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

//    static ValidateResult tokenError(){
//        return new ValidateResult()
//    }
}
