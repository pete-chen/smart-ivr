package hcc.pete.smartivr.service;

import hcc.pete.smartivr.dao.AudioDao;
import hcc.pete.smartivr.pojo.Audio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@Service
@Transactional(rollbackOn = Exception.class)
public class AudioService {

    @Autowired
    private AudioDao audioDao;

    public List<Audio> findAll() {
        return audioDao.findAll();
    }

    public void addAudio(Audio audio) {
        audioDao.save(audio);
    }

    public void updateAudio(Audio audioDB, Audio audio) {
        audioDB = audioDao.findByCombId(audio.getCombId());
        if (audioDB != null) {
            audioDB.setComment(audio.getComment());
            audioDB.setFilename(audio.getFilename());
            audioDB.setName(audio.getName());
            audioDB.setCombId(audio.getCombId());
            audioDB.setBlockId(audio.getBlockId());
            audioDB.setPath(audio.getPath());
        }
    }

    public Audio findById(int id) {
        return audioDao.findById(id).get();
    }

    public Audio findByCombId(String combId) {
        return audioDao.findByCombId(combId);
    }

    public void delById(int id) {
        audioDao.deleteById(id);
    }
}
