package com.example.mybatisplus.model.gie.service;

import com.example.mybatisplus.model.gie.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * gis_用户表
 *
 * @author LIFULIN
 * @since 2020-09-04
 */
public interface IUserService extends IService<User> {

    /**
     *  获取列表
     *
     * @param currPage 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    Object listForUser(int currPage, int pageSize);

    /**
     * 新增记录
     *
     * @param User user
     * @return
     */
    Object insertForUser(User User);

    /**
     * 修改记录
     *
     * @param User user
     * @return
     */
    Object updateByUserId(User User);

    /**
     * 获取单个id信息
     *
     * @param userId gis 用户表的编号
     * @return
     */
    Object infoById(String userId);

    /**
     * 批量删除记录
     *
     * @param userIds gis 用户表的编号
     * @return
     */
    Object deleteBatchByIs(List<String> userIds);

}
