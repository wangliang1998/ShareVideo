package com.sharevideo.video.Data;

public class Video {
    private int video_id;
    private String user_id;
    private String title;
    private String info;
    private String time;
    private String picture;
    private String video_url;
    private int state;

    public Video() {
    }

    public Video(int video_id, String user_id, String title, String info, String time, String picture, String video_url, int state) {
        this.video_id = video_id;
        this.user_id = user_id;
        this.title = title;
        this.info = info;
        this.time = time;
        this.picture = picture;
        this.video_url = video_url;
        this.state = state;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
