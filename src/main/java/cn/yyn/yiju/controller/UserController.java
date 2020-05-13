package cn.yyn.yiju.controller;

import cn.yyn.yiju.bean.UserAuth;
import cn.yyn.yiju.bean.UserInfo;
import cn.yyn.yiju.service.UserService;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author 杨以诺
 * by 2020-04-25 18:29
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/login.do")
    public String doLogin(@Param(value = "phone") String phone,
                          @Param(value = "password") String password,
                          HttpSession session) {
        // 1.查不到用户的时候，提醒注册
        // 2.查得到，但是密码不正确 ---- 重新登陆
        // 3.密码正确，登陆成功
        JSONObject jsonObject = new JSONObject();
        UserInfo user = this.userService.findUserByPhone(phone);
        if (user == null) {
            System.out.println("用户不存在");
            jsonObject.put("result", "0");
        } else if (!password.equals(user.getPassword())) {
            System.out.println("密码错误");
            jsonObject.put("result", "1");
        } else {
            System.out.println("登陆成功");
            session.setAttribute("user", user);
            jsonObject.put("result", phone);
        }
        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping("/register.do")
    public String register(@RequestParam("phone") String phone,
                           @RequestParam("password") String password) {
        JSONObject jsonObject = new JSONObject();
        UserInfo userInfo = userService.findUserByPhone(phone);
        if (userInfo != null) {
            System.out.println("该用户已注册");
            jsonObject.put("result", "0");
        } else {
            //正常注册
            Integer i = userService.register(phone, password);
            if (i > 0) {
                jsonObject.put("result", "1");
                System.out.println("注册成功");
            } else {
                System.out.println("注册失败");
            }
        }
        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping("/saveEdit.do")
    public String saveEdit(UserInfo userInfo, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        Integer i = this.userService.saveEdit(userInfo);
        session.setAttribute("user", userInfo);
        return jsonObject.toString();
    }

    //实名认证
    @RequestMapping("/verify.do")
    public ModelAndView verify(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        UserAuth userAuth = this.userService.findUserAuthById(userInfo.getUserId());
        ModelAndView mv = new ModelAndView();
        mv.addObject("userAuth", userAuth);
        mv.setViewName("verify");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/saveAuth.do")
    public String saveAuth(UserAuth userAuth, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");

        userAuth.setUserName(userInfo.getTruename());
        userAuth.setUserGender(userInfo.getGender());
        userAuth.setUserId(userInfo.getUserId());
        userAuth.setCreateTime(new Date().getTime());
        userAuth.setUpdateTime(new Date().getTime());

        JSONObject jsonObject = new JSONObject();
        Integer i = this.userService.saveAuth(userAuth);
        return jsonObject.toString();
    }

    @RequestMapping("/deleteAuthById.do")
    public String deleteAuthById(HttpSession session){
        UserInfo userInfo= (UserInfo) session.getAttribute("user");
        this.userService.deleteAuthById(userInfo.getUserId());
        return "personal";
    }

    @ResponseBody
    @RequestMapping("/updPasswordById.do")
    public String updPasswordById(@RequestParam("oldpassword") String oldpassword,
                                  @RequestParam("password") String password,
                                  HttpSession session){
        UserInfo userInfo= (UserInfo) session.getAttribute("user");
        JSONObject jsonObject = new JSONObject();
        if (!userInfo.getPassword().equals(oldpassword)){
            jsonObject.put("result","0");
        }else {
            userInfo.setPassword(password);
            this.userService.updPassword(userInfo);
            jsonObject.put("result","1");
        }

        return jsonObject.toString();
    }

    @RequestMapping("/edit.do")
    public String edit(HttpSession session) {
        try {
//            session.removeAttribute("phone");
//            session.removeAttribute("trueName");
            session.removeAttribute("user");
        } catch (Exception e) {

        }
        return "redirect:/";
    }
}
