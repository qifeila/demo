package goct.query.demo.util;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;

/**
 * @auther wmm
 * @date 2021/6/20 14:16
 */
@Data
@JSONType
public class LayUiResult<T> {
    private Integer code;//返回码
    private String msg;//提示信息
    private Integer count;//数量
    private T data;

    public LayUiResult() {
    }

    public LayUiResult(Integer code, String msg, Integer count, T data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

}
