package hcc.pete.smartivr.service;

import hcc.pete.smartivr.dao.BlockDao;
import hcc.pete.smartivr.pojo.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@Service
@Transactional(rollbackOn = Exception.class)
public class BlockService {

    @Autowired
    private BlockDao blockDao;

    public void addBlock(Block block) {
        blockDao.save(block);
    }

    public void updateBlock(Block block) {
       if (blockDao.findById(block.getId()).get() != null) {
           blockDao.save(block);
       }
    }

    public Block findById(int id) {
        return blockDao.findById(id).get();
    }

    public void delById(int id) {
        blockDao.deleteById(id);
    }


}
