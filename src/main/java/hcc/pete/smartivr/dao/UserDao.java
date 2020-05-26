package hcc.pete.smartivr.dao;

import hcc.pete.smartivr.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pete Chen
 * @date 2020/5/25
 */
public interface UserDao extends JpaRepository<User, Integer> {
    /**
     * 根据用户名查询数据信息
     * @param username
     * @return
     */
    User findByUsername(String username);
}
