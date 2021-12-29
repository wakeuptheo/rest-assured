package parenkov.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSupport {
    private User support;

    public User getSupport() {
        return support;
    }

    public void setSupport(User support) {
        this.support = support;
    }
}
