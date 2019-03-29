package com.gosuncn.refresh.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: chenxihua
 * @Date: 2019/3/27:11:27
 * @Version 1.0
 **/
@Slf4j
@Component
public class SchedulerTask {


//    @Scheduled(cron = "0/5 * * * * ? ")//cron表达式
//    public void task() {
//
//    }

//    @Scheduled(cron = "0/5 * * * * ? ")
//    public void task1() {
//        String msg = "任务调度二-----发送时间" + new SimpleDateFormat("HH:mm:ss").format(new Date());
//        log.info("---> : "+msg);
//    }

}
