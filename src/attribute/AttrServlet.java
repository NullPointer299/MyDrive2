package attribute;

public enum AttrServlet {
    LOGIN("/Login/"),
    LOGOUT("/Logout/"),
    REGISTER("/Register/"),
    MAIN("/Main/");

    private final String url;

    AttrServlet(final String url) {
        this.url = url;
    }

    public String getUrl(final boolean isCallingFromJsp) {
        return isCallingFromJsp ? "/MyDrive2" + url : url;
    }
}
