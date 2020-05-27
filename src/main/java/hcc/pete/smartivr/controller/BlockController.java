package hcc.pete.smartivr.controller;

import hcc.pete.smartivr.pojo.Block;
import hcc.pete.smartivr.service.BlockService;
import hcc.pete.smartivr.utils.CommonResult;
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
    private CommonResult result;
    @Autowired
    private BlockService blockService;

    @GetMapping(value = "/get")
    public CommonResult getBlock(int id) {
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
    public CommonResult addBlock(@RequestBody Block block) {
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
    public CommonResult updateBlock(@RequestBody Block block) {
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
    public CommonResult delBlock(int id) {
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
