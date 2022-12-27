package com.wyh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wyh.dao.CheckGroupDao;
import com.wyh.pojo.CheckGroup;
import com.wyh.result.PageResult;
import com.wyh.result.QueryPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service(interfaceClass = CheckGroupService.class)
@org.springframework.stereotype.Service
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    private final CheckGroupDao checkGroupDao;

    @Autowired
    public CheckGroupServiceImpl(CheckGroupDao checkGroupDao) {
        this.checkGroupDao = checkGroupDao;
    }

    /**
     * @description 新增检查组，同时需要检查组管理检查项
     * @return [void]
     * @date [2022/12/14 0014 20:21]
     */
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkietmIds) {
        //新增检查组，操作t_checkgroup表
        checkGroupDao.add(checkGroup);
        //设置检查组和检查项的多对多的关联关系，操作t_checkgroup_checkitem表
        this.setCheckGroupAndCheckItem(checkGroup.getId(),checkietmIds);
    }

    /**
     * @description 编辑检查组，同时需要检查组管理检查项
     * @return [void]
     * @date [2022/12/14 0014 20:21]
     */
    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkietmIds) {
        //修改检查组的基本信息
        checkGroupDao.edit(checkGroup);
        //清理掉之前的检查项关联
        checkGroupDao.deleteAssociation(checkGroup.getId());
        //重新建立关联
        this.setCheckGroupAndCheckItem(checkGroup.getId(), checkietmIds);
    }

    /**
     * @description 设置检查项和检查组的对应关系
     * @return [void]
     * @date [2022/12/14 0014 20:21]
     */
    public void setCheckGroupAndCheckItem(Integer checkgroupId,Integer[] checkietmIds) {
        if (checkietmIds != null && checkietmIds.length > 0) {
            for (Integer checkietmId : checkietmIds) {
                HashMap<String,Integer> map = new HashMap<>();
                map.put("checkgroupId",checkgroupId);
                map.put("checkitemId",checkietmId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }

    /**
     * @description 新增检查组，同时需要检查组管理检查项
     * @return [void]
     * @date [2022/12/14 0014 20:21]
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //利用Mybatis提供的分页组件完成分页查询
        //利用线程绑定机制，传入的参数动态的绑定的查询的sql语句上，加上了limit关键字：select * from t_checkitem limit 1 10;
        String queryString = queryPageBean.getQueryString();
        if (queryString != null && queryString.length() > 0) {
            PageHelper.startPage(1, queryPageBean.getPageSize());
        } else {
            PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        }
        Page<CheckGroup> page = checkGroupDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public ArrayList<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }
}
