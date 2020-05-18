package hcc.pete.smartivr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hcc.pete.smartivr.pojo.Audio;
import hcc.pete.smartivr.service.AudioService;
import hcc.pete.smartivr.util.AudioStream;
import hcc.pete.smartivr.util.CommonResult;
import hcc.pete.smartivr.util.JSONUtils;
import hcc.pete.smartivr.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@RestController
@RequestMapping(value = "/audio")
public class AudioController {

    @Autowired
    private CommonResult result;
    @Autowired
    private AudioService audioService;
    @Autowired
    private JSONUtils jsonUtils;
    @Autowired
    private AudioStream audioStream;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "getAll")
    @ResponseBody
    public JSONArray getAll() {
        try {
            return JSONArray.parseArray(JSON.toJSONString(audioService.findAll()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "get")
    public void getAudio(@RequestParam String combId, HttpServletResponse response) {
        Audio audio;
        try {
            String path = redisUtil.get(combId);
            if (path == null) {
                audio = audioService.findByCombId(combId);
                redisUtil.set(combId, audio.getPath());
            } else {
                audio = new Audio(combId, path);
            }
            audioStream.returnStream(audio, response);
            result.success(200, "success", audio);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail(500, "cannot find this audio in DB", null);
        }
    }

    @PostMapping(value = "add")
    public CommonResult addAudio(@RequestBody JSONObject jsonObject) {
        try {
            Audio audio = new Audio();
            jsonUtils.toAudioObj(jsonObject, audio);
            if (audioService.findByCombId(audio.getCombId()) == null) {
                audioService.addAudio(audio);
                redisUtil.set(audio.getCombId(), audio.getPath());
                result.success(200, "success", audio);
            } else {
                result.fail(500, "duplicate data", audio);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.fail(500, "fail", null);
        }
        return result;
    }

    @PostMapping(value = "update")
    public CommonResult updateAudio(@RequestBody JSONObject jsonObject) {
        try {
            Audio audio = new Audio();
            jsonUtils.toAudioObj(jsonObject, audio);
            Audio audioDB = audioService.findByCombId(audio.getCombId());
            if (audioDB != null) {
                audioService.updateAudio(audioDB, audio);
                redisUtil.update(audio.getCombId(), audio.getPath());
                result.success(200, "success", audio);
            } else {
                result.fail(500, "cannot find this data in DB", audio);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.fail(500, "fail", null);
        }
        return result;
    }

    @RequestMapping(value = "delete")
    public CommonResult delAudio(@RequestParam int id) {
        try {
            audioService.delById(id);
            result.success(200, "success", null);
        } catch (Exception e) {
            e.printStackTrace();
            result.fail(500, "fail", null);
        }
        return result;
    }

}
