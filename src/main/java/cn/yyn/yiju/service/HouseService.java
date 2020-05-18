package cn.yyn.yiju.service;

import cn.yyn.yiju.bean.House;
import cn.yyn.yiju.bean.HouseInfo;
import cn.yyn.yiju.bean.HouseInter;
import cn.yyn.yiju.pojo.HouseView;

import java.util.List;

/**
 * @author 杨以诺
 * by 2020-04-25 20:49
 */
public interface HouseService {

    // 查询房源信息
    List<HouseView> findFourHouse(Integer houseType);

    // 房源详情
    // page - 代表当前页
    List<HouseView> findAllHouseByType(Integer page,Integer houseType);

    HouseView findHouseById(Integer houseId);

    void saveHousePost1(House house);

    void saveHousePost2(HouseInfo houseInfo);

    void saveHousePost3(HouseInter houseInter);
}
