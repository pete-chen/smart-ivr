package hcc.pete.smartivr.service;

import hcc.pete.smartivr.dao.UserDao;
import hcc.pete.smartivr.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Pete Chen
 * @date 2020/5/25
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findById(int id) {
        return userDao.findById(id).get();
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
