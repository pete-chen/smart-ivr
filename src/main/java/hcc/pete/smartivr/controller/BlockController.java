package hcc.pete.smartivr.controller;

import hcc.pete.smartivr.pojo.Block;
import hcc.pete.smartivr.service.BlockService;
import hcc.pete.smartivr.pojo.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@RestController
@RequestMapping(value = "/block")
@Transactional(rollbackOn = Exception.class)
public class BlockController {

    @Autowired
    private CommonResponse result;
    @Autowired
    private BlockService blockService;

    @GetMapping(value = "/get")
    public CommonResponse getBlock(int id) {
        try {
            Block block = blockService.findById(id);
            result.success("success", block);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("cannot find this block in DB", null);
        }
        return result;
    }

    @PostMapping(value = "/add")
    public CommonResponse addBlock(@RequestBody Block block) {
        try {
            blockService.addBlock(block);
            result.success("success", block);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("fail to add this block data", block);
        }
        return result;
    }

    @PostMapping(value = "update")
    public CommonResponse updateBlock(@RequestBody Block block) {
        try {
            blockService.updateBlock(block);
            result.success("success", block);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("fail to update the block info", block);
        }
        return result;
    }

    @GetMapping(value = "delete")
    public CommonResponse delBlock(int id) {
        try {
            blockService.delById(id);
            result.success("success", id);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("fail to delete this block data", id);
        }

        return result;
    }

}
