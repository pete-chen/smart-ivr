package hcc.pete.smartivr.controller;

import hcc.pete.smartivr.pojo.Block;
import hcc.pete.smartivr.service.BlockService;
import hcc.pete.smartivr.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@RestController
@RequestMapping(value = "/block")
public class BlockController {

    @Autowired
    private CommonResult result;
    @Autowired
    private BlockService blockService;

    @RequestMapping(value = "/get")
    public CommonResult getBlock(@RequestParam int id) {
        try {
            Block block = blockService.findById(id);
            result.setData(block);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("cannot find this block in DB", e);
        }
        return result;
    }

    @PostMapping(value = "/add")
    public CommonResult addBlock(Block block) {
        try {
            blockService.addBlock(block);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("fail", e);
        }
        return result;
    }

    @PostMapping(value = "update")
    public CommonResult updateBlock(Block block) {
        try {
            blockService.updateBlock(block);
            result.setData(block);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("fail", e);
        }
        return result;
    }

    @RequestMapping(value = "delete")
    public CommonResult delBlock(@RequestParam int id) {
        try {
            blockService.delById(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("fail", e);
        }

        return result;
    }

}
