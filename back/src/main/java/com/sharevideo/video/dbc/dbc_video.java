package com.sharevideo.video.dbc;

import com.sharevideo.video.Data.Comment;
import com.sharevideo.video.Data.Video;
import com.sharevideo.video.Data.Video_Kind;
import com.sharevideo.video.Data.kinds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class dbc_video {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int get_auto_increase() {
        String sql = "SELECT MAX(video_id) max FROM video";
        int id = jdbcTemplate.queryForObject(sql,Integer.class);
        return id+1;
    }

    //获取视频类别
    public List<kinds> getAllKinds(){

        String sql = "select * from kind";
        RowMapper<kinds> rm = BeanPropertyRowMapper.newInstance(kinds.class);
        List<kinds> list= jdbcTemplate.query(sql,rm);
        return  list;
    }


    //获取所有视频
    public List<Video> getAllVideos(int page){
        String sql = "select * from video where state=1 order by time desc limit " + (page-1)*30 + ",30";
        RowMapper<Video> rm = BeanPropertyRowMapper.newInstance(Video.class);
        List<Video> list= jdbcTemplate.query(sql,rm);
        return  list;
    }
    //获取所示视频数量
    public int getAllVideosCount(){
        String sql = "SELECT count(*) from video where state=1";
        int count = jdbcTemplate.queryForObject(sql,Integer.class);
        return count;
    }

    //获取分类视频


    public List<Video> get_kind_Videos(int page,int kind){
        String sql = "SELECT * from video JOIN video_kind on(video.video_id = video_kind.video_id) where kind_id = ? and state=1  limit ?,30";
        RowMapper<Video> rm = BeanPropertyRowMapper.newInstance(Video.class);
        List<Video> list= jdbcTemplate.query(sql,rm,kind,(page-1)*30);
        return  list;
    }

    /**
     * 获取分类视频总数
     */
    public int get_kind_Videos_Count(int kind){
        String sql = "SELECT count(*) count from video JOIN video_kind on(video.video_id = video_kind.video_id) where kind_id = ? and state=1";
        int count = jdbcTemplate.queryForObject(sql,Integer.class,kind);
        return count;
    }

    /**
     * 视频搜索
     */
    public List<Video> search_Movies(String name,int page){
        String sql = "select * from video where title like '%"+name+"%' and state=1 limit "+(page-1)*30+",30";
        RowMapper<Video > rm = BeanPropertyRowMapper.newInstance(Video.class);
        List<Video> list= jdbcTemplate.query(sql,rm);
        return  list;
    }
    /**
     * 模糊搜索视频数量
     */
    public Integer getCountBySearch(String name){
        String sql = "select count(*) from video where title like '%"+name+"%' and state=1";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    ///根据视频ID获取视频信息
    public Video getVideoById(int id){
        String sql = "SELECT * FROM video where video_id =?";
        Object[] objects = new Object[]{id};
        RowMapper<Video> rowMapper = new BeanPropertyRowMapper<>(Video.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,objects);
    }
    //根据视频ID获取视频评论信息
    public List<Comment> getCommentByVideoId(int id){
        String sql = "select * from comment where video_id="+id;
        RowMapper<Comment> rm = BeanPropertyRowMapper.newInstance(Comment.class);
        List<Comment> list= jdbcTemplate.query(sql,rm);
        return  list;
    }

    //发表评论
    public void SubmitComment(String username,int videoID,String contain,String user_pic,
                              String time){
        String sql = "insert into comment values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,0,username,videoID,contain,user_pic,time);
    }

    //发表视频
    public void SubmitVideo(int video_id,String user_id,String title,String info,
                            String time,String picture,String video_url,
                            int state){
        String sql = "insert into video values(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,video_id,user_id,title,info,time,picture,video_url,state);
    }
    /**
     * 将视频类别添加到表，用于分类查看
     * @throws SQLException
     */
    public void batchAddKinds(List<Video_Kind> lists) throws SQLException {
        String sql = "insert into video_kind(video_id,kind_id) values(?,?)";
        List<Object[]> objects = new ArrayList<>();
        for(int i=0;i<lists.size();i++){
            objects.add(new Object[]{lists.get(i).getVideo_id(),lists.get(i).getKind_id()});
        }
        jdbcTemplate.batchUpdate(sql,objects);
    }
    //根据用户ID获取用户发布的视频
    public List<Video> getVideosByUserID(String username,int state){
        String sql = "select * from video where user_id='"+username+"' and state ="+state;
        RowMapper<Video> rm = BeanPropertyRowMapper.newInstance(Video.class);
        List<Video> list= jdbcTemplate.query(sql,rm);
        return  list;
    }
    //视频信息修改:该标题和简介
    public void updateVideotitleInfo(int video_id,String title,String info){
        String sql = "update video set title=?,info=? where video_id=?";
        jdbcTemplate.update(sql,title,info,video_id);
    }

    //视频上下架操作
    public void updateVideoState(int video_id,int state){
        String sql = "update video set state = ? where video_id = ?";
        jdbcTemplate.update(sql,state,video_id);
    }

    //后台视频搜索
    public List<Video> search_Movies_Back(String name){
        String sql = "select * from video where title like '%"+name+"%'";
        RowMapper<Video > rm = BeanPropertyRowMapper.newInstance(Video.class);
        List<Video> list= jdbcTemplate.query(sql,rm);
        return  list;
    }

    //后台视频信息修改
    public void updateVideo(int video_id,String title,String info,int state){
        String sql = "update video set title=?,info=?,state=? where video_id=?";
        jdbcTemplate.update(sql,title,info,state,video_id);
    }


}
