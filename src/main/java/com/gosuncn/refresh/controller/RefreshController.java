package com.gosuncn.refresh.controller;

import com.gosuncn.refresh.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenxihua
 * @Date: 2019/3/27:9:27
 * @Version 1.0
 * 用于提示用户在线人数
 **/
@Slf4j
@Controller
@RequestMapping(value = "/verify")
public class RefreshController {


    @Value(value = "${spring.timing.interval}")
    private Integer interval;

    // 用于存储在线用户的数据，key:用户名+登录IP， value:登录时间+访问在线接口时间
    private static Map<String, Object> map = new HashMap<String, Object>();

//    static{
//        map.put("ch&196.18.11.52", "12345126789&hahah");
//        map.put("zhong&196.18.41.12", "12223456789&chenxihua");
//        map.put("chen&196.168.19.12", "123456789&nihaoytlaozhong");
//        map.put("xiha&196.18.18.92", "12345996789&malizhi@qq.com");
//        map.put("ch&196.128.11.16", "123456789&hahah");
//        map.put("zhong&196.128.171.12", "12523456789&chenxihuar");
//        map.put("chen&196.180.11.152", "12345356789&nihaolaozhongvv");
//        map.put("xiha&196.128.18.192", "1234556789&malizhi@qq.comvv");
//        map.put("zxeh&196.128.11.52", "1523456789&hahahvv");
//        map.put("zhodageng&196.128.41.12", "1923456789&chenxihuavv");
//        map.put("sfdin&196.118.19.12", "1234156789&nihaoaozhongvv");
//        map.put("malixhiz&196.128.18.92", "1234560789&malizhi@qq.comvv");
//        map.put("malixxx&196.128.11.16", "1234056789&hahahse");
//        map.put("malizchendge&196.128.171.12", "1237456789&chenxihuase");
//        map.put("adgaec&196.128.11.152", "1234656789&nihalaozhongsee");
//        map.put("dfegchenseee&196.128.18.192", "1023456789&malizhi@qq.comwr");
//
//        map.put("ceceh&196.18.11.52", "12345126789&hahah");
//        map.put("zhoaang&196.18.41.12", "12223456789&chenxihua");
//        map.put("chenaec&196.168.19.12", "123456789&nihaoytlaozhong");
//        map.put("xihcha&196.18.18.92", "12345996789&malizhi@qq.com");
//        map.put("ch00&196.128.11.16", "123456789&hahah");
//        map.put("zho21ng&196.128.171.12", "12523456789&chenxihuar");
//        map.put("chenui&196.180.11.152", "12345356789&nihaolaozhongvv");
//        map.put("xihaaec&196.128.18.192", "1234556789&malizhi@qq.comvv");
//        map.put("zxeeedch&196.128.11.52", "1523456789&hahahvv");
//        map.put("zhodaeacgeng&196.128.41.12", "1923456789&chenxihuavv");
//        map.put("sfdiaecn&196.118.19.12", "1234156789&nihaoaozhongvv");
//        map.put("maliaexhiz&196.128.18.92", "1234560789&malizhi@qq.comvv");
//        map.put("malieexxx&196.128.11.16", "1234056789&hahahse");
//        map.put("malizeacchendge&196.128.171.12", "1237456789&chenxihuase");
//        map.put("adgae11c&196.128.11.152", "1234656789&nihalaozhongsee");
//        map.put("dfegch34enseee&196.128.18.192", "1023456789&malizhi@qq.comwr");
//    }

    /**
     * 定时上报，这个用户不断地访问系统，客户端必须 每10秒 内访问一次这个接口（即一直在线）
     * @param username
     * @param httpServletRequest
     */
    @ResponseBody
    @RequestMapping(value = "/online", method = RequestMethod.GET)
    public void reportOnline(String username, HttpServletRequest httpServletRequest){
        if (username==null && username==""){
            log.info("用户名为空，退出");
            return;
        }
        // 获取当前时间戳，得到访问这个接口的时间戳
        long timeMillis = System.currentTimeMillis();
        // 变成一个session
        HttpSession session = httpServletRequest.getSession();
        // 获取session创建时间
        long creationTime = session.getCreationTime();
        // 获取ip地址
        String remoteHost = httpServletRequest.getRemoteHost();
        String key = username+"&"+remoteHost;
        String value = creationTime+"&"+timeMillis;

        if (map.containsKey(key)){
            // 已经存在，则更新
            map.put(key, value);
            log.info("更新成功。。。"+map);
        }else {
            // 还没有存在当前的key, 则保存这条数据
            map.put(key, value);
            log.info("添加成功。。。"+map);
        }
    }

    /**
     * 定时上报，有用户离线，离线即移除出在线用户的列表
     * @param username
     * @param httpServletRequest
     */
    @ResponseBody
    @RequestMapping(value = "/offline", method = RequestMethod.GET)
    public void reportOffline(String username, HttpServletRequest httpServletRequest){
        // 判断map是否空，如果空，则返回
        if (map.isEmpty() || username==null && username==""){
            log.info("map为空，或用户名为空，退出");
            return;
        }
        // 获取ip地址
        String remoteHost = httpServletRequest.getRemoteHost();
        String key = username+"&"+remoteHost;
        // 不考虑key不存在的情况，直接考虑key是存在的情况
        if (map.get(key)!=null){
            // 移除这样用户
            map.remove(key);
            log.info("移除登出用户成功。。。");
        }

    }

    /**
     * 这个方法是，用于移除不继续访问的用户，即使session不继续访问的用户，每隔5秒，执行一次这个方法。
     */
    @Scheduled(cron = "0/5 * * * * ? ")//cron表达式
    public void existDatas(){
        if (map.isEmpty()){
            return;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()){
            // 获取当前时间戳，得到访问这个离线接口的时间戳
            long timeMillis = System.currentTimeMillis();

            String key = entry.getKey();
            String entryValue = (String)entry.getValue();

            String[] splits = entryValue.split("&");
            // 转成long类型
            long lastMillis = Long.parseLong(splits[1]);
            if (timeMillis-lastMillis>interval){
                // 表示离线，则移除这个登出用户。
                map.remove(key);
//                log.info("移除不操作用户成功");
            }
        }
    }


    /**
     * 定时上报，返回一个网页，在网页显示在线人数
     * @return
     */
    @RequestMapping(value = "/displayList")
    public String displayOnline(Model model){
        return "login";
    }


    /**
     *
     * @param page
     * @param limit
     * @param username  查询的用户名
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/online_list")
    public Map<String, Object> showOnline(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                          @RequestParam(value = "limit", defaultValue = "15")Integer limit,
                                          String username, String ip){
        Map<String, Object> result = new HashMap<String, Object>();
        List<User> userList = new ArrayList<User>();

        try{
            if (username!=null && ip==null){
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
                    result.put("code", 0);
                    result.put("msg", "查询用户名成功");
                    result.put("data", userList);
                    result.put("count", userList.size());
                    return result;
                }
                else{
                    Integer start = page*limit-limit;
                    Integer end = page*limit;

                    Integer endLength = userList.size() - page*limit;
                    List<User> subList;
                    if (endLength>0){
                        subList = userList.subList(start, end);

                    }else{
                        subList = userList.subList(start, userList.size());
                    }
                    result.put("code", 0);
                    result.put("msg", "查询用户名成功");
                    result.put("data", subList);
                    result.put("count", userList.size());
                    return result;

                }
            }else if (username==null && ip!=null){
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
                    result.put("code", 0);
                    result.put("msg", "查询ip成功");
                    result.put("data", userList);
                    result.put("count", userList.size());
                    return result;
                }else{
                    Integer start = page*limit-limit;
                    Integer end = page*limit;
                    Integer endLength = userList.size() - page*limit;
                    List<User> subList;
                    if (endLength>0){
                        subList = userList.subList(start, end);
                    }else {
                        subList = userList.subList(start, userList.size());
                    }
                    result.put("code", 0);
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
                    result.put("code", 0);
                    result.put("msg", "查询用户名和ip成功");
                    result.put("data", userList);
                    result.put("count", userList.size());
                } else{
                    Integer start = page*limit-limit;
                    Integer end = page*limit;

                    Integer endLength = userList.size() - page*limit;
                    List<User> subList;
                    if(endLength>0){
                        subList = userList.subList(start, end);
                    }else{
                        subList = userList.subList(start, userList.size());
                    }
                    result.put("code", 0);
                    result.put("msg", "查询用户名和ip成功2");
                    result.put("data", subList);
                    result.put("count", userList.size());
                    return result;
                }
            }else{
                log.info("==> 查询所有map数据。 page:"+page+"; limit: "+limit);
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
                    result.put("code", 0);
                    result.put("msg", "查询所有数据成功");
                    result.put("data", userList);
                    result.put("count", userList.size());
                    return result;
                }else{
                    Integer start = page*limit-limit;
                    Integer end = page*limit;

                    Integer endLength = userList.size() - page*limit;
                    List<User> subList;
                    if (endLength>0){
                        subList = userList.subList(start, end);
                    }else{
                        subList = userList.subList(start, userList.size());
                    }
                    result.put("code", 0);
                    result.put("msg", "查询所有数据成功1");
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


}
