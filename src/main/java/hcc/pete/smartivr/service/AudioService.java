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

    public List<Audio> findAllJSON(){
        return audioDao.findAll();
    }

    public void addAudio(Audio audio){
        audioDao.save(audio);
        redisUtil.set(audio.getFilename(), audio.getPath());
    }

    public void updateAudio(Audio audio) throws Exception {
        Audio audioDB = findById(audio.getId());
        if (null != audioDB) {
            audioDB.setComment(audio.getComment());
            audioDB.setFilename(audio.getFilename());
            audioDB.setName(audio.getName());
            audioDB.setBlockId(audio.getBlockId());
            audioDB.setPath(audio.getPath());
            redisUtil.update(audio.getFilename(), audio.getPath());
        } else {
            throw new Exception("cannot find this audio file");
        }
    }

    public Audio findById(int id) throws Exception {
        Optional<Audio> optionalAudio = audioDao.findById(id);
        if(optionalAudio != null && optionalAudio.isPresent()) {
            return optionalAudio.get();
        } else {
            throw new Exception("cannot find this audio file");
        }
    }

    public Audio findByFileName(String fileName) throws Exception {
        Audio audio = audioDao.findByFilename(fileName);
        if (audio != null) {
            redisUtil.set(fileName, audio.getPath());
            return audio;
        } else {
            throw new Exception("cannot find this audio file");
        }
    }

    public void delById(int id) throws Exception {
        Audio audio = findById(id);
        if (audio != null) {
            audioDao.deleteById(id);
            redisUtil.delete(audio.getFilename());
        } else {
            throw new Exception("cannot delete this audio file");
        }
    }

}
