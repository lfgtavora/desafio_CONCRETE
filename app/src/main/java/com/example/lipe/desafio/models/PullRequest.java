package com.example.lipe.desafio.models;

public class PullRequest {

    private String title;
    private String body;
    private String state;
    private String html_url;
    private Owner user;

    public PullRequest(String title, String body, String state, String html_url, Owner user) {
        this.title = title;
        this.body = body;
        this.state = state;
        this.html_url = html_url;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Owner getUser() {
        return user;
    }

    public void setUser(Owner user) {
        this.user = user;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
