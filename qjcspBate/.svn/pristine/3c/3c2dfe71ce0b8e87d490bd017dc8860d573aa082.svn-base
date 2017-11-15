package com.qjkj.qjcsp.service.machine;

import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.ThisMachineOfGoodsMapper;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/21.
 */
@Service
public class ThisMachineOfGoodsService {

    private static final Logger logger = LoggerFactory.getLogger(ThisMachineOfGoodsService.class);
    @Autowired
    private BasicsMachineMapper basicsMachineMapper;
    @Autowired
    private ThisMachineOfGoodsMapper thisMachineOfGoodsMapper;
    
    public Map<String,Object> getThisMachineOfGoodsService(JSONObject req)
    {
        Map<String,Object> returnJson=new HashMap<String, Object>();
        //查询参数map
        Map<String,Object> map=new HashMap<String, Object>();
        //返回值
        List<Map> returnContent=new ArrayList<Map>();
        String deviceCode=req.getString("deviceCode");
        if (deviceCode!=null&& StringUtils.isNoneBlank(deviceCode.toString())) {
            long areaModelId;
            areaModelId = basicsMachineMapper.getAreaModelbyDeviceCode(deviceCode);
            map.put("areaModelId", areaModelId);
            map.put("deviceCode", deviceCode);
            //通过参数找到categoryID
            List<Long> list = thisMachineOfGoodsMapper.getCategoryByMachineId(map);
            for (Long goodsCategoryId : list) {
                //将要包装到最后返回List中的map
                Map<String, Object> map5 = new HashMap<String, Object>();
                //将categoryID放入查询map中
                map.put("goodsCategoryId", goodsCategoryId);
                //将categoryId放入map5即需要包装到最外层的List中
                map5.put("goodsCategoryId", goodsCategoryId);
                //传递areaModelId参
                //通过ID获取categoryName
                map5.put("goodsCategoryName", thisMachineOfGoodsMapper.getCategoryNameByMachineId(map));
                //通过参数获取ID和名称
                List<Map> list3 = thisMachineOfGoodsMapper.getGoodsInfoByMachineId(map);
                map5.put("goodsList", list3);
                returnContent.add(map5);

            }
            returnJson.put("requestMethod", "getThisMachineOfGoods");
            returnJson.put("returnCode", "1");
            returnJson.put("returnContent",returnContent);
        }
        else {
        	returnJson.put("requestMethod", "getThisMachineOfGoods");
        	returnJson.put("returnCode", "0");
            returnJson.put("returnContent","参数错误");
        }

        return returnJson;
    }
}
