package com.wyh.dao;

import com.github.pagehelper.Page;
import com.wyh.pojo.CheckItem;
import com.wyh.result.QueryPageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * @date [2022/12/14 0014 14:58]
 * @description 检查项数据层接口
 */
@Mapper
public interface CheckItemDao {
    void add(CheckItem checkItem);

    /**
     * @description 分页查询方法
     * @return [com.github.pagehelper.Page<com.wyh.pojo.CheckItem>] 返回值必须是这个 因为分页查询的组件用的是mybatis的pageHelper
     * @date [2022/12/14 0014 16:55]
     */
    Page<CheckItem> selectByCondition(String queryString);
    void delete(Integer id);
    long findCountByCheckItemId(Integer id);
    void edit(CheckItem checkItem);
    CheckItem findById(Integer id);
    ArrayList<CheckItem> findAll();
}
