package com.doodl6.wechatrobot.service;

import com.doodl6.wechatrobot.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ChatGptService.class, AppConfig.class})
public class ChatGptServiceTest {
    @Resource
    private ChatGptService chatGptService;

    @Test
    public void name() {
        String hello = chatGptService.chat("hello");
        System.out.println(hello);
    }
}