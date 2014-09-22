package com.github.greengerong.book.utils.delegate;

/**
 * ***************************************
 * *
 * Auth: green gerong                     *
 * Date: 2014                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 * *
 * ****************************************
 */
public interface Action2<T1, T2> {
    void apply(T1 input1, T2 input2);
}
