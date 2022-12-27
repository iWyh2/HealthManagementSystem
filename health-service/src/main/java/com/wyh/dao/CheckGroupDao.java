package com.wyh.dao;

import com.github.pagehelper.Page;
import com.wyh.pojo.CheckGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface CheckGroupDao {
    void add(CheckGroup checkGroup);
    void edit(CheckGroup checkGroup);
    void setCheckGroupAndCheckItem(HashMap<String, Integer> map);
    Page<CheckGroup> selectByCondition(String queryString);
    CheckGroup findById(Integer id);
    ArrayList<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    void deleteAssociation(Integer id);
    List<CheckGroup> findAll();
}
