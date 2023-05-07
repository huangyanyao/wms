package com.example.wms.mapper;

import com.example.wms.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yy
 * @since 2023-05-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
