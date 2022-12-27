package com.wyh.service;

import com.wyh.pojo.Setmeal;
import com.wyh.result.PageResult;
import com.wyh.result.QueryPageBean;

import java.util.List;
import java.util.Map;

public interface SetmealService {
    void add(Setmeal setmeal,Integer[] checkGroupIds);
    PageResult pageQuery(QueryPageBean queryPageBean);
    List<Setmeal> findAll();
    Setmeal findById(int id);
    List<Map<String,Object>> findSetmealCount();
}
