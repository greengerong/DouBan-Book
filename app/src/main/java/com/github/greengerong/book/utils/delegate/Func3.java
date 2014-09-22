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
public interface Func3<P1, P2, P3, T> {
    T apply(P1 input1, P2 input2, P3 input3);
}
