package com.jun.demo.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author: Jun
 * @date: 2019/10/8 20:56
 */
@Data
@ToString
public class OrderDetail {
    private String orderId;

    private Item item = new Item();

    public OrderDetail() {
    }

    public OrderDetail(String orderId, Item item) {
        this.orderId = orderId;
        this.item = item;
    }

}
