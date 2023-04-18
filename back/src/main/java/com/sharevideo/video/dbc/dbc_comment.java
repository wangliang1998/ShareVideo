package com.sharevideo.video.dbc;

import com.sharevideo.video.Data.Comment;
import com.sharevideo.video.Data.upComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class dbc_comment {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //获取用户的所有评论内容
    public List<upComment> getCommentByUserId(String username){
        String sql = "select id,picture,title,info,comment.time,contain from comment join video on(comment.video_id=video.video_id) where comment.user_id='"+username +"' and state =1";
        RowMapper<upComment> rm = BeanPropertyRowMapper.newInstance(upComment.class);
        List<upComment> list= jdbcTemplate.query(sql,rm);
        return  list;
    }

    //修改用户评论内容
    public void updateUserComment(int id,String contain){
        String sql = "update comment set contain=? where id=?";
        jdbcTemplate.update(sql,contain,id);
    }

    //删除用户评论内容
    public void deleteUserComment(int id){
        String sql = "delete from comment where id=?";
        jdbcTemplate.update(sql,id);
    }
}
