package com.darwin.prototype.exception;

/**
 * 基本的错误类型
 */
public enum BaseExceptionType {
    MISS(1L <<31);
    private long bit;
    BaseExceptionType(long bit){
        this.bit = bit;
    }

    public long getBitCode() {
        return bit;
    }
}
