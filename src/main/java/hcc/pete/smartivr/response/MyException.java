package hcc.pete.smartivr.response;

/**
 * @author Pete Chen
 * @date 2020/5/28
 */
public class MyException extends Exception {

    private final ErrorCodeAndMsg response;

    public MyException(ErrorCodeAndMsg response) {
        this.response = response;
    }

    public ErrorCodeAndMsg getResponse() {
        return response;
    }
}
