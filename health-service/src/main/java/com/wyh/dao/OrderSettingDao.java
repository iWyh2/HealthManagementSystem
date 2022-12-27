package com.wyh.dao;

import com.wyh.pojo.OrderSetting;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    void add(OrderSetting orderSetting);//插入操作
    void editNumberByOrderDate(OrderSetting orderSetting);//编辑操作 根据日期
    long findCountByOrderDate(Date orderDate);//根据日期查询
    List<OrderSetting> getOrderSettingByMonth(Map date);
    //根据预约日期查询预约设置信息
    OrderSetting findByOrderDate(Date orderDate);
    //更新已预约人数
    void editReservationsByOrderDate(OrderSetting orderSetting);
}