package hcc.pete.smartivr.service;

import com.alibaba.fastjson.JSONArray;
import hcc.pete.smartivr.dao.ScriptDao;
import hcc.pete.smartivr.pojo.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@Service
@Transactional(rollbackOn = Exception.class)
public class ScriptService {

    @Autowired
    private ScriptDao scriptDao;

    public void addScript(Script script) {

        scriptDao.save(script);
    }

    public void updateScript(Script script) {
        if (scriptDao.findById(script.getId()).get() != null) {
            scriptDao.save(script);
        }
    }

    public Script findById(int id) {
        return scriptDao.findById(id).get();
    }

    public void delById(int id) {
        scriptDao.deleteById(id);
    }

}
