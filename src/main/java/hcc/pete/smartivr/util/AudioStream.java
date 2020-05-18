package hcc.pete.smartivr.util;

import hcc.pete.smartivr.pojo.Audio;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@Component
public class AudioStream {

    public void returnStream(Audio audio, HttpServletResponse response) throws IOException {
        String path = audio.getPath();
        String name = audio.getCombId();
        File file = new File(path, name);
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("audio/x-wav");
        byte[] buff = new byte[1024];
        int length;
        while ((length = inputStream.read(buff)) != -1) {
            outputStream.write(buff, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

}
