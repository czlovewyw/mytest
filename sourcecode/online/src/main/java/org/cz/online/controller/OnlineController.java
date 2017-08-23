package org.cz.online.controller;

import org.cz.online.OnlineApplication;
import org.cz.online.entity.Teamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class OnlineController {

    @Autowired
    private CacheManager cacheManager;
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "latestStatus")
    @ResponseBody
    public List<Teamer> latestStatus(){
        List<Teamer> list = new LinkedList<>();
        for(String name : OnlineApplication.teamers){
            Teamer teamer = new Teamer();
            teamer.setName(name);
            list.add((Teamer) (cacheManager.getCache("team").get(name).get()));
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "addDoing")
    public Map<String,Object> addDoing(String name){
        Map<String,Object> result = new HashMap<>();
        Teamer teamer = (Teamer) (cacheManager.getCache("team").get(name).get());
        teamer.addDoing();
        cacheManager.getCache("team").put(name,teamer);
        result.put("status",1);
        result.put("teamer",teamer);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "addDone")
    public Map<String,Object> addDone(String name){
        Map<String,Object> result = new HashMap<>();
        Teamer teamer = (Teamer) (cacheManager.getCache("team").get(name).get());
        try {
            teamer.addDone();
        } catch (Exception e) {
            result.put("message",e.getMessage());
            result.put("status",0);
            return result;
        }
        cacheManager.getCache("team").put(name,teamer);
        result.put("status",1);
        result.put("teamer",teamer);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "hangup")
    public Map<String,Object> hangup(String name){
        Map<String,Object> result = new HashMap<>();
        Teamer teamer = (Teamer) (cacheManager.getCache("team").get(name).get());
        teamer.setStatus(false);
        cacheManager.getCache("team").put(name,teamer);
        result.put("status",1);
        result.put("teamer",teamer);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "open")
    public Map<String,Object> open(String name){
        Map<String,Object> result = new HashMap<>();
        Teamer teamer = (Teamer) (cacheManager.getCache("team").get(name).get());
        teamer.setStatus(true);
        cacheManager.getCache("team").put(name,teamer);
        result.put("status",1);
        result.put("teamer",teamer);
        return result;
    }
    public String updateTeamer(Teamer teamer){
        return null;
    }
}
