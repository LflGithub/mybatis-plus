package com.example.mybatisplus.model.gie.controller;

import com.example.mybatisplus.common.annotation.RequestJson;
import com.example.mybatisplus.model.gie.entity.User;
import com.example.mybatisplus.model.gie.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * gis_用户表 控制类
 *
 * @author LIFULIN
 * @since 2020-09-04
 */
@RestController
@RequestMapping("/gie/user")
public class UserController {

    @Autowired(required = false)
    private IUserService IUserService;

    /**
     *  获取列表
     *
     * @param currPage 当前页数
     * @param pageSize 每页记录数
     * @return
     * @author LIFULIN
     **/
    @RequestMapping(value = "/listForUser", method = RequestMethod.POST)
    public Object listForUser(@RequestJson(required = true) int currPage,@RequestJson(required = true) int pageSize){
        return IUserService.listForUser(currPage, pageSize);
    }

    /**
     * 新增记录
     *
     * @param  User gis_用户表
     * @return
     * @author LIFULIN
     */
    @RequestMapping(value = "/insertForUser", method = RequestMethod.POST)
    public Object insertForUser(@RequestBody User User){
        return IUserService.insertForUser(User);
    }

    /**
     * 修改记录
     *
     * @param  User gis_用户表
     * @return
     * @author LIFULIN
     */
    @RequestMapping(value = "/updateByUserId", method = RequestMethod.POST)
    public Object updateByUserId(@RequestBody User User){
        return IUserService.updateByUserId(User);
    }

    /**
     * 获取单个id信息
     *
     * @param userId gis 用户表的编号
     * @return
     * @author LIFULIN
     */
    @RequestMapping(value = "infoById", method = RequestMethod.POST)
    public Object infoById(@RequestJson(required = true) String userId){
        return IUserService.infoById(userId);
    }

    /**
     * 批量删除记录
     *
     * @param userIds gis 用户表的编号
     * @return
     * @author LIFULIN
     */
    @RequestMapping(value = "/deleteBatchByIds", method = RequestMethod.POST)
    public Object deleteBatchByIds(@RequestJson(required = true) List<String> userIds){
        return IUserService.deleteBatchByIs(userIds);
    }

}

