package com.darwin.prototype.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通用的异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommonException extends RuntimeException{
    // 基本错误类型
    transient private BaseExceptionType baseExceptionType;
    // 异常码
    private long fullErrorCode;

    public CommonException(BaseExceptionType baseExceptionType,
                           int errorCode,
                           String detailMessage){
        super(detailMessage);
        this.fullErrorCode = baseExceptionType.getBitCode() | errorCode;
        this.baseExceptionType= baseExceptionType;
    }

    public static CommonException of(BaseExceptionType baseExceptionType,
                                     int errorCode,
                                     String detailMessage){
        return new CommonException(baseExceptionType,errorCode,detailMessage);
    }

}
