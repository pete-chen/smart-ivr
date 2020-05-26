package hcc.pete.smartivr.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@Data
@Component
public class CommonResult {

    private Integer state;
    private String msg;
    private Object data;

    public CommonResult() {
        this.state = 200;
        this.msg = "success";
    }

    public CommonResult(Integer state, String msg, Object data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public CommonResult(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public void fail(String msg, Object data) {
        this.state = 500;
        this.msg = msg;
        this.data = data;
    }

    public void success(String msg, Object data) {
        this.state = 200;
        this.msg = msg;
        this.data = data;
    }
}
