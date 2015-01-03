import java.util.Map;

import org.jsoup.Connection.Response;
import org.jsoup.Connection;
import org.jsoup.Jsoup;


public class Hello {
    public static void main(String[] args) throws Exception {
        String url = "http://www.kmart.com/bed-bath-towels-rugs-bath-towels&Kmart/b-1219498023?filter=storeOrigin&previousSort=ORIGINAL_SORT_ORDER&viewItems=50";
        Connection connection = Jsoup.connect(url);
        connection = connection.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36");
        Response response = connection.execute();
        System.out.println(response.statusCode());
        Map<String, String> map = response.headers();
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
        System.out.println(response.body());
    }
}
