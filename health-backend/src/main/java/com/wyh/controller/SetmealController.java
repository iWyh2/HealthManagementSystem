package com.wyh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wyh.constant.MessageConstant;
import com.wyh.constant.RedisConstant;
import com.wyh.pojo.Setmeal;
import com.wyh.result.PageResult;
import com.wyh.result.QueryPageBean;
import com.wyh.result.Result;
import com.wyh.service.SetmealService;
import com.wyh.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private JedisPool jedisPool;
    @Reference
    private SetmealService setmealService;
    
    /**
     * @author iWyh2
     * @date [2022/12/15 0015 19:01]
     * @description 接收前端发送过来的文件 并上传到七牛云（使用了SpringMVC的文件上传组件）
     */
    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        try {
            String originalFilename = imgFile.getOriginalFilename();//获取原始文件名
            String fileLastName = originalFilename.substring(originalFilename.lastIndexOf(".")-1);//获取文件后缀名
            String fileName = UUID.randomUUID().toString() + fileLastName;//随机生成文件名+文件后缀
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            //将上传图片名称存入Redis，基于Redis的Set集合存储
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkGroupIds) {
        try {
            setmealService.add(setmeal,checkGroupIds);
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return setmealService.pageQuery(queryPageBean);
    }
}
