package com.wyh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wyh.dao.CheckItemDao;
import com.wyh.pojo.CheckItem;
import com.wyh.result.PageResult;
import com.wyh.result.QueryPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @description 检查项服务实现 - 服务提供者
 * @date [2022/12/14 0014 14:55]
 */
@org.springframework.stereotype.Service
@Service(interfaceClass = CheckItemService.class)//dubbo需要指定实现的是哪个服务接口
@Transactional//开启Spring事务
public class CheckItemServiceImpl implements CheckItemService{

    private final CheckItemDao checkItemDao;

    @Autowired
    public CheckItemServiceImpl(CheckItemDao checkItemDao) {
        this.checkItemDao = checkItemDao;
    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

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
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void delete(Integer id) throws Exception{
        //判断当前检查项是否已经关联到了检查组，有的话是不允许删除的
        if (checkItemDao.findCountByCheckItemId(id) > 0) {
            throw new RuntimeException();
        } else {
            checkItemDao.delete(id);
        }
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    @Override
    public ArrayList<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
