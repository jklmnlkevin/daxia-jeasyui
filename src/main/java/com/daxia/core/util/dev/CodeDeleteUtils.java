package com.daxia.core.util.dev;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;

public class CodeDeleteUtils {
	static List<String> deleteList = new ArrayList<String>();
	static {
		deleteList.add("src/main/java/com/daxia/webchat/model/{model}.java");
		deleteList.add("src/main/java/com/daxia/webchat/dao/{model}DAO.java");
		deleteList.add("src/main/java/com/daxia/webchat/service/{model}Service.java");
		deleteList.add("src/main/java/com/daxia/webchat/web/controller/admin/Admin{model}Controller.java");
		deleteList.add("src/main/java/com/daxia/webchat/dto/{model}DTO.java");
		deleteList.add("src/main/webapp/WEB-INF/jsp/admin/{model}/{model}_list.jsp");
		deleteList.add("src/main/webapp/WEB-INF/jsp/admin/{model}/{model}_detail.jsp");
	}
			
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要删除的model: ");
		 //String model = scanner.nextLine();
//		String model = "Kevin";
		List<String> models = Lists.newArrayList();
		models.add("Example");
//		models.add("Sleep");
		models.add("Sleep");
		models.add("StoreBaseModel");
		models.add("StoreImage");
		models.add("StoreImageType");
		models.add("SystemMessage");
		models.add("Topic");
		models.add("TopicComment");
		models.add("WxResult");
		models.add("");
		models.add("");
		models.add("");
		models.add("");
		
		
		
		for (String model : models) {
			for (String path : deleteList) {
				File file = new File(path.replace("{model}", model));
				if (file.exists()) {
					file.delete();
				}
			}
		}
		
    }
}
