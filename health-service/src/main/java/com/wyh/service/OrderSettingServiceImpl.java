package com.wyh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wyh.dao.OrderSettingDao;
import com.wyh.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService{
    @Autowired
    private OrderSettingDao orderSettingDao;
    //批量添加
    public void add(List<OrderSetting> list) {
        if(list != null && list.size() > 0){
            for (OrderSetting orderSetting : list) {
                //检查此数据（日期）是否存在
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if(count > 0){
                    //已经存在，执行更新操作
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                }else{
                    //不存在，执行添加操作
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    //根据日期查询预约设置数据
    public List<Map> getOrderSettingByMonth(String date) {//2022-12
        String dateBegin = date + "-1";//2022-12-1
        String dateEnd = date + "-31";//2022-12-31
        // select * from tb where dateBegin between dateBegin and dateEnd ：不用判断每月份有多少天 最多就三十一天
        Map map = new HashMap();
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> data = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map orderSettingMap = new HashMap();
            //依据页面设置 date number reservations
            orderSettingMap.put("date",orderSetting.getOrderDate().getDate());//获得日期（几号）
            orderSettingMap.put("number",orderSetting.getNumber());//可预约人数
            orderSettingMap.put("reservations",orderSetting.getReservations());//已预约人数
            data.add(orderSettingMap);
        }
        return data;
    }

    //根据日期修改可预约人数
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if(count > 0){
            //当前日期已经进行了预约设置，需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else{
            //当前日期没有进行预约设置，进行添加操作
            orderSettingDao.add(orderSetting);
        }
    }
}
