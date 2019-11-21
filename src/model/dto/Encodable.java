package model.dto;


import model.util.servlet.ServletUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public interface Encodable {

    String UTF_8 = ServletUtil.getUTF_8();

    default String encode(Object target) {
        try {
            return URLEncoder.encode(String.valueOf(target), UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.exit(1);
        }
        // 取得失敗
        return null;
    }
}
