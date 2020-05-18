package hcc.pete.smartivr.controller;

import com.alibaba.fastjson.JSONObject;
import hcc.pete.smartivr.pojo.Script;
import hcc.pete.smartivr.service.ScriptService;
import hcc.pete.smartivr.util.CommonResult;
import hcc.pete.smartivr.util.JSONUtils;
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
    @Autowired
    private JSONUtils JSONUtils;

    @RequestMapping(value = "/get")
    public CommonResult getScript(@RequestParam int id) {
        try {
            Script script = scriptService.findById(id);
            result.setData(script);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail(500, "cannot find this script in DB", e);
        }
        return result;
    }

    @PostMapping(value = "/add")
    public CommonResult addScript(@RequestBody JSONObject jsonObject) {
        try {
            Script script = new Script();
            JSONUtils.toScriptObj(jsonObject, script);
            scriptService.addScript(script);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail(500, "fail", e);
        }
        return result;
    }

    @PostMapping(value = "/update")
    public CommonResult updateScript(@RequestBody JSONObject jsonObject) {
        try {
            Script script = new Script();
            JSONUtils.toScriptObj(jsonObject, script);
            scriptService.updateScript(script);
            result.setData(script);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail(500, "fail", e);
        }
        return result;
    }

    @RequestMapping(value = "delete")
    public CommonResult delScript(@RequestParam int id) {
        try {
            scriptService.delById(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail(500, "fail", e);
        }
        return result;
    }
}
