package com.daxia.core.web.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.daxia.core.common.SystemConfigType;
import com.daxia.core.web.controller.BaseController;

/**
 * 以Admin开头的Controller，都是只为管理后台提供服务的
 */
@Controller
@RequestMapping(value = "/admin/image", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class AdminImageController extends BaseController {

	@ResponseBody
	@RequestMapping(value = "upload")
	public String upload(HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;  
		MultipartFile mf = mhs.getFile("filedata");  
		String fileName = mf.getOriginalFilename();
		fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
		File file = new File(systemConfigService.get(SystemConfigType.ImagePath) + "/" + fileName);
		mf.transferTo(file);
		String msg = "{\"err\":\"\",\"msg\":\"" + request.getSession().getAttribute("ctx").toString() + "/admin/image/download/" + fileName + "\"}";
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "download/{fileName:.*}")
	public void download(HttpServletResponse response, @PathVariable String fileName) throws IOException {
		File file = new File(systemConfigService.get(SystemConfigType.ImagePath) + "/" + fileName);
		ServletOutputStream outStream = response.getOutputStream();// 得到向客户端输出二进制数据的对象  
        FileInputStream fis = new FileInputStream(file); // 以byte流的方式打开文件  
        // 读数据  
        byte data[] = new byte[1000];  
        while (fis.read(data) > 0) {  
            outStream.write(data);  
        }  
        fis.close();
        response.setContentType("image/*"); // 设置返回的文件类型  
        outStream.write(data); // 输出数据  
        outStream.close();  
	}
	
}