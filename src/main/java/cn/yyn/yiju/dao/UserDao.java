package cn.yyn.yiju.dao;

import cn.yyn.yiju.bean.UserAuth;
import cn.yyn.yiju.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author 杨以诺
 * by 2020-04-25 17:37
 */
public interface UserDao {
    //登录
    UserInfo findUserByPhone(String phone);

    //注册
    Integer register(@Param("phone") String phone,@Param("password") String password);

    Integer saveEdit(UserInfo userInfo);

    UserAuth findUserAuthById(Integer userId);

    Integer saveAuth(UserAuth userAuth);

    Integer deleteAuthById(Integer userId);

    Integer updPassword(UserInfo userInfo);
}
