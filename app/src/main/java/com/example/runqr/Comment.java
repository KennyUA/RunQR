package com.example.runqr;

import java.io.Serializable;

/**
 * This class represents a comment on a QRCode object.
 * A comment can be added by any player to any QRCode object in the game.
 * A comment object has one attribute: the body text of the comment.
 */
public class Comment implements Serializable {
    String body;



    public Comment(){}

    public Comment(String body) {
        this.body = body;
    }


    /**
     * This method returns the body text of the comment.
     * @return
     *      A String representing the comment body.
     */
    public String getBody() {
        return body;
    }

    /**
     * This method sets the body text of the comment.
     * @param body
     *      The String to set the body of the comment to.
     */
    public void setBody(String body) {
        this.body = body;
    }

}
