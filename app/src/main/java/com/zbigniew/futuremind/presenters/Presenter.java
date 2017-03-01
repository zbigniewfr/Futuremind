package com.zbigniew.futuremind.presenters;

/**
 * Created by zbigniew on 28/02/2017.
 */

public interface Presenter<T> {
    void setView(T view);
}
