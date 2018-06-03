package com.example.rz.apptesttool.mvp.model;

/**
 * Created by rz on 4/11/18.
 */

/**
 *
 * @param <V> - Value
 * @param <E> - Error
 */
public class Response<V, E> {

    private boolean isSuccessfull;

    private V value;

    private E error;

    public static <V, E> Response<V, E> success(V v) {
        return new Response<>().setSuccessfull(true).setValue(v);
    }
    public static <V, E> Response<V, E> success(V v, E e) {
        return new Response<>().setSuccessfull(true).setValue(v).setError(e);
    }

    public static <V, E> Response<V, E> failure(E e) {
        return new Response<>().setSuccessfull(false).setError(e);
    }

    public static <V, E> Response<V, E> failure(E e, V v) {
        return new Response<>().setSuccessfull(false).setError(e).setValue(v);
    }

    public boolean isSuccessfull() {
        return isSuccessfull;
    }

    public Response setSuccessfull(boolean successfull) {
        isSuccessfull = successfull;
        return this;
    }

    public V getValue() {
        return value;
    }

    public Response setValue(V value) {
        this.value = value;
        return this;
    }

    public E getError() {
        return error;
    }

    public Response setError(E error) {
        this.error = error;
        return this;
    }
}
