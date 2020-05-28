package hcc.pete.smartivr.response;


import org.omg.PortableInterceptor.NON_EXISTENT;

/**
 * @author Pete Chen
 * @date 2020/5/27
 */

public enum ErrorCodeAndMsg {
    NO_TOKEN(501, "no token"),
    INVALID_TOKEN(502, "INVALID_TOKEN"),
    NON_EXISTENT_USER(401, "cannot find this user in DB");

    private Integer state;
    private String msg;

    ErrorCodeAndMsg(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
