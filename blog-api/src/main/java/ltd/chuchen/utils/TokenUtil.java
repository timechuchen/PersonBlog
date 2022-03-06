package ltd.chuchen.utils;

import java.util.UUID;

/**
 * token 相关操作
 * @author : chuchen
 */
public class TokenUtil {

    public static String getToken() {
        UUID uuid = UUID.randomUUID();
        return String.valueOf(uuid).replaceAll("-","");
    }

}
