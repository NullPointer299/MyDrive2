package model.dto;


import controller.util.ServletUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public interface Encodable {

    default String encode(Object target) {
        try {
            return URLEncoder.encode(String.valueOf(target), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 取得失敗
        return null;
    }
}
