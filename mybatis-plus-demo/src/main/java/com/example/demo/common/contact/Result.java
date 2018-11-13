package com.example.demo.common.contact;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class Result implements Serializable {
    private String code;
    private String message;
    private Object data;

    public Result() {
        this("200", "操作成功");
    }

    public Result(String code) {
        this(code, null);
    }

    public Result(Object data) {
        this("200", "操作成功");
        this.data = data;
    }

    public Result(String code, String message) {
        this(code, message, null);
    }


    public Result(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        if (this.message == null) {
            if (ResultCode.RESULT_2000.equals(code)) {
                this.message = "系统繁忙,请稍后再试";
            }
            if (ResultCode.RESULT_1000.equals(code)) {
                this.message = "操作失败";
            }
            if (ResultCode.RESULT_200.equals(code)) {
                this.message = "操作成功";
            }

        }
    }

    public static String fail(String code) {
        return fail(code, null);
    }

    public static String fail(String code, String message) {
        Result result = new Result(code, message, null);
        return new Gson().toJson(result);
    }

    public static String success() {
        return success(null);
    }

    public static String success(Object data) {
        if (data instanceof Pager) {
            Pager pager = (Pager) data;
            Map<String, Object> map = new HashMap<String, Object>();
            map.clear();
            map.put("rows", pager.getResultList());
            map.put("total", pager.getTotalCount());
            map.put("footer",pager.getFooter());
            return success(null, map);
        } else {
            return success(null, data);
        }

    }
    
    public static String successForPage(Object data) {
        if (data instanceof Pager) {
            Pager pager = (Pager) data;
            Map<String, Object> map = new HashMap<String, Object>();
            map.clear();
            map.put("rows", pager.getResultList());
            map.put("total", pager.getTotalCount());
            map.put("mapData", pager.getMapData());
            return success(null, map);
        } else {
            return success(null, data);
        }

    }

    public static String success(String message, Object data) {
        Result result;
        if (data instanceof Pager) {
            Pager pager = (Pager) data;
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("rows", pager.getResultList());
            map.put("total", pager.getTotalCount());
            map.put("footer",pager.getFooter());
            result = new Result("200", message, map);
        } else {
            result = new Result("200", message, data);
        }
        return new Gson().toJson(result);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}