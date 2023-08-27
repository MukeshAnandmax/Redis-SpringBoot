package com.practice.redisspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user/")
public class HashController {

    private static final String hashkey="Hash:User";
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @PostMapping("/insert_user")
    public  void hmSet(@RequestBody User user){
        redisTemplate.opsForHash().put(hashkey,user.getId(),user);
    }


    @GetMapping("/get_user/{id}")
    public User getUserById(@PathVariable("id") int id){
        return  (User)redisTemplate.opsForHash().get(hashkey,id);
    }


    @GetMapping("/get_users")
    public Map<Object,Object> getUsers(){
     return redisTemplate.opsForHash().entries(hashkey);

    }

    @PutMapping("/update_user")
    public  void updateUser(@RequestBody User user){
        redisTemplate.opsForHash().put(hashkey,user.getId(),user);
    }

    @DeleteMapping("/delete_user/{id}")
    public void deleteUser(@PathVariable("id") int id){
        redisTemplate.opsForHash().delete(hashkey,id);
    }
}
