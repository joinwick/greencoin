package com.gc.common.inter;

/**
 * @author join wick
 * @version 1.0.0
 * @description data validation
 * @createDate 2020/12/10 8:51
 * @since 1.0.0
 */
public interface DataValidation<T> {
    boolean checkValid(T t);
}
