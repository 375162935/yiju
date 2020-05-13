package cn.yyn.yiju.service;

import cn.yyn.yiju.bean.UserAuth;
import cn.yyn.yiju.bean.UserInfo;

/**
 * @author 杨以诺
 * by 2020-04-25 18:10
 */
public interface UserService {

    //登录
    UserInfo findUserByPhone(String phone);

    //注册
    Integer register(String phone,String password);

    Integer saveEdit(UserInfo userInfo);

    UserAuth findUserAuthById(Integer userId);

    Integer saveAuth(UserAuth userAuth);

    Integer deleteAuthById(Integer userId);

    Integer updPassword(UserInfo userInfo);
}
