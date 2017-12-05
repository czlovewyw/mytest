package vision.apollo;

import java.io.Serializable;

public class SimpleToken implements Serializable {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 创建时间戳，毫秒
     */
    private long timeStamp;

    public SimpleToken() {
    }

    public SimpleToken(String userName, long timeStamp) {
        this.userName = userName;
        this.timeStamp = timeStamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "SimpleToken{" +
                "userName='" + userName + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
