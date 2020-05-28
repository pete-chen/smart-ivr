package hcc.pete.smartivr.controller;

import hcc.pete.smartivr.pojo.Script;
import hcc.pete.smartivr.service.ScriptService;
import hcc.pete.smartivr.pojo.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@RestController
@RequestMapping(value = "/script")
@Transactional(rollbackOn = Exception.class)
public class ScriptController {

    @Autowired
    private CommonResponse result;
    @Autowired
    private ScriptService scriptService;

    @GetMapping(value = "/get")
    public CommonResponse getScript(int id) {
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
    public CommonResponse addScript(@RequestBody Script script) {
        try {
            scriptService.addScript(script);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail( "fail", e);
        }
        return result;
    }

    @PostMapping(value = "/update")
    public CommonResponse updateScript(@RequestBody Script script) {
        try {
            scriptService.updateScript(script);
            result.setData(script);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail( "fail", e);
        }
        return result;
    }

    @GetMapping(value = "delete")
    public CommonResponse delScript(int id) {
        try {
            scriptService.delById(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail( "fail", e);
        }
        return result;
    }
}
