package com.sharevideo.video.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

        private int code;
        private String msg;
        private Object data;

    public static Result succ(int code,String mes,Object data) {
         Result m = new Result();
         m.setCode(code);
         m.setData(data);
         m.setMsg(mes);
         return m;
     }

}
