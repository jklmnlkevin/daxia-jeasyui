package com.daxia.core.util.dev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GenerateMobileDTO {
    private static String _Model = null;
    private static String _model = null;
    public static void main(String[] args) throws Exception {
        System.out.println("请输入model :");
        Scanner scanner = new Scanner(System.in);
        _Model = scanner.nextLine();
        _model = _Model.substring(0, 1).toLowerCase() + _Model.substring(1);
        
        generateDTO();
        // generateInfoDTO();
        // generateController();
    }
    private static void generateDTO() throws Exception {
        String path = "src/main/java/com/daxia/wy/dto/api";
        String templateFile = "ModelAPIDTO.txt";
        doGenerate(path, templateFile);
    }
    private static void doGenerate(String path, String templateFile) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/utils/template/" + templateFile)));
        String line = null;

        File outputFile = new File(path + "/" + templateFile.replace("Model", _Model).replace(".txt", ".java"));
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
        FileWriter writer = new FileWriter(outputFile);
        while ((line = reader.readLine()) != null) {
            line = line.replace("{Model}", _Model);
            line = line.replace("{model}", _model);
            
            writer.write(line);
            writer.write("\n");
        }
        
        writer.close();
        reader.close();
        System.out.println("generated " + templateFile);
    }
    private static void generateInfoDTO() throws Exception {
        String path = "src/main/java/com/daxia/wy/dto/api/info";
        String templateFile = "ModelInfoAPIDTO.txt";
        doGenerate(path, templateFile);
    }
    private static void generateController() throws Exception {
        String path = "src/main/java/com/daxia/wy/web/controller/m";
        String templateFile = "MModelController.txt";
        doGenerate(path, templateFile);
    }
}
