package cn.yyn.yiju.controller;

import cn.yyn.yiju.bean.House;
import cn.yyn.yiju.bean.HouseInfo;
import cn.yyn.yiju.bean.HouseInter;
import cn.yyn.yiju.bean.UserInfo;
import cn.yyn.yiju.pojo.HouseView;
import cn.yyn.yiju.service.HouseService;
import cn.yyn.yiju.service.UserService;
import cn.yyn.yiju.util.YiJuUtil;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

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
        HouseView hv = this.houseService.findHouseById(houseId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("houseInfo", hv);
        mv.setViewName("details");
        return mv;
    }

    @RequestMapping("/toHousePost.do")
    public ModelAndView toHousePost(@RequestParam("houseTitle") String houseTitle,
                                    @RequestParam("houseAddress") String houseAddress,
                                    @RequestParam("housePrice") BigDecimal housePrice,
                                    @RequestParam("priceUnit") String priceUnit,
                                    @RequestParam("houseHeadimg") MultipartFile houseHeadimg,
                                    @RequestParam("housePlanimg1") MultipartFile housePlanimg1,
                                    @RequestParam("housePlanimg2") MultipartFile housePlanimg2,
                                    @RequestParam("houseImg1") MultipartFile houseImg1,
                                    @RequestParam("houseImg2") MultipartFile houseImg2,
                                    @RequestParam("houseImg3") MultipartFile houseImg3,
                                    @RequestParam("houseImg4") MultipartFile houseImg4,
                                    @RequestParam("houseImg5") MultipartFile houseImg5,
                                    @RequestParam("houseImg6") MultipartFile houseImg6,
                                    HttpSession session) throws IOException {
        House house = new House();
        house.setHouseTitle(houseTitle);
        house.setHouseAddress(houseAddress);
        house.setHousePrice(housePrice);
        house.setPriceUnit(priceUnit);

        Map<String,byte[]> images=new HashMap<>();
        if (houseHeadimg.getSize() != 0) {
            String pirName = YiJuUtil.getPirName();
            images.put(pirName,houseHeadimg.getBytes());
            house.setHouseHeadimg(pirName);
//            YiJuUtil.upload(houseHeadimg.getBytes(), pirName);
        }
        if (housePlanimg1.getSize() != 0) {
            String pirName = YiJuUtil.getPirName();
            images.put(pirName,housePlanimg1.getBytes());
            house.setHousePlanimg1(pirName);
        }
        if (housePlanimg2.getSize() != 0) {
            String pirName = YiJuUtil.getPirName();
            images.put(pirName,housePlanimg2.getBytes());
            house.setHousePlanimg2(pirName);
        }
        if (houseImg1.getSize() != 0) {
            String pirName = YiJuUtil.getPirName();
            images.put(pirName,housePlanimg1.getBytes());
            house.setHouseImg1(pirName);
        }
        if (houseImg2.getSize() != 0) {
            String pirName = YiJuUtil.getPirName();
            images.put(pirName,houseImg2.getBytes());
            house.setHouseImg2(pirName);
        }
        if (houseImg3.getSize() != 0) {
            String pirName = YiJuUtil.getPirName();
            images.put(pirName,houseImg3.getBytes());
            house.setHouseImg3(pirName);
        }
        if (houseImg4.getSize() != 0) {
            String pirName = YiJuUtil.getPirName();
            images.put(pirName,houseImg4.getBytes());
            house.setHouseImg4(pirName);
        }
        if (houseImg5.getSize() != 0) {
            String pirName = YiJuUtil.getPirName();
            images.put(pirName,houseImg5.getBytes());
            house.setHouseImg5(pirName);
        }
        if (houseImg6.getSize() != 0) {
            String pirName = YiJuUtil.getPirName();
            images.put(pirName,houseImg6.getBytes());
            house.setHouseImg6(pirName);
        }
        UserInfo userInfo= (UserInfo) session.getAttribute("user");
        house.setUserId(userInfo.getUserId());
        house.setCreateTime(new Date().getTime());
        house.setUpdateTime(new Date().getTime());
        this.houseService.saveHouse(house);
        session.setAttribute("house",house);
        System.out.println(house);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("housePost2");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/saveHouseInfo.do")
    public String saveHouseInfo(HouseInfo houseInfo,HttpSession session) {
        session.setAttribute("houseInfo",houseInfo);
        return new JSONObject().toString();
    }

    //增加房源第三页
    //saveHousePost3
    @ResponseBody
    @RequestMapping("/saveHousePost3.do")
    public String saveHousePost3(HouseInter houseInter,HttpSession session){
        session.setAttribute("houseInter",houseInter);
        return new JSONObject().toString();
    }
    //doHousePost
    @RequestMapping("/doHousePost.do")
    public String doHousePost(HttpSession session){
        //1.处理图片上传
        Map<String,byte[]> images = (Map<String, byte[]>) session.getAttribute("images");
        Set<String> keys = images.keySet();
        for(String key:keys){
            byte[] bytes=images.get(key);
            YiJuUtil.upload(bytes,key);
        }
        //2.处理house
        House house= (House) session.getAttribute("house");
        house.setCreateTime(new Date().getTime());
        house.setUpdateTime(new Date().getTime());
        this.houseService.saveHouse(house);
        //3.处理houseInfo
        HouseInfo houseInfo= (HouseInfo) session.getAttribute("houseInfo");
        houseInfo.setHouseId(house.getHouseId());
        houseInfo.setCreateTime(new Date().getTime());
        houseInfo.setUpdateTime(new Date().getTime());
        //4.处理houseInter
        HouseInter houseInter= (HouseInter) session.getAttribute("houseInter");
        houseInter.setHouseId(house.getHouseId());
        houseInter.setCreateTime(new Date().getTime());
        houseInter.setUpdateTime(new Date().getTime());
        return "redirect:findHouseById.do?houseId="+house.getHouseId();
    }
}
