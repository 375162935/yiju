package cn.yyn.yiju.service.impl;

import cn.yyn.yiju.bean.UserAuth;
import cn.yyn.yiju.bean.UserInfo;
import cn.yyn.yiju.dao.UserDao;
import cn.yyn.yiju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 杨以诺
 * by 2020-04-25 18:11
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserInfo findUserByPhone(String phone) {
        return userDao.findUserByPhone(phone);
    }

    @Override
    public Integer register(String phone,String password) { return userDao.register(phone,password); }

    @Override
    public Integer saveEdit(UserInfo userInfo) { return userDao.saveEdit(userInfo); }

    @Override
    public UserAuth findUserAuthById(Integer userId) { return this.userDao.findUserAuthById(userId); }

    @Override
    public Integer saveAuth(UserAuth userAuth) { return this.userDao.saveAuth(userAuth); }

    @Override
    public Integer deleteAuthById(Integer userId) { return this.userDao.deleteAuthById(userId); }

    @Override
    public Integer updPassword(UserInfo userInfo) { return this.userDao.updPassword(userInfo); }
}
