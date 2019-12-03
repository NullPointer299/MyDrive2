package model.dto.question;

import model.dto.Encodable;
import model.dto.Jsonable;

import java.util.Arrays;

public class Questions implements Jsonable<Questions.Encoded> {

    private final String[] questions;

    private final Encoded encoded;

    Questions(final String[] questions) {
        this.questions = questions;
        this.encoded = new Encoded(questions);
    }

    public String[] getQuestions() {
        return questions;
    }

    public Encoded getEncoded() {
        return encoded;
    }

    @Override
    public String toJson() {
        return toJson(encoded);
    }

    // 送信専用のため、Convertibleは実装しない
    public class Encoded extends Encodable {

        private final String[] questions;

        Encoded(final String[] questions) {
            this.questions =
                    Arrays.stream(questions).map(this::encode).toArray(String[]::new);
        }
    }
}
