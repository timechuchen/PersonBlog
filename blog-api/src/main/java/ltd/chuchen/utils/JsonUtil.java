package ltd.chuchen.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class JsonUtil {

    public static String getJson(Object o){
        return getJson(o,"yyyy-MM-dd HH:mm:ss");
    }

    public static String getJson(Object o,String dataFormat) {
        ObjectMapper mapper = new ObjectMapper();
        //时间解析后的时间默认是时间戳  Timestamp,可以通过时间戳来设置
        SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
        mapper.setDateFormat(sdf);

        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
