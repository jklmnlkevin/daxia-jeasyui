package com.daxia.core.util.dev;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;

public class CodeDeleteUtils {
	static List<String> deleteList = new ArrayList<String>();
	static {
		deleteList.add("src/main/java/com/daxia/wy/model/{model}.java");
		deleteList.add("src/main/java/com/daxia/wy/dao/{model}DAO.java");
		deleteList.add("src/main/java/com/daxia/wy/service/{model}Service.java");
		deleteList.add("src/main/java/com/daxia/wy/web/controller/admin/Admin{model}Controller.java");
		deleteList.add("src/main/java/com/daxia/wy/web/controller/m/M{model}Controller.java");
		deleteList.add("src/main/java/com/daxia/wy/dto/{model}DTO.java");
		deleteList.add("src/main/java/com/daxia/wy/dto/api/{model}APIDTO.java");
		deleteList.add("src/main/java/com/daxia/wy/dto/api/info/{model}InfoAPIDTO.java");
		deleteList.add("src/main/webapp/WEB-INF/jsp/admin/{model}/{model}_list.jsp");
		deleteList.add("src/main/webapp/WEB-INF/jsp/admin/{model}/{model}_detail.jsp");
	}
			
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要删除的model: ");
		 //String model = scanner.nextLine();
//		String model = "Kevin";
		List<String> models = Lists.newArrayList();
		models.add("advise");
		models.add("advisereply");
		models.add("article");
		models.add("building");
		models.add("businesses");
		models.add("category");
		models.add("community");
		models.add("communityaddapply");
		models.add("convenience");
		models.add("doorplate");
		models.add("feeitem");
		models.add("housekeeping");
		models.add("keyword");
		models.add("nature");
		models.add("noticereply");
		models.add("order");
		models.add("orderitem");
		models.add("payrecord");
		models.add("product");
		models.add("question");
		models.add("questionreply");
		models.add("repair");
		models.add("repair_state");
		models.add("repairhistory");
		models.add("repairreply");
		models.add("reply");
		models.add("residential_quarters");
		models.add("servicetype");
		models.add("store");
		models.add("systemmessage");
		models.add("topic");
		models.add("topicreply");

		
		
		
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
