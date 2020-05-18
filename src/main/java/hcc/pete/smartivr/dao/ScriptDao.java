package hcc.pete.smartivr.dao;

import hcc.pete.smartivr.pojo.Script;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */
public interface ScriptDao extends JpaRepository<Script, Integer> {
}
