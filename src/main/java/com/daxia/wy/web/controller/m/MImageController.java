package com.daxia.wy.web.controller.m;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.alibaba.fastjson.JSONObject;
import com.daxia.core.common.SystemConfigType;

@Controller
@RequestMapping(value = "/m/image", produces="text/html;charset=UTF-8")
public class MImageController extends MBaseController {

        @ResponseBody
        @RequestMapping(value = "upload")
        public String upload(HttpServletRequest request) throws Exception {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = new Date();
            MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;  
            MultipartFile mf = mhs.getFile("file");  
            String fileName = mf.getOriginalFilename();
            fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
            File file = new File(systemConfigService.get(SystemConfigType.ImagePath) + "/" + fileName);
            mf.transferTo(file);
            String path = request.getSession().getAttribute("ctx").toString() + "/image/" + fileName;
            JSONObject obj = new JSONObject();
            obj.put("name", fileName);
            obj.put("path", path);
            
            Date end = new Date();
            obj.put("start", dateFormat.format(start));
            obj.put("end", dateFormat.format(end));
            obj.put("size", file.length());
            return toJson(obj);
        }
        
        @ResponseBody
        @RequestMapping(value = "{imagePath}.{suffix}")
        public void image(HttpServletResponse response, @PathVariable String imagePath, @PathVariable String suffix) throws IOException {
            File file = new File(systemConfigService.get(SystemConfigType.ImagePath) + "/" + imagePath + "." + suffix);
            System.out.println("imagePath = " + imagePath);
            
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
