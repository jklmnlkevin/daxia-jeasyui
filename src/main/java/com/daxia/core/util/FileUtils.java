package com.daxia.core.util;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class FileUtils {
    public static String normalizePath(String path) {
        if (StringUtils.isBlank(path)) {
            return path;
        }
        path = normalizeFilePath(path);
        return (path.endsWith("/") ? path : path + "/");
    }
    
    public static String normalizeFilePath(String path) {
        if (StringUtils.isBlank(path)) {
            return path;
        }
        path = path.replace("\\", "/").replace("\\", "/").replace("//", "/").replace("//", "/");
        return path;
    }
    
    public static boolean isExitFile(String fileUrl) {
    	File file = new File(fileUrl);
    	return file.exists();
    }
    
    /**
     * Return if the file is an image
     * @param file
     * @return
     */
    public static boolean isImagefile(String file) {
        String extension = FilenameUtils.getExtension(file).toLowerCase();
        return (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("bmp")
                || extension.equals("gif") || extension.equals("png"));
    }
    
    /**
     * 批量删除图片
     * */
	public static void deleteFile(String fileNames) {
		String[] list = fileNames.split(",");
		for(int i = 0; i < list.length; i++) {
			String filePath = "D:/nanche_image/" + list[i];
			File file = new File(filePath);
			if(file.exists()) {
				file.delete();
			}else {
				System.out.println("图片不存在...");
			}
		}
	}
}
