package com.example.runqr;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents the CommentLibrary for a QRCode object.
 * It contains a list of comments added to QRCodes by players in the game.
 * Allows for adding and removing of comments.
 * This class abstracts ArrayList<String> with numComments attribute to track the total number of comments in the CommentLibrary.
 */
public class CommentLibrary implements Serializable {

    private ArrayList<Comment> commentList;
    private int numComments;

    public CommentLibrary() {
        this.commentList = new ArrayList<Comment>();
        this.numComments = 0;
    }

    /**
     * This method returns the list of comments for a given QRCode.
     * @return
     *      ArrayList of Strings, each String represents a single comment.
     */
    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    /**
     * This method returns the total number of comments in the CommentLibrary, i.e. the number of Strings in the commentList.
     * @return
     *      An int representing the number of comments in the library.
     */
    public int getNumComments() {
        return numComments;
    }


    /**
     * This method sets the commentList of commentLibrary to comments.
     * @param comments
     *      The ArrayList of Strings to set the commentList to.
     */
    public void setCommentList(ArrayList<Comment> comments) {
        this.commentList = comments;
    }

    /**
     * This method sets the total number of comments to numComments.
     * @param numComments
     *      The int number to set the numComments attribute to.
     */
    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }


    /**
     * This method adds a comment to the comment library and increments the number of total comments.
     * @param comment
     *  The new comment to be added to the library.
     */
    public void addComment(Comment comment) {
        commentList.add(comment);
        numComments += 1;
    }
}
