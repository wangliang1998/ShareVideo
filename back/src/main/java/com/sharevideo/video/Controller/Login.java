package com.sharevideo.video.Controller;

import com.sharevideo.video.Data.User;
import com.sharevideo.video.dbc.dbc_user;
import com.sharevideo.video.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class Login {

    @Autowired
    private dbc_user dbcUser;

    @GetMapping("/usr/login")
    public Result login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password){

        User user = dbcUser.account_isExist(username,password);
        if(user==null){
            return Result.succ(0,null,null);
        }else{
            int role = user.getRole();
            Map<String,Object> map = new HashMap<>();
            map.put("user",user);
            if(role == 1){
                return Result.succ(1,null,map);
            }else if(role == 2){
                return Result.succ(2,null,map);
            }
        }
        return Result.succ(200,null,null);
    }

    @GetMapping("/usr/zhuce")
    public Result register(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password,
                           @RequestParam(value = "age") int age,@RequestParam(value = "sex") String sex,
                           @RequestParam(value = "address") String address,@RequestParam(value = "phone") String phone) throws IOException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String time = df.format(new Date());
        User user = new User(username,password,age,sex,address,time,phone,"",1);
        user.setIcon("/icon/test.jpeg");
        dbcUser.register(user);
        return Result.succ(200,null,null);
    }



}
