package com.sharevideo.video.Controller;

import com.sharevideo.video.Data.*;
import com.sharevideo.video.dbc.dbc_comment;
import com.sharevideo.video.dbc.dbc_user;
import com.sharevideo.video.dbc.dbc_video;
import com.sharevideo.video.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class Main {

    @Autowired
    private dbc_video dv;
    @Autowired
    private dbc_user du;
    @Autowired
    private dbc_comment dc;


    //获取视频分类
    @GetMapping("/api/getKinds")
    public Result getKinds(){
        List<kinds> kinds = null;
        kinds = dv.getAllKinds();
        return Result.succ(200,null,kinds);
    }

    //获取所有视频
    @GetMapping("/video/all")
    public Result getAllVideos(@RequestParam("page") int page){
        List<Video> videos = null;
        videos = dv.getAllVideos(page);
        int count = dv.getAllVideosCount();
        Map<String,Object> map = new HashMap<>();
        map.put("data",videos);
        map.put("count",count);
        return Result.succ(200,null,map);
    }
    //分类获取视频
    @GetMapping("/video/kind")
    public Result getKindVideos(@RequestParam("page") int page,@RequestParam("kind") int kind){
        List<Video> videos = null;
        videos = dv.get_kind_Videos(page,kind);
        int count = dv.get_kind_Videos_Count(kind);
        Map<String,Object> map = new HashMap<>();
        map.put("data",videos);
        map.put("count",count);
        return Result.succ(200,null,map);
    }

    //视频搜索
    @GetMapping("/video/search")
    public Result getSearchVideos(@RequestParam("page") int page,@RequestParam("name") String name){
        List<Video> videos = null;
        videos = dv.search_Movies(name,page);
        int count = dv.getCountBySearch(name);
        Map<String,Object> map = new HashMap<>();
        map.put("data",videos);
        map.put("count",count);
        return Result.succ(200,null,map);
    }

    //根据视频ID获取视频信息
    @GetMapping("/video/detail")
    public Result getVideoDetail(@RequestParam("id") int id){
        //先获取视频信息
        Video video = dv.getVideoById(id);
        //然后获取该视频作者的信息
        User user = du.getUserById(video.getUser_id());
        //获取该视频的评论信息
        List<Comment> comments= dv.getCommentByVideoId(id);
        //封装
        Map<String,Object> maps = new HashMap<>();
        maps.put("video",video);
        maps.put("user",user);
        maps.put("comments",comments);
        return Result.succ(200,"",maps);
    }

    //发布评论
    @GetMapping("/video/comment")
    public Result submitComment(@RequestParam("username") String username,
                                @RequestParam("video_id") int video_id,
                                @RequestParam("contain") String contain){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String time = df.format(new Date());
        User user = du.getUserById(username);
        dv.SubmitComment(username,video_id,contain,user.getIcon(),time);
        return Result.succ(200,null,null);
    }

    //根据用户ID获取用户发布的视频
    @GetMapping("/user/video/submit")
    public Result getVideosByUserid(@RequestParam("username") String username,@RequestParam("state") int state){
        List<Video> lists = dv.getVideosByUserID(username,state);
        return Result.succ(200,null,lists);
    }

    //视频信息修改:该标题和简介
    @GetMapping("/usr/video/updateTitle")
    public Result updateTitleInfo(@RequestParam("video_id") int video_id,@RequestParam("title") String title,
                                  @RequestParam("info") String info){
        dv.updateVideotitleInfo(video_id,title,info);
        return Result.succ(200,null,null);
    }

    //视频上下架处理
    @GetMapping("/usr/video/updateState")
    public Result updateState(@RequestParam("video_id") int video_id,
                                  @RequestParam("state") String state){
        dv.updateVideoState(video_id,Integer.parseInt(state));
        return Result.succ(200,null,null);
    }

    //获取用户评论内容
    @GetMapping("/usr/comment/all")
    public Result getUserAllComments(@RequestParam("username") String username){
        List<upComment> list = dc.getCommentByUserId(username);
        return Result.succ(200,null,list);
    }

    //修改用户评论内容
    @GetMapping("/usr/comment/update")
    public Result updateUserComment(@RequestParam("id") int id,@RequestParam("contain") String contain){
        dc.updateUserComment(id,contain);
        return Result.succ(200,null,null);
    }

    //删除用户评论内容
    @GetMapping("/usr/comment/delete")
    public Result deleteUserComment(@RequestParam("id") int id){
        dc.deleteUserComment(id);
        return Result.succ(200,null,null);
    }


}
