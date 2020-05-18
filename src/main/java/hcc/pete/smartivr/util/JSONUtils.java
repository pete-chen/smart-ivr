package hcc.pete.smartivr.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hcc.pete.smartivr.pojo.Audio;
import hcc.pete.smartivr.pojo.Block;
import hcc.pete.smartivr.pojo.Script;
import org.springframework.stereotype.Component;

/**
 * @author Pete Chen
 * @date 2020/5/15
 */

@Component
public class JSONUtils {
    public void toScriptObj(JSONObject jsonObject, Script script) {
        script.setBlocks(jsonObject.getString("blocks"));
        script.setUserData(jsonObject.getString("properties"));
        script.setName(jsonObject.getString("name"));
        script.setUserData(jsonObject.getString("userData"));
    }

    public void toBlockObj(JSONObject jsonObject, Block block) {
        block.setProperties(jsonObject.getString("properties"));
        block.setName(jsonObject.getString("name"));
    }

    public void toAudioObj(JSONObject jsonObject, Audio audio) {
        String fileName = jsonObject.getString("fileName");
        audio.setFilename(fileName);
        audio.setName(jsonObject.getString("name"));
        audio.setPath(jsonObject.getString("path"));
        audio.setBlockId(jsonObject.getIntValue("blockId"));
        audio.setComment(jsonObject.getString("comment"));
        audio.setCombId(jsonObject.getString("blockId") + "-" + fileName);
    }

}
