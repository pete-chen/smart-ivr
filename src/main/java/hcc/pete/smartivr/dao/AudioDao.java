package hcc.pete.smartivr.dao;

import hcc.pete.smartivr.pojo.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */
public interface AudioDao extends JpaRepository<Audio, Integer> {

    /**
     * 通过录音文件名字查询
     * @param fileName 录音文件名
     * @return
     */
    Audio findByFilename(String fileName);
}
