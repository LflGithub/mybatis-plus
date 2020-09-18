package com.example.mybatisplus.model.gie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.gie.entity.User;
import com.example.mybatisplus.model.gie.mapper.UserMapper;
import com.example.mybatisplus.model.gie.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * gis_用户表 实现类
 *
 * @author LIFULIN
 * @since 2020-09-04
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public Object listForUser(int currPage, int pageSize) {
        QueryWrapper queryWrapper = new QueryWrapper();
        //不分页
        if (currPage == 0 && pageSize == 0) {
            return this.list(queryWrapper);
        }
        //分页
        else {
            return this.page(new Page<>(currPage, pageSize), queryWrapper);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object insertForUser(User User) {
        if (this.save(User)) {
            return "新增成功";
        } else {
            return "新增失败";
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object updateByUserId(User User) {
        if (this.updateById(User)) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @Override
    public Object infoById(String userId) {
        if (userId == null) {
            return "id不能为空";
        } else {
            return this.getById(userId);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object deleteBatchByIs(List<String> userIds) {
        if (this.removeByIds(userIds)) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}
