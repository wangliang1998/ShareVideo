package com.sharevideo.video.Controller;

import com.sharevideo.video.Data.User;
import com.sharevideo.video.Data.Video_Kind;
import com.sharevideo.video.dbc.dbc_user;
import com.sharevideo.video.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private dbc_user du;

    @GetMapping("/getUser")
    public Result getUserByID(@RequestParam("username") String username){

        User user = du.getUserById(username);
        return Result.succ(200,null,user);

    }

    @RequestMapping("/user/update")
    public Result upload(@RequestParam(value = "file1",required = false) MultipartFile pic,
                         @RequestParam("username") String username,
                         @RequestParam("sex") String sex, @RequestParam("age") int age,
                         @RequestParam("address") String address,@RequestParam("phone") String phone) throws Exception {

        //图片保存
        if(pic!=null){
            String picname = new Date().getTime() +"."+ pic.getOriginalFilename().split("\\.")[1];
            String upload = "D:\\ShareVideo\\icon\\";
            String path = upload + picname;
            File filePath = new File(path);
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
            outputStream.write(pic.getBytes());
            outputStream.flush();
            outputStream.close();
            du.upateUser(username,sex,age,address,phone,"/icon/" + picname);
        }
        User user = du.getUserById(username);
        //进行修改
        du.upateUser(username,sex,age,address,phone,user.getIcon());
        return Result.succ(200,null,null);
    }

}
