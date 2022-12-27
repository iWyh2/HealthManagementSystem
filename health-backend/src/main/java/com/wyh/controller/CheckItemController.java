package com.wyh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wyh.constant.MessageConstant;
import com.wyh.pojo.CheckItem;
import com.wyh.result.PageResult;
import com.wyh.result.QueryPageBean;
import com.wyh.result.Result;
import com.wyh.service.CheckItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @description 检查项的Controller
 * @date [2022/12/14 0014 14:47]
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference//由dubbo提供，在注册中心中寻找服务
    private CheckItemService checkItemService;

    /**
     * @description 新增检查项
     * @return [com.wyh.result.Result]
     * @date [2022/12/14 0014 14:51]
     */
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.add(checkItem);
        } catch (Exception e) {//企业做法，出现了异常，那么就是新增失败，返回前端信息中包含失败信息
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * @description 分页查询
     * @return [com.wyh.result.PageResult]
     * @date [2022/12/14 0014 16:40]
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkItemService.findPage(queryPageBean);
    }

    /**
     * @description 检查项删除
     * @return [com.wyh.result.Result]
     * @date [2022/12/14 0014 17:38]
     */
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")//权限校验
    public Result delete(Integer id) {
        try {
            checkItemService.delete(id);
        } catch (Exception e) {//企业做法，出现了异常，那么就是新增失败，返回前端信息中包含失败信息
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    /**
     * @description 检查项编辑
     * @return [com.wyh.result.Result]
     * @date [2022/12/14 0014 18:20]
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.edit(checkItem);
        } catch (Exception e) {//企业做法，出现了异常，那么就是新增失败，返回前端信息中包含失败信息
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    /**
     * @description 检查项根据id查找
     * @return [com.wyh.result.Result]
     * @date [2022/12/14 0014 18:20]
     */
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            CheckItem checkItem = checkItemService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
        } catch (Exception e) {//企业做法，出现了异常，那么就是新增失败，返回前端信息中包含失败信息
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    /**
     * @description 查询所有检查项
     * @return [com.wyh.result.Result]
     * @date [2022/12/14 0014 18:20]
     */
    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            ArrayList<CheckItem> checkItems= checkItemService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItems);
        } catch (Exception e) {//企业做法，出现了异常，那么就是新增失败，返回前端信息中包含失败信息
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
}
