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
public interface Action3<T1, T2, T3> {
    void apply(T1 input1, T2 input2, T3 input3);
}
