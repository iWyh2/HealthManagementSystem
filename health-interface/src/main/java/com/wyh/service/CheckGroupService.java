package com.wyh.service;

import com.wyh.pojo.CheckGroup;
import com.wyh.result.PageResult;
import com.wyh.result.QueryPageBean;

import java.util.ArrayList;
import java.util.List;

public interface CheckGroupService {
    void add(CheckGroup checkGroup,Integer[] checkietmIds);
    void edit(CheckGroup checkGroup,Integer[] checkietmIds);
    PageResult findPage(QueryPageBean queryPageBean);
    CheckGroup findById(Integer id);
    ArrayList<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    List<CheckGroup> findAll();
}
