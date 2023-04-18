package com.sharevideo.video.Data;

public class Video_Kind {
    private int video_id;
    private int kind_id;

    public Video_Kind() {
    }

    public Video_Kind(int video_id, int kind_id) {
        this.video_id = video_id;
        this.kind_id = kind_id;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public int getKind_id() {
        return kind_id;
    }

    public void setKind_id(int kind_id) {
        this.kind_id = kind_id;
    }
}
