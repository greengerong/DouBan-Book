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
public interface Func2<P1, P2, T> {
    T apply(P1 input1, P2 input2);
}
