package com.wyh.service;

import com.wyh.pojo.CheckItem;
import com.wyh.result.PageResult;
import com.wyh.result.QueryPageBean;

import java.util.ArrayList;

/**
 * @date [2022/12/14 0014 14:55]
 * @description 检查项服务接口
 */
public interface CheckItemService {
    void add(CheckItem checkItem);
    PageResult findPage(QueryPageBean queryPageBean);
    void delete(Integer id) throws Exception;
    void edit(CheckItem checkItem);
    CheckItem findById(Integer id);
    ArrayList<CheckItem> findAll();
}
