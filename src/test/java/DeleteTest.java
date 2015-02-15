import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;


public class DeleteTest {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/kevin/table_name.txt")));
        String line = null;
        while ((line = reader.readLine()) != null) {
            if (StringUtils.isBlank(line)) {
                continue;
            }
            // System.out.println("drop table if exists `" + line.trim() + "`;");
            System.out.println("models.add(\"" + line.trim() + "\");");
        }
    }
}
