package hcc.pete.smartivr.controller;

import hcc.pete.smartivr.annotation.UserLoginToken;
import hcc.pete.smartivr.pojo.Audio;
import hcc.pete.smartivr.pojo.CommonResponse;
import hcc.pete.smartivr.service.AudioService;
import hcc.pete.smartivr.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@RestController
@RequestMapping(value = "audio")
public class AudioController {

    @Autowired
    private CommonResponse commonResponse;
    @Autowired
    private AudioService audioService;
    @Autowired
    private AudioStream audioStream;
    @Autowired
    private RedisMysqlSearch redisMysqlSearch;

    /**
     * 查找所有mysql已存在的音频数据
     * @return 以JSONArray的格式返回
     */
    @UserLoginToken
    @GetMapping(value = "getAll")
    public CommonResponse getAll() {
        try {
            List<Audio> audioList = audioService.findAllJSON();
            commonResponse.success("success", audioList);
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.fail("fail", e.getMessage());
        }
        return commonResponse;
    }

    /**
     * 通过文件名查找相对的录音信息
     * @param filename          录音文件名
     * @return                  返回CommonResult对象
     */
    @UserLoginToken
    @GetMapping(value = "get")
    public CommonResponse getAudio(String filename) {
        try {
            Audio audio = audioService.findByFileName(filename);
            commonResponse.success("success", audio);
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.fail(e.getMessage(), filename);
        }

        return commonResponse;

    }

    /**
     * 根据combId查询音频文件。
     * 先从redis缓存进行查询，
     * 若redis缓存没有数据，则进入mysql数据库进行查询。
     * @param filename          音频文件名
     * @param response          响应，返回一个文件流
     */
    @GetMapping(value = "getStream")
    public void getAudioStream(String filename, HttpServletResponse response) {
        try {
            Audio audio = redisMysqlSearch.search(filename);
            if (audio != null) {
                audioStream.returnStream(audio, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加音频文件到数据库
     * @param audio         audio对象
     * @return              返回CommonResults对象
     */
    @UserLoginToken
    @PostMapping(value = "add")
    public CommonResponse addAudio(@RequestBody Audio audio) {
        try {
            audioService.addAudio(audio);
            commonResponse.success("success", audio);
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.fail(e.getMessage(), audio);
        }
        return commonResponse;

    }

    /**
     * 更新音频文件属性
     * @param audio         audio对象
     * @return              返回CommonResult对象
     */
    @UserLoginToken
    @PostMapping(value = "update")
    public CommonResponse updateAudio(@RequestBody Audio audio) {
        try {
            audioService.updateAudio(audio);
            commonResponse.success("success", audio);
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.fail(e.getMessage(), audio);
        }
        return commonResponse;
    }

    /**
     * 根据音频文件id删除音频文件
     * @param id            音频文件id
     * @return              CommonResult对象
     */
    @UserLoginToken
    @GetMapping(value = "delete")
    public CommonResponse delAudio(int id) {
        try {
            audioService.delById(id);
            commonResponse.success("success", id);
        }catch (Exception e) {
            e.printStackTrace();
            commonResponse.fail(e.getMessage(), id);
        }

        return commonResponse;
    }

}
