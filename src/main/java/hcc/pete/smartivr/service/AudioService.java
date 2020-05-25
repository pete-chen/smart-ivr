package hcc.pete.smartivr.service;

import hcc.pete.smartivr.dao.AudioDao;
import hcc.pete.smartivr.pojo.Audio;
import hcc.pete.smartivr.utils.CommonResult;
import hcc.pete.smartivr.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */


@Service
@Transactional(rollbackOn = Exception.class)
public class AudioService {

    @Autowired
    private AudioDao audioDao;
    @Autowired
    private RedisUtil redisUtil;

    public List<Audio> findAllJSON() {
        try {
            return audioDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addAudio(Audio audio, CommonResult result) {
        try {
            audioDao.save(audio);
            redisUtil.set(audio.getFilename(), audio.getPath());
            result.success("success", audio);
        } catch (Exception e) {
            result.fail("fail", null);
            e.printStackTrace();
        }

    }

    public void updateAudio(Audio audioDB, Audio audio, CommonResult result) {
        try {
            audioDB = findById(audio.getId());
            if (audioDB != null) {
                audioDB.setComment(audio.getComment());
                audioDB.setFilename(audio.getFilename());
                audioDB.setName(audio.getName());
                audioDB.setBlockId(audio.getBlockId());
                audioDB.setPath(audio.getPath());
                result.success("update success", audio);
                redisUtil.update(audio.getFilename(), audio.getPath());
            } else {
                result.fail("cannot find this data in DB", audio);
            }
        } catch (Exception e) {
            result.fail("fail", null);
            e.printStackTrace();
        }
    }

    public Audio findById(int id) {
        try {
            Optional<Audio> optionalAudio = audioDao.findById(id);
            if(optionalAudio != null && optionalAudio.isPresent()) {
                return optionalAudio.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Audio findByFileName(String fileName) {
        Audio audio = new Audio();
        try {
            audio =  audioDao.findByFilename(fileName);
            if (audio != null) {
                redisUtil.set(fileName, audio.getPath());
                return audio;
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void delById(int id, CommonResult result) {
        Audio audio = findById(id);
        try {
            if (audio != null) {
                audioDao.deleteById(id);
                redisUtil.delete(audio.getFilename());
                result.success("deleted", audio);
            } else {
                result.fail("cannot find this data with id: " + id, null);
            }
        } catch (Exception e) {
            result.success("fail", audio);
            e.printStackTrace();
        }
    }

}
