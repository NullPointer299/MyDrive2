package model.dto.question;

import model.dto.Encodable;
import model.dto.Jsonable;

import java.util.Arrays;

public class Questions implements Jsonable<Questions.Encoded> {

    private final String[] questions;

    private final Encoded encoded;

    Questions(String[] questions) {
        this.questions = questions;
        this.encoded = new Encoded(questions);
    }

    public String[] getQuestions() {
        return questions;
    }

    @Override
    public String toJson() {
        return toJson(encoded);
    }

    class Encoded implements Encodable {

        private final String[] questions;

        Encoded(String[] questions) {
            this.questions =
                    Arrays.stream(questions).map(this::encode).toArray(String[]::new);
        }
    }
}
