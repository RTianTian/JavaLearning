package icu.xuyijie.springbootthymeleaf.mapper;

import icu.xuyijie.springbootthymeleaf.dto.UserDto;
import icu.xuyijie.springbootthymeleaf.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 徐一杰
 * @date 2023/7/22 14:01
 * @description
 */
@Mapper
public interface UserMapper {
    User findUserByUsernameAndPassword(User user);
    int insert(UserDto userDto);
}
