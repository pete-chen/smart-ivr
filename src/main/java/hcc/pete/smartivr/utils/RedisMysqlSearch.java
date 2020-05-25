package hcc.pete.smartivr.utils;

import hcc.pete.smartivr.pojo.Audio;
import hcc.pete.smartivr.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Pete Chen
 * @date 2020/5/22
 */

@Component
public class RedisMysqlSearch {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AudioService audioService;

    public Audio search(String fileName) {

        // Redis 查询
        String path = redisUtil.get(fileName);

        // Mysql 查询
        if (path == null) {
            Audio audio = audioService.findByFileName(fileName);
            if (audio != null) {
                return audio;
            }
        } else {
            return new Audio(fileName, path);
        }

        return null;

    }
}
