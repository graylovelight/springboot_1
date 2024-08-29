package com.example.springboot_1.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {
    private int code;
    private String msg;
    private Object data;

    public static ResponseResult success(Object data) {
        return result(200, "操作成功", data);
    }

    public static ResponseResult fail(String msg) {
        return result(400, msg, null);
    }

    public static ResponseResult unauthorized(String msg) {
        return result(401, msg, null);
    }

    public static ResponseResult result(int code, String msg) {
        return result(code, msg, null);
    }

    public static ResponseResult result(int code, String msg, Object data) {
        return ResponseResult.builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }
}
