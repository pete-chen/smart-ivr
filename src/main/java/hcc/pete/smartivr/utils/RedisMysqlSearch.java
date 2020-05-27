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

    public Audio search(String filename) throws Exception{

        // Redis 查询
        String path = redisUtil.get(filename);
        // Mysql 查询
        if (null == path || "".equals(path)) {
             return audioService.findByFileName(filename);
        } else {
            return new Audio(filename, path);
        }

    }
}
