package com.sharevideo.video.Controller;

import com.sharevideo.video.Data.User;
import com.sharevideo.video.Data.Video;
import com.sharevideo.video.Data.upComment;
import com.sharevideo.video.dbc.dbc_comment;
import com.sharevideo.video.dbc.dbc_user;
import com.sharevideo.video.dbc.dbc_video;
import com.sharevideo.video.util.Result;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class Back {
    @Autowired
    private dbc_user du;
    @Autowired
    private dbc_video dv;
    @Autowired
    private dbc_comment dc;

    //后台获取用户信息
    @GetMapping("/back/getUser")
    public Result getBackUser(@RequestParam("username") String username){
        List<User> list = new ArrayList<>();
        User user = du.getUserById(username);
        if (user!=null)
            list.add(user);
        return Result.succ(200,null,list);

    }
    //用户状态改变
    @GetMapping("/back/user/state")
    public Result updateUserState(@RequestParam("username") String username,@RequestParam("state") int state){
        du.updateUserState(username,state);
        return Result.succ(200,null,null);
    }

    //搜索视频
    @GetMapping("/back/video/search")
    public Result SearchVideo(@RequestParam("name") String name){
        List<Video> list = dv.search_Movies_Back(name);
        return Result.succ(200,null,list);
    }

    //修改视频
    @GetMapping("/back/video/update")
    public Result updateVideo(@RequestParam("video_id") int video_id,@RequestParam("title") String title,
                              @RequestParam("info") String info,@RequestParam("state") int state){

        dv.updateVideo(video_id,title,info,state);
        return Result.succ(200,null,null);

    }
    //获取用户评论内容
    @GetMapping("/back/comment/search")
    public Result getBackUserComment(@RequestParam("username") String username){
        List<upComment> list = dc.getCommentByUserId(username);
        return Result.succ(200,null,list);
    }
}
