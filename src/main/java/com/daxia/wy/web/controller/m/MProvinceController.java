package com.daxia.wy.web.controller.m;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daxia.wy.dto.DistrictDTO;
import com.daxia.wy.dto.ProvinceDTO;
import com.daxia.wy.dto.api.ProvinceAPIDTO;
import com.daxia.wy.dto.api.info.ProvinceInfoAPIDTO;
import com.daxia.wy.model.City;
import com.daxia.wy.model.District;
import com.daxia.wy.model.Province;
import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "/m/province", produces = "text/html;charset=UTF-8")
public class MProvinceController extends MBaseController {
    
    @ResponseBody
    @RequestMapping("list")
    public String list(ProvinceDTO dto) throws Exception {
        List<ProvinceDTO> provinces = provinceService.find(dto, null);

        return toApiOutput(provinces, ProvinceAPIDTO.class, ProvinceInfoAPIDTO.class);
    }

    @ResponseBody
    @RequestMapping("version")
    public String version() throws Exception {
        JSONObject object = new JSONObject();
        object.put("version", 1);
        return toJson(object);
    }
    
    @ResponseBody
    @RequestMapping("allData")
    public String allData() throws Exception {
        List<DistrictDTO> districts = districtService.find(new DistrictDTO(), null);
        
        // key: provinceId:
        Map<Province, List<City>> cityMap = new HashMap<Province, List<City>>();
        // key: cityId
        Map<City, List<District>> districtMap = new HashMap<City, List<District>>();
        
        List<Province> provinces = Lists.newArrayList();
        for (DistrictDTO d : districts) {
            Province p = d.getCity().getProvince();
            City c = d.getCity();
            
            List<City> cityList = cityMap.get(p);
            if (cityList == null) {
                cityList = Lists.newArrayList();
                cityMap.put(p, cityList);
                if (!provinces.contains(p)) {
                    provinces.add(p);
                }
            }
            if (!cityList.contains(c)) { 
                cityList.add(c);
            }
            
            List<District> districtList = districtMap.get(c);
            if (districtList == null) {
                districtList = Lists.newArrayList();
                districtMap.put(c, districtList);
            }
            if (!districtList.contains(d)) {
                districtList.add(d);
            }
        }
        
        JSONArray pArray = new JSONArray();
        for (Province p : provinces) {
            JSONObject pJsonObj = new JSONObject();
            pJsonObj.put("id", p.getId());
            pJsonObj.put("name", p.getName());
            pJsonObj.put("letter", p.getLetter());
            pJsonObj.put("fullLetter", p.getFullLetter());
            
            List<City> cList = cityMap.get(p);
            JSONArray cArray = new JSONArray();
            for (City c : cList) {
                JSONObject cJsonObj = new JSONObject();
                cJsonObj.put("id", c.getId());
                cJsonObj.put("name", c.getName());
                cJsonObj.put("hot", c.isHot() ? "true" : "false");
                cJsonObj.put("letter", c.getLetter());
                cJsonObj.put("fullLetter", c.getFullLetter());
                
                List<District> dList = districtMap.get(c);
                JSONArray dArray = new JSONArray();
                for (District d : dList) {
                    JSONObject dJsonObject = new JSONObject();
                    dJsonObject.put("id", d.getId());
                    dJsonObject.put("name", d.getName());
                    dJsonObject.put("letter", d.getLetter());
                    dJsonObject.put("fullLetter", d.getFullLetter());
                    
                    dArray.add(dJsonObject);
                }
                cJsonObj.put("districts", dArray);
                cArray.add(cJsonObj);
            }
            pJsonObj.put("cities", cArray);
            pArray.add(pJsonObj);
        }
        JSONObject provinceObj = new JSONObject();
        provinceObj.put("provinces", pArray);
                
        return toJson(provinceObj);
    }
}
