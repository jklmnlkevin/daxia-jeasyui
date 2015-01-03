package com.daxia.wy.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daxia.core.web.controller.BaseController;
import com.daxia.wy.dto.PushDTO;
import com.daxia.wy.service.CategoryService;

@Controller
@RequestMapping(value = "/test", produces="text/html;charset=UTF-8")
public class TestController extends BaseController {
    private static Logger logger = Logger.getLogger(TestController.class);
    
	@Autowired
	private CategoryService categoryService;
	private static String localImageFileDir = "/tmp";
	
	@RequestMapping(value = "upload")
	public String upload(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File localImageDir = new File(localImageFileDir);
        if (!localImageDir.exists()) {
            localImageDir.mkdirs();
        }
        factory.setRepository(localImageDir);
        factory.setSizeThreshold(1024 * 1024);
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            @SuppressWarnings("unchecked")
            List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
            for (FileItem item : list) {
                if (item.isFormField()) {
                } else {
                    // 取到最后一个反斜杠。
                    String filename = item.getName();
                    if (logger.isDebugEnabled()) {
                        logger.debug("filename is " + filename);
                    }
                    OutputStream out = new FileOutputStream(localImageFileDir + "/" + filename);
                    InputStream in = item.getInputStream();

                    int length = 0;
                    byte[] buf = new byte[1024];

                    while ((length = in.read(buf)) != -1) {
                        out.write(buf, 0, length);
                    }
                    in.close();
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    
	}
	
	@RequestMapping(value = "createCategory", method = RequestMethod.GET)
    public String createCategory() throws Exception {
	    categoryService.createCategory();
        return "test/test_push";
    }
	
    @RequestMapping(value = "push", method = RequestMethod.GET)
    public String push() throws Exception {
	    return "test/test_push";
    }
    
    @RequestMapping(value = "push", method = RequestMethod.POST)
    public String pushSubmit(String code, String title, String content, String id, Map<String, Object> map) throws Exception {
        PushDTO pushDTO = new PushDTO();
        pushDTO.setCode(code);
        pushDTO.setTitle(title);
        pushDTO.setContent(content);
        pushDTO.setId(id);
        System.out.println("push dto: " + pushDTO);
        String result = pushService.push(pushDTO);
        map.put("result", result);
        map.put("push", pushDTO);
        return "test/test_push";
    }
}
