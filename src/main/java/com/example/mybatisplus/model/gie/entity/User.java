package com.example.mybatisplus.model.gie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * gis_用户表
 *
 * @author LIFULIN
 * @since 2020-09-04
 */
@Data
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * gis 用户表的编号
    */
    @TableId(value = "user_id", type = IdType.INPUT)
    private String userId;

    /**
    * 用户的账号、不可重复
    */
    private String account;

    /**
    * 用户加密后的密码
    */
    private String password;

    /**
    * shiro加密用到的盐值、使用uuid 自动生成
    */
    private String salt;

    /**
    * 明文密码 、暂时使用
    */
    private String pd;

    /**
    * 身份证
    */
    @TableField("idCard")
    private String idCard;

}
