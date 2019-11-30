/*
継承したクラスのフィールドをエンコードするための抽象クラスです。

 */


package model.dto;

import model.dto.token.Token;
import model.dto.token.TokenFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@SuppressWarnings("SpellCheckingInspection")
public abstract class Encodable {

    private String token = null;

    protected String encode(Object target) {
        try {
            return URLEncoder.encode(String.valueOf(target), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setToken(final Token token) {
        this.token = token.getTokenString();
    }

    public Token getToken() {
        return TokenFactory.createToken(token);
    }
}
