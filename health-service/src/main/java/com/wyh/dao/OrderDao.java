package com.wyh.dao;

import com.wyh.pojo.Order;
import java.util.List;
import java.util.Map;

public interface OrderDao {
    public void add(Order order);
    public List<Order> findByCondition(Order order);
    @SuppressWarnings("MybatisXMapperMethodInspection")
    public Map findById4Detail(Integer id);
    public Integer findOrderCountByDate(String date);
    public Integer findOrderCountAfterDate(String date);
    public Integer findVisitsCountByDate(String date);
    public Integer findVisitsCountAfterDate(String date);
    @SuppressWarnings("MybatisXMapperMethodInspection")
    public List<Map> findHotSetmeal();
}
