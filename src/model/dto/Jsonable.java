/*
オブジェクトをJson化する必要があるときに実装するインタフェースです。
対象のクラスにEncodedクラスを継承した状態でジェネリクスの型に指定してください。

 */


package model.dto;

import com.google.gson.Gson;
import controller.util.ServletUtil;

@SuppressWarnings("SpellCheckingInspection")
public interface Jsonable<T extends Encodable> {

    Gson GSON = ServletUtil.GSON;

    default String toJson(final T encoded) {
        return GSON.toJson(encoded);
    }

    String toJson();
}
