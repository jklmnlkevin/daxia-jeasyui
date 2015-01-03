package com.daxia.wy.web.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.common.Log;
import com.daxia.core.common.SystemConfigType;
import com.daxia.core.dto.JsonResultDTO;
import com.daxia.core.support.Page;
import com.daxia.core.web.controller.BaseController;
import com.daxia.wy.dto.ApiTestDTO;
import com.daxia.wy.service.ApiTestService;

/**
 * 以Admin开头的Controller，都是只为管理后台提供服务的
 */
// @Module(name = "API测试模块")
@Controller
@RequestMapping(value = "/admin/apiTest", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class AdminApiTestController extends BaseController {
    private int downloadTimes = 0;
	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private ApiTestService apiTestService;
	
	/**
	 * 详情页面。用来获得单个对象的具体信息的
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			ApiTestDTO dto = apiTestService.load(id);
			map.put("apiTest", dto);
		}
		return "admin/apiTest/apiTest_detail";
	}
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@Log(operation = "保存API测试") 
	@ResponseBody
	@RequestMapping(value = "/save")
	@PreAuthorize("(hasRole('apiTest.update') and #dto.id != null) or (hasRole('apiTest.add') and #dto.id == null)")
	public String save(ApiTestDTO dto, JsonResultDTO resultDTO) throws Exception {
		if (dto.getId() == null) {
			apiTestService.create(dto);
		} else {
			apiTestService.updateAllFields(dto);
		}
		return okAndClose(resultDTO);
	}

    @Log(operation = "删除API测试")
    @PreAuthorize("hasRole('apiTest.delete')")
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids, JsonResultDTO resultDTO) throws IOException {
		apiTestService.deleteByIds(ids);
		return okAndRefresh(resultDTO);
	}
	
	@RequestMapping(value = "/list")
	@PreAuthorize("hasRole('apiTest.list')")
	public String list(ApiTestDTO dto, Map<String, Object> map, Page page) {
		List<ApiTestDTO> dtos = apiTestService.find(dto, page);
		// 这个数据是用来展示的
		map.put("apiTests", dtos);
		// 这个数据是保存查询条件的
		map.put("apiTest", dto);
		return "admin/apiTest/apiTest_list";
	}
	
	/**
	 * 用于查找带回的
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("search")
	public String search(ApiTestDTO dto, Map<String, Object> map, Page page) {
		List<ApiTestDTO> dtos = apiTestService.find(dto, page);
		// 这个数据是用来展示的
		map.put("apiTests", dtos);
		// 这个数据是保存查询条件的
		map.put("apiTest", dto);
		return "admin/apiTest/apiTest_search";
	}
    
    
    @RequestMapping("getDoc")
    public String getDoc(HttpServletResponse response, Map<String, Object> map) throws Exception {
        File file = new File(systemConfigService.get(SystemConfigType.ApiDocPath));
        Date date = new Date(file.lastModified());
        map.put("date", date);
        map.put("downloadTimes", downloadTimes);
        return "admin/apiTest/apiTest_doc";
    }
    
    @ResponseBody
    @RequestMapping("doc")
    public void doc(HttpServletResponse response) throws Exception {
        downloadTimes ++;
        File file = new File(systemConfigService.get(SystemConfigType.ApiDocPath));
        response.setContentType("application/x-msdownload"); // 设置返回的文件类型  
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        
        ServletOutputStream outStream = response.getOutputStream();// 得到向客户端输出二进制数据的对象  
        FileInputStream fis = new FileInputStream(file); // 以byte流的方式打开文件  
        // 读数据  
        byte data[] = new byte[1000];  
        while (fis.read(data) > 0) {  
            outStream.write(data);  
        }  
        fis.close();
        outStream.write(data); // 输出数据  
        outStream.close();  
    }
}
