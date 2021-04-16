package com.woniu.car.service.web.util;


import org.apache.http.HttpResponse;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;


/**
 * @ClassName OilPricesQueryUtil
 * @Desc TODO
 * @Author Sakura
 * @Date 2021/4/16 9:38
 * @Version 1.0
 */
public class OilPricesQueryUtil {
    public static String OilPricesQuseryByPlace()throws Exception {
        URL u = new URL("http://route.showapi.com/138-46?showapi_appid%3Dmyappid%26prov%3D%E5%8C%97%E4%BA%AC%26showapi_sign%3Dmysecret");
        InputStream in = u.openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte buf[] = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        byte b[] = out.toByteArray();
        String x = new String(b, "utf-8");
        System.out.println(x);
         return x;
      }
    }
