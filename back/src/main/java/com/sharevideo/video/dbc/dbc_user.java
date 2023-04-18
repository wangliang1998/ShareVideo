package com.sharevideo.video.dbc;


import com.sharevideo.video.Data.User;
import com.sharevideo.video.Data.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class dbc_user {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //登录
    public User account_isExist(String username, String password) {
        String sql = "select * from account where username=? and password=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        Object[] objects = new Object[]{username,password};
        List<User> list= jdbcTemplate.query(sql,rowMapper,objects);
        if(list.size()==0){
            return null;
        }
        return  list.get(0);
    }

    //注册
    public void register(User user){
        String sql = "insert into account values(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getAge(),
                user.getSex(),user.getAddress(),user.getRegister_time(),
                user.getPhone(),user.getIcon(),user.getRole());
    }
    //获取用户信息
    public User getUserById(String username){
        String sql = "SELECT * FROM account where username =? and role!=2";
        Object[] objects = new Object[]{username};
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        List<User>list= jdbcTemplate.query(sql,rowMapper,objects);
        if(list.size()==0){
            return null;
        }
        return  list.get(0);
    }
    //用户信息修改
    public void upateUser(String username,String sex,int age,String address,String phone,String pic){
        String sql = "update account set sex=?,age=?,address=?,phone=?,icon=? where username=?";
        jdbcTemplate.update(sql,sex,age,address,phone,pic,username);

    }
    //用户状态修改
    public void updateUserState(String username,int state){
        String sql = "update account set role=? where username=?";
        jdbcTemplate.update(sql,state,username);
    }

}
