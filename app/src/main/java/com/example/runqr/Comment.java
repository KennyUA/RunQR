package com.example.runqr;

public class Comment {
    //String title;
    String body;

    /*
    public Comment(String title, String body) {
        this.title = title;
        this.body = body;
    }
     */

    public Comment(String body) {
        this.body = body;
    }


    public String getBody() {
        return body;
    }

    /*
    public String getTitle() {
        return title;
    }
    */
    public void setBody(String body) {
        this.body = body;
    }

    /*
    public void setTitle(String title) {
        this.title = title;
    }
     */
}
