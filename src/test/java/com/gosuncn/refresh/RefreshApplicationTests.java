package com.gosuncn.refresh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RefreshApplicationTests {

    // 用于存储在线用户的数据，key:用户名+登录IP， value:登录时间+访问在线接口时间
    private static Map<String, Object> map = new HashMap<String, Object>();

//    @Before
//    public void testAdd(){
//        map.put("ch&196.128.11.52", "12345126789&hahah");
//        map.put("zhong&196.128.41.12", "12223456789&chenxihua");
//        map.put("chen&196.128.19.12", "123456789&nihaoytlaozhong");
//        map.put("xiha&196.128.18.92", "12345996789&malizhi@qq.com");
//        map.put("ch&196.128.11.16", "123456789&hahah");
//        map.put("zhong&196.128.171.12", "12523456789&chenxihuar");
//        map.put("chen&196.128.11.152", "12345356789&nihaolaozhongvv");
//        map.put("xiha&196.128.18.192", "1234556789&malizhi@qq.comvv");
//        map.put("zxeh&196.128.11.52", "1523456789&hahahvv");
//        map.put("zhodageng&196.128.41.12", "1923456789&chenxihuavv");
//        map.put("sfdin&196.128.19.12", "1234156789&nihaoaozhongvv");
//        map.put("malixhiz&196.128.18.92", "1234560789&malizhi@qq.comvv");
//        map.put("malixxx&196.128.11.16", "1234056789&hahahse");
//        map.put("malizchendge&196.128.171.12", "1237456789&chenxihuase");
//        map.put("adgaec&196.128.11.152", "1234656789&nihalaozhongsee");
//        map.put("dfegchenseee&196.128.18.192", "1023456789&malizhi@qq.comwr");
//    }


//    @Test
//    public void testHashMap(){
////        log.info("map: --> "+map);
//        if (map.isEmpty()){
//            log.info("map为空，结束");
//            return;
//        }
//        for (Map.Entry<String, Object> entry : map.entrySet()){
//            // 获取当前时间戳，得到访问这个离线接口的时间戳
//            long timeMillis = System.currentTimeMillis();
//
//            String key = entry.getKey();
//            String entryValue = (String)entry.getValue();
//            log.info("1: "+key+"; 2: "+entryValue);
////            String[] split = entryValue.split("&");
////            log.info("show: "+ split.length);
//
//            String[] splits = entryValue.split("&");
//            // 转成long类型
////            for (String split : splits) {
////                log.info("---> : "+split);
////            }
//            long lastMillis = Long.parseLong(splits[1]);
//            log.info("---> : "+lastMillis);
//
//
//        }
//    }

    @Test
    public void contextLoads() {
    }
//    @Test
//    public void testTime(){
//        try {
//            long one = System.currentTimeMillis();
//            Thread.sleep(5000);
//            long two = System.currentTimeMillis();
//
//            long haha = two-one;
//            // 开始时间：1553665967963; 结束时间：1553665972962;时间差：4999
//            log.info("开始时间："+one+"; 结束时间："+two+";时间差："+ haha);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            log.error("有异常");
//        }
//    }
//    @Test
//    public void testMap(){
//        Map<String, Object> map = new HashMap();
//        map.put("ch&196.128.11.12", "hahah");
//        map.put("zhong&196.128.11.12", "chenxihua");
//        map.put("chen&196.128.11.12", "nihao,laozhong");
//        map.put("xiha&196.128.11.12", "malizhi@qq.com");
//        for (Map.Entry<String, Object> entry : map.entrySet()){
//            log.info("---> : "+entry.getKey()+"; "+entry.getValue());
//        }
//    }


    @Test
    public void testList(){

        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(11);
        arr.add(56);
        arr.add(77);
        arr.add(18);
        arr.add(86);
        arr.add(3);
        arr.add(91);

        List<Integer> integers = arr.subList(2, 5);
        log.info("---> shuju: "+integers);

    }




}
