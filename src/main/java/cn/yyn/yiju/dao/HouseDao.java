package cn.yyn.yiju.dao;

import cn.yyn.yiju.bean.House;
import cn.yyn.yiju.bean.HouseInfo;
import cn.yyn.yiju.bean.HouseInter;
import cn.yyn.yiju.pojo.HouseView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 杨以诺
 * by 2020-04-25 20:43
 */
public interface HouseDao {

    // 查询房源信息
    List<HouseView> findFourHouse(@Param("houseType") Integer houseType);

    List<HouseView> findAllHouseByType(@Param("houseType") Integer houseType);

    HouseView findHouseById(@Param("houseId") Integer houseId);

    void saveHousePost1(House house);

    void saveHousePost2(HouseInfo houseInfo);

    void saveHousePost3(HouseInter houseInter);
}
