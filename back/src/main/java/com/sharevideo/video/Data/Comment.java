package com.sharevideo.video.Data;

public class Comment {
    private int id;
    private String user_id;
    private int video_id;
    private String contain;
    private String user_pic;
    private String time;

    public Comment() {
    }

    public Comment(int id,String user_id, int video_id, String contain, String user_pic, String time) {
        this.id = id;
        this.user_id = user_id;
        this.video_id = video_id;
        this.contain = contain;
        this.user_pic = user_pic;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
