package com.bc.third.party.server.entity.company.tyc;

import java.util.List;

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
