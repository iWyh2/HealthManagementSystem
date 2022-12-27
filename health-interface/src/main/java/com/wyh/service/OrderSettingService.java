package com.wyh.service;

import com.wyh.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    void add(List<OrderSetting> list);
    List<Map> getOrderSettingByMonth(String date);
    void editNumberByDate(OrderSetting orderSetting);
}
