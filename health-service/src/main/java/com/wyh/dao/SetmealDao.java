package com.wyh.dao;

import com.github.pagehelper.Page;
import com.wyh.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    void add(Setmeal setmeal);
    void setSetmealAndCheckGroup(Map<String,Integer> map);

    public Page<Setmeal> selectByCondition(String queryString);
    List<Setmeal> findAll();
    Setmeal findById(int id);
    @SuppressWarnings("MybatisXMapperMethodInspection")
    List<Map<String,Object>> findSetmealCount();
}
