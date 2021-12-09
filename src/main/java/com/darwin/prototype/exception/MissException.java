package com.darwin.prototype.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
public class MissException extends RuntimeException {

}
