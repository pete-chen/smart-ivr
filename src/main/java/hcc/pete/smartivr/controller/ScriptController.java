package hcc.pete.smartivr.controller;

import hcc.pete.smartivr.pojo.Script;
import hcc.pete.smartivr.service.ScriptService;
import hcc.pete.smartivr.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@RestController
@RequestMapping(value = "/script")
public class ScriptController {

    @Autowired
    private CommonResult result;
    @Autowired
    private ScriptService scriptService;

    @RequestMapping(value = "/get")
    public CommonResult getScript(@RequestParam int id) {
        try {
            Script script = scriptService.findById(id);
            result.setData(script);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail("cannot find this script in DB", e);
        }
        return result;
    }

    @PostMapping(value = "/add")
    public CommonResult addScript(Script script) {
        try {
            scriptService.addScript(script);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail( "fail", e);
        }
        return result;
    }

    @PostMapping(value = "/update")
    public CommonResult updateScript(Script script) {
        try {
            scriptService.updateScript(script);
            result.setData(script);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail( "fail", e);
        }
        return result;
    }

    @RequestMapping(value = "delete")
    public CommonResult delScript(@RequestParam int id) {
        try {
            scriptService.delById(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail( "fail", e);
        }
        return result;
    }
}
