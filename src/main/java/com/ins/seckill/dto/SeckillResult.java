package com.ins.seckill.dto;

/**
 * 封装数据，作为 JSON 结果集返回
 *
 * @param <T> the type parameter
 */
public class SeckillResult<T> {

    /**
     * 成功标记
     */
    private boolean success;

    /**
     * 封装的数据
     */
    private T data;

    /**
     * 错误信息
     */
    private String error;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
