package attribute;

public enum AttrJsp {

    LOGIN("login.jsp"),
    REGISTER("register.jsp"),
    MAIN("main.jsp");

    private final String url;

    AttrJsp(final String url) {
        this.url = "/WEB-INF/" + url;
    }

    public String getUrl() {
        return url;
    }
}
