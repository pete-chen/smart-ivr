package hcc.pete.smartivr.pojo;

import lombok.Data;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@Data
@Component
public class CommonResponse implements Serializable {

    private Integer state;
    private String msg;
    private Object data;

    public CommonResponse() {
        this.state = 200;
        this.msg = "success";
    }

    public CommonResponse(Integer state, String msg, Object data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public CommonResponse(Integer state, String msg) {
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
