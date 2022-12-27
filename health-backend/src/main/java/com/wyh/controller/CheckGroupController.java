package com.wyh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wyh.constant.MessageConstant;
import com.wyh.pojo.CheckGroup;
import com.wyh.result.PageResult;
import com.wyh.result.QueryPageBean;
import com.wyh.result.Result;
import com.wyh.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @date [2022/12/14 0014 20:00]
 * @description 检查组Controller
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    /**
     * @description 新增检查组
     * @return [com.wyh.result.Result]
     * @date [2022/12/14 0014 20:02]
     */
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkItemIds) {
        try {
            checkGroupService.add(checkGroup,checkItemIds);
        } catch (Exception e) {//企业做法，出现了异常，那么就是新增失败，返回前端信息中包含失败信息
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * @description 分页查询检查组
     * @return [com.wyh.result.Result]
     * @date [2022/12/14 0014 20:02]
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkGroupService.findPage(queryPageBean);
    }

    /**
     * @description 按id查询检查组
     * @return [com.wyh.result.Result]
     * @date [2022/12/14 0014 20:02]
     */
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        } catch (Exception e) {//企业做法，出现了异常，那么就是新增失败，返回前端信息中包含失败信息
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }

    }

    /**
     * @description 按id查询检查组的查询项们的id
     * @return [com.wyh.result.Result]
     * @date [2022/12/14 0014 20:02]
     */
    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer id) {
        try {
            ArrayList<Integer> list = checkGroupService.findCheckItemIdsByCheckGroupId(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        } catch (Exception e) {//企业做法，出现了异常，那么就是查询失败，返回前端信息中包含失败信息
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    /**
     * @description 编辑检查组
     * @return [com.wyh.result.Result]
     * @date [2022/12/14 0014 20:02]
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkItemIds) {
        try {
            checkGroupService.edit(checkGroup,checkItemIds);
        } catch (Exception e) {//企业做法，出现了异常，那么就是新增失败，返回前端信息中包含失败信息
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    /**
     * @description 查询所有检查组
     * @return [com.wyh.result.Result]
     * @date [2022/12/14 0014 20:02]
     */
    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<CheckGroup> list = checkGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        } catch (Exception e) {//企业做法，出现了异常，那么就是新增失败，返回前端信息中包含失败信息
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }

    }
}
