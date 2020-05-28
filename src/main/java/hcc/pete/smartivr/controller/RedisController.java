package hcc.pete.smartivr.controller;

import hcc.pete.smartivr.pojo.Audio;
import hcc.pete.smartivr.utils.AudioStream;
import hcc.pete.smartivr.pojo.CommonResponse;
import hcc.pete.smartivr.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

/**
 * @author Pete Chen
 * @date 2020/5/18
 */

@RestController
@RequestMapping(value = "/redis")
@Transactional(rollbackOn = Exception.class)
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AudioStream audioStream;
    @Autowired
    private CommonResponse result;

    @GetMapping(value = "/getAudio")
    public void getAudio(@RequestParam String combId, HttpServletResponse response) {
        String path = redisUtil.get(combId);
        if (path != null) {
            Audio audio = new Audio(combId, path);
            audioStream.returnStream(audio, response);
        }
    }

    @GetMapping(value = "/addAudio")
    public void addAudio(@RequestParam String combId, String path) {
        redisUtil.set(combId, path);
    }

    @GetMapping(value = "/updateAudio")
    public void updateAudio(@RequestParam String combId, String path) {
        redisUtil.update(combId, path);
    }

    @GetMapping(value = "/delAudio")
    public void deleteAudio(@RequestParam String combId) {
        redisUtil.delete(combId);
    }

}
