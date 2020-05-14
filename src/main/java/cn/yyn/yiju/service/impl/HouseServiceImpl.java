package cn.yyn.yiju.service.impl;

import cn.yyn.yiju.bean.House;
import cn.yyn.yiju.bean.HouseInfo;
import cn.yyn.yiju.bean.HouseInter;
import cn.yyn.yiju.dao.HouseDao;
import cn.yyn.yiju.pojo.HouseView;
import cn.yyn.yiju.service.HouseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨以诺
 * by 2020-04-25 20:50
 */
@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseDao houseDao;

    @Override
    public List<HouseView> findFourHouse(Integer houseType) {
        try {
            List<HouseView> list = houseDao.findFourHouse(houseType);
            for (HouseView houseView : list) {
                System.out.println(houseView);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this.houseDao.findFourHouse(houseType);
    }

    @Override
    public List<HouseView> findAllHouseByType(Integer page, Integer houseType) {
        PageHelper.startPage(page, 5);
        return this.houseDao.findAllHouseByType(houseType);
    }

    @Override
    public HouseView findHouseById(Integer houseId) {
        return this.houseDao.findHouseById(houseId);
    }

    @Override
    public void saveHouse(House house) {
        this.houseDao.saveHouse(house);
    }

    @Override
    public void saveHouseInfo(HouseInfo houseInfo) {
        this.houseDao.saveHouseInfo(houseInfo);
    }

    @Override
    public void saveHousePost3(HouseInter houseInter) {
        this.houseDao.saveHousePost3(houseInter);
    }
}
