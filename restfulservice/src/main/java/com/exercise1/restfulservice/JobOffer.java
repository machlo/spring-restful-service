package com.exercise1.restfulservice;

/**
 * Created by daniel on 25.03.17.
 */
public class JobOffer {

    private final long id;
    private final String content;

    public JobOffer(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
