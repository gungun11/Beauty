package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {


    //    // 总数量
//    private Integer totalCount;
    // 总金额
//    private BigDecimal toatlPrice;
    // 购物车中的商品
    /**
     * key 商品编号
     * value 是商品信息
     */
    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        System.out.println(map.keySet());
        System.out.println(map.values());
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", toatlPrice=" + getToatlPrice() +
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        // 遍历全部的商品信息,累加求总数量
        for (CartItem item : items.values()) {
            totalCount += item.getCount();
        }
        return totalCount;
    }


    public BigDecimal getToatlPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        // 遍历所有商品累加总金额
        for (CartItem item : items.values()) {
            totalPrice = totalPrice.add(item.getTotalPrice());
        }

        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    /**
     * 添加商品到购物车
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        // 先查看当前购物车中是否有添加的商品
        CartItem item = items.get(cartItem.getId());

        if (item == null) {
            items.put(cartItem.getId(), cartItem);
        } else {
            //修改数量
            item.setCount(item.getCount() + 1);
            // 修改总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除商品
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * 修改购物车商品数量
     *
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count) {
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            // 修改商品数量
            cartItem.setCount(count);
            // 修改总金额
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }


}
