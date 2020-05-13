package cn.yyn.yiju.controller;

import cn.yyn.yiju.bean.UserInfo;
import cn.yyn.yiju.pojo.HouseView;
import cn.yyn.yiju.service.HouseService;
import cn.yyn.yiju.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author 杨以诺
 * by 2020-04-25 20:24
 */
@Controller
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private UserService userService;

    @RequestMapping("/findFourHouse.do")
    public ModelAndView findFourHouse() {
        // 根据service分别查询当前房源所有信息
        List<HouseView> newHouse = this.houseService.findFourHouse(0);
        List<HouseView> oldHouse = this.houseService.findFourHouse(1);
        List<HouseView> rentHouse = this.houseService.findFourHouse(2);
//        UserInfo user=userService.findUserByPhone(phone);

        //响应数据并响应页面
        ModelAndView mv = new ModelAndView();
        mv.addObject("newHouse", newHouse);
        mv.addObject("oldHouse", oldHouse);
        mv.addObject("rentHouse", rentHouse);
//        mv.addObject("user",user);
        mv.setViewName("../main");
        return mv;
    }

    @RequestMapping("/findAllHouseByType.do")
    public ModelAndView findAllHouseByType(@RequestParam(defaultValue = "1") Integer page,
                                           Integer houseType) {
        List<HouseView> houseList = houseService.findAllHouseByType(page, houseType);
        //推荐房源
        List<HouseView> tjfy = houseService.findFourHouse(houseType);
        PageInfo<HouseView> pageInfo = new PageInfo<>(houseList);
        ModelAndView mv = new ModelAndView();
        switch (houseType) {
            case 0:
                mv.addObject("houseTypeName", "新房房源");
                break;
            case 1:
                mv.addObject("houseTypeName", "二手房房源");
                break;
            case 2:
                mv.addObject("houseTypeName", "租房房源");
                break;
        }
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("tjfy", tjfy);
        mv.setViewName("houses");
        return mv;
    }

    @RequestMapping("/findHouseById.do")
    public ModelAndView findHouseById(Integer houseId) {
        HouseView hv=this.houseService.findHouseById(houseId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("houseInfo",hv);
        mv.setViewName("details");
        return mv;
    }
}
