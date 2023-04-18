package com.sharevideo.video.Controller;

import com.sharevideo.video.Data.Video_Kind;
import com.sharevideo.video.dbc.dbc_video;
import com.sharevideo.video.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class Upload {

    @Autowired
    private dbc_video dv;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("file1") MultipartFile pic,@RequestParam("file2") MultipartFile video,
                         @RequestParam("username") String username,
                         @RequestParam("title") String title,@RequestParam("info") String info,
                         @RequestParam("kind") String kind) throws Exception {

        //图片保存
        String picname = new Date().getTime() +"."+ pic.getOriginalFilename().split("\\.")[1];
        String upload = "D:\\ShareVideo\\picture\\";
        String path = upload + picname;
        File filePath = new File(path);
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(pic.getBytes());
        outputStream.flush();
        outputStream.close();
        //视频保存
        String videoname = new Date().getTime() + "." + video.getOriginalFilename().split("\\.")[1];
        String upload2 = "D:\\ShareVideo\\video\\";
        String path2 = upload2 + videoname;
        File filePath2 = new File(path2);
        BufferedOutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(filePath2));
        outputStream2.write(video.getBytes());
        outputStream2.flush();
        outputStream2.close();
        //写入数据库
        int video_id = dv.get_auto_increase();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String time = df.format(new Date());
        dv.SubmitVideo(video_id,username,title,info,time,
                "/picture/"+picname,"/video/"+videoname,1);
        //视频分类写入数据库
        List<Video_Kind> movieKinds = new ArrayList<>();
        for (String ss:kind.split(",")) {
            movieKinds.add(new Video_Kind(video_id, Integer.parseInt(ss)));
        }
        dv.batchAddKinds(movieKinds);
        return Result.succ(200,null,null);
    }
}
