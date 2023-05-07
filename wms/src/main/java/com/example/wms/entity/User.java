package com.example.wms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yy
 * @since 2023-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 账号
     */
    private String no;

    private String name;

    private String password;

    private Integer age;

    private Integer sex;

    private String phone;

    /**
     * 角色  0超级管理员，1管理员，2普通账号
     */
    private Integer roleId;

    /**
     * 是否有效Y有效
     */
    @TableField("isValid")
    private String isvalid;


}
