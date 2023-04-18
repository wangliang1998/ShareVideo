package com.sharevideo.video.Data;

public class upComment {
    private int id;
    private String picture;
    private String title;
    private String info;
    private String time;
    private String contain;

    public upComment() {
    }

    public upComment(int id, String picture, String title, String info, String time, String contain) {
        this.id = id;
        this.picture = picture;
        this.title = title;
        this.info = info;
        this.time = time;
        this.contain = contain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }
}
