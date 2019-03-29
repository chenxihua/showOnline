package com.gosuncn.refresh;

import com.gosuncn.refresh.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenxihua
 * @Date: 2019/3/28:15:42
 * @Version 1.0
 **/

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapTest {

    // 用于存储在线用户的数据，key:用户名+登录IP， value:登录时间+访问在线接口时间
    private static Map<String, Object> map = new HashMap<String, Object>();

    @Before
    public void testAdd(){
        map.put("ch&196.18.11.52", "12345126789&hahah");
        map.put("zhong&196.18.41.12", "12223456789&chenxihua");
        map.put("chen&196.168.19.12", "123456789&nihaoytlaozhong");
        map.put("xiha&196.18.18.92", "12345996789&malizhi@qq.com");
        map.put("ch&196.128.11.16", "123456789&hahah");
        map.put("zhong&196.128.171.12", "12523456789&chenxihuar");
        map.put("chen&196.180.11.152", "12345356789&nihaolaozhongvv");
        map.put("xiha&196.128.18.192", "1234556789&malizhi@qq.comvv");
        map.put("zxeh&196.128.11.52", "1523456789&hahahvv");
        map.put("zhodageng&196.128.41.12", "1923456789&chenxihuavv");
        map.put("sfdin&196.118.19.12", "1234156789&nihaoaozhongvv");
        map.put("malixhiz&196.128.18.92", "1234560789&malizhi@qq.comvv");
        map.put("malixxx&196.128.11.16", "1234056789&hahahse");
        map.put("malizchendge&196.128.171.12", "1237456789&chenxihuase");
        map.put("adgaec&196.128.11.152", "1234656789&nihalaozhongsee");
        map.put("dfegchenseee&196.128.18.192", "1023456789&malizhi@qq.comwr");
    }

    public Map<String, Object> hahaMap(Integer page, Integer limit, String username, String ip){
        Map<String, Object> result = new HashMap<String, Object>();
        List<User> userList = new ArrayList<User>();

        try{
            if (username!=null && ip==null ){ //&&ip==null&&ip==""
                log.info("==> 来到用户名，无ip");
                for (Map.Entry<String, Object> entry : map.entrySet()){
                    User user = new User();
                    String key = entry.getKey();
                    String[] splitKey = key.split("&");
                    String usernameKey = splitKey[0];
                    if (usernameKey.contains(username)){
                        String ipKey = splitKey[1];
                        // 分割时间
                        String valueKey = (String)entry.getValue();
                        String[] splitValue = valueKey.split("&");
                        long loginTime = Long.parseLong(splitValue[0]);

                        // 保存
                        user.setUsername(usernameKey);
                        user.setIp(ipKey);
                        user.setCreateTime(loginTime);
                        userList.add(user);
                    }
                }
                if (userList.size()==0){
                    result.put("code", 300); result.put("msg", "此次用户名查询无数据");
                    return result;
                }else if (userList.size()<limit){
                    result.put("code", 200);
                    result.put("msg", "查询用户名成功");
                    result.put("data", userList);
                    result.put("count", userList.size());
                    return result;
                }
                else{
                    Integer start = page*limit-limit;
                    Integer end = page*limit;
                    List<User> subList = userList.subList(start, end);

                    result.put("code", 200);
                    result.put("msg", "查询用户名成功");
                    result.put("data", subList);
                    result.put("count", userList.size());
                    return result;
                }
            }else if (username==null && ip!=null){  // &&username==null&&username==""
                log.info("==> 来到ip, 无用户名");
                for (Map.Entry<String, Object> entry : map.entrySet()){
                    User user = new User();
                    String key = entry.getKey();
                    String[] splitKey = key.split("&");
                    String ipKey = splitKey[1];
                    if (ipKey.contains(ip)){
                        String usernameKey = splitKey[0];
                        // 分割时间
                        String valueKey = (String)entry.getValue();
                        String[] splitValue = valueKey.split("&");
                        long loginTime = Long.parseLong(splitValue[0]);

                        // 保存
                        user.setUsername(usernameKey);
                        user.setIp(ipKey);
                        user.setCreateTime(loginTime);
                        userList.add(user);
                    }
                }
                if (userList.size()==0){
                    result.put("code", 300); result.put("msg", "此次ip查询无数据");
                    return result;
                }else if(userList.size()<limit){
                    result.put("code", 200);
                    result.put("msg", "查询ip成功");
                    result.put("data", userList);
                    result.put("count", userList.size());
                    return result;
                }else{
                    Integer start = page*limit-limit;
                    Integer end = page*limit;
                    List<User> subList = userList.subList(start, end);

                    result.put("code", 200);
                    result.put("msg", "查询ip成功");
                    result.put("data", subList);
                    result.put("count", userList.size());
                    return result;
                }
            }else if(username!=null && ip!=null){
                log.info("==> 来到有ip, 有用户名");
                for (Map.Entry<String, Object> entry : map.entrySet()){
                    User user = new User();
                    String key = entry.getKey();
                    String[] splitKey = key.split("&");
                    String usernameKey = splitKey[0];
                    String ipKey = splitKey[1];
                    if (usernameKey.contains(username)&&ipKey.contains(ip)){
                        // 分割时间
                        String valueKey = (String)entry.getValue();
                        String[] splitValue = valueKey.split("&");
                        long loginTime = Long.parseLong(splitValue[0]);

                        // 保存
                        user.setUsername(usernameKey);
                        user.setIp(ipKey);
                        user.setCreateTime(loginTime);
                        userList.add(user);
                    }
                }
                if (userList.size()==0){
                    result.put("code", 300); result.put("msg", "此次用户名和ip查询无数据");
                    return result;
                }else if (userList.size()<limit){
                    result.put("code", 200);
                    result.put("msg", "查询用户名和ip成功");
                    result.put("data", userList);
                    result.put("count", userList.size());
                } else{
                    Integer start = page*limit-limit;
                    Integer end = page*limit;
                    List<User> subList = userList.subList(start, end);

                    result.put("code", 200);
                    result.put("msg", "查询用户名和ip成功");
                    result.put("data", subList);
                    result.put("count", userList.size());
                    return result;
                }
            }else{
                log.info("==> 查询所有map数据");
                for (Map.Entry<String, Object> entry : map.entrySet()){
                    User user = new User();
                    // 分割key
                    String key = entry.getKey();
                    String[] splitKey = key.split("&");
                    String usernameKey = splitKey[0];
                    String ipKey = splitKey[1];
                    // 分割时间
                    String valueKey = (String)entry.getValue();
                    String[] splitValue = valueKey.split("&");
                    long loginTime = Long.parseLong(splitValue[0]);

                    // 保存
                    user.setUsername(usernameKey);
                    user.setIp(ipKey);
                    user.setCreateTime(loginTime);

                    userList.add(user);
                }
                if (userList.size()==0){
                    result.put("code", 300); result.put("msg", "此次用户名和ip查询无数据");
                    return result;
                }else if(userList.size()<limit){
                    result.put("code", 200);
                    result.put("msg", "查询所有数据成功");
                    result.put("data", userList);
                    result.put("count", userList.size());
                    return result;
                }else{
                    Integer start = page*limit-limit;
                    Integer end = page*limit;
                    List<User> subList = userList.subList(start, end);

                    result.put("code", 200);
                    result.put("msg", "查询所有数据成功");
                    result.put("data", subList);
                    result.put("count", userList.size());
                    return result;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "查询失败");
        }
        return result;
    }


    @Test
    public void testHahaMap(){
        Map<String, Object> objectMap = hahaMap(1, 5,null, null);

        log.info("---> 数据： "+objectMap);
    }

    @Test
    public void testhhhhh(){
        for (Map.Entry<String, Object> entry : map.entrySet()){
            log.info("---> "+entry.getKey());
        }
    }


}
