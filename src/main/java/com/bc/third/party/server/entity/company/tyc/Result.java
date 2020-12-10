package com.bc.third.party.server.entity.company.tyc;

import java.util.List;

/**
 * 列表返回值(天眼查)
 *
 * @param <T> 泛型
 * @author zhou
 */
public class Result<T> {

    private List<T> items;
    private int total;

    public void setItems(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

}
