package attribute;

public enum AttrCookie {

    // 24時間保持する
    LOGIN("/MyDrive2", 60 * 60 * 24);

    private final String path;
    private final int maxAge;


    AttrCookie(final String path, final int maxAge) {
        this.path = path;
        this.maxAge = maxAge;
    }

    public String getPath() {
        return path;
    }

    public int getMaxAge() {
        return maxAge;
    }
}
