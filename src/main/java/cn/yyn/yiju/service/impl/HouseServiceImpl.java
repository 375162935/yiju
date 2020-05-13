package cn.yyn.yiju.service.impl;

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
        List<HouseView> list=houseDao.findFourHouse(houseType);
        for (HouseView houseView:list){
            System.out.println(houseView);
        }
        return this.houseDao.findFourHouse(houseType);
    }

    @Override
    public List<HouseView> findAllHouseByType(Integer page, Integer houseType) {
        PageHelper.startPage(page,5);
        return this.houseDao.findAllHouseByType(houseType);
    }

    @Override
    public HouseView findHouseById(Integer houseId) {
        return this.houseDao.findHouseById(houseId);
    }
}
