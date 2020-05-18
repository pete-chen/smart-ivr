package hcc.pete.smartivr.dao;

import hcc.pete.smartivr.pojo.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */
public interface AudioDao extends JpaRepository<Audio, Integer> {

    Audio findByCombId(String combId);

}
