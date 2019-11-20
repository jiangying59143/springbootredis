package com.example.springbootredis.controller;

import com.example.springbootredis.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class StudentController {
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/setStu")
    public void set(@RequestBody Student student){
        redisTemplate.opsForValue().set("stu",student);
    }

    @GetMapping("/string")
    public String stringTest(){
        redisTemplate.opsForValue().set("str", "sdfsadfsadfasdfasdfsdf");
        String str = (String) redisTemplate.opsForValue().get("str");
        return str;
    }

    @GetMapping("/list")
    public List<String> listTest(){
        ListOperations<String,String> list = redisTemplate.opsForList();
        list.leftPush("list","Hello");
        list.leftPush("list","World");
        list.leftPush("list","Java");
        List<String> result = list.range("list",0,2);
        return result;
    }

    @GetMapping("/set")
    public Set<String> setTest(){
        SetOperations<String,String> set = redisTemplate.opsForSet();
        set.add("set","Hello");
        set.add("set","Hello");
        set.add("set","World");
        set.add("set","World");
        set.add("set","Java");
        set.add("set","Java");
        Set<String> result = set.members("set");
        return result;
    }

    //有序集合
    @GetMapping("/zset")
    public Set<String> zsetTest(){
        ZSetOperations<String,String> zset = redisTemplate.opsForZSet();
        zset.add("zset","Hello",1);
        zset.add("zset","World",2);
        zset.add("zset","Java",3);
        Set<String> result = zset.range("zset",0,2);
        for(String value:result){
            System.out.println(value);
        }
        return result;
    }

    @GetMapping("/hash")
    public String hashTest(){
        HashOperations<String,String,String> hash = redisTemplate.opsForHash();
        hash.put("hashMap1","key","hello");
        String str = hash.get("hashMap1","key");
        return str;
    }
}
