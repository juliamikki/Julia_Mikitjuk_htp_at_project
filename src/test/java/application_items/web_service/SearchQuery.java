package application_items.web_service;

public class SearchQuery {

    private String user;
    private boolean strict;

    public SearchQuery() {
    }

    public SearchQuery(String user, boolean strict) {
        this.user = user;
        this.strict = strict;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isStrict() {
        return strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    @Override
    public String toString() {
        return "SearchQuery{" +
                "user='" + user + '\'' +
                ", strict=" + strict +
                '}';
    }
}
