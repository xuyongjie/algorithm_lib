package com.yon.dag;

import java.util.Map;
import java.util.Set;

public class DAG<T> {

    /**
     * 出度
     */
    private Map<Vertex<T>, Set<Vertex<T>>> outMap;

    /**
     * 入度
     */
    private Map<Vertex<T>,Set<Vertex<T>>> inMap;

    public static class Vertex<T>{
        private String id;
        private T data;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
