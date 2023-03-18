package com.doodl6.wechatrobot.service;

import cn.hutool.core.convert.ConvertException;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.doodl6.wechatrobot.config.AppConfig;
import com.doodl6.wechatrobot.response.BaseMessage;
import com.doodl6.wechatrobot.response.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图灵服务类
 */
@Slf4j
@Service
public class PythonService {


    @Resource
    private AppConfig appConfig;

    /**
     * 获取图灵机器人消息响应
     */
    public BaseMessage getChatGptResponse(String content, String fromUserName) {
        String info = new String(content.getBytes(), StandardCharsets.UTF_8);
        String result = chat(info);
        String replace = result.replace("\n\n", "").replace("\n\n", "\n");
        return new TextMessage(replace);
    }

    /**
     * 发送消息
     *
     * @param txt 内容
     * @return {@link String}
     */
    public String chat(String txt) {
        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(new HashMap<String, String>() {{
            put("role", "user");
            put("content", txt);
        }});
        JSONObject message = null;
        try {
            String body = HttpRequest.get("http://47.107.47.156:81/ChatCompletion?messages="
                    + JSONUtil.toJsonStr(dataList)).execute().body();
           message = JSONUtil.parseObj(body);
        } catch (HttpException e) {
            return "出现了异常";
        } catch (ConvertException e) {
            return "出现了异常";
        }
        return message.getStr("content");
    }

}
