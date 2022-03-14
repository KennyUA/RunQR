<<<<<<< HEAD
package com.example.runqr;

import java.io.Serializable;

/**
 * This class represents a player's account in the game.
 * Account has 2 main attributes: username, and contactEmail:
 * A username is chosen by player and is used to uniquely identify the player in the game.
 * A contactEmail is given by player and is used to allow for communication with player.
 *
 */
public class Account  implements Serializable {

    private String username;
    private String contactEmail;


    public Account() {
    }

    public Account(String username, String contactEmail) {
        this.username = username;
        this.contactEmail = contactEmail;
    }

<<<<<<< HEAD
=======
    //public ArrayList<>


>>>>>>> ceedd5f6b56cb4f3360a61c496759026fb77a3a7

    /**
     * This method gets the username associated with a given player's account.
     * @return
     *      String representing the account's username.
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * This method gets the contact information (email) associated with a given player's account.
     * @return
     *      String representing the account's email.
     */


    public String getContactEmail() { return this.contactEmail; }





}
=======
package com.example.runqr;

import java.io.Serializable;

/**
 * This class represents a player's account in the game.
 * Account has 2 main attributes: username, and contactEmail:
 * A username is chosen by player and is used to uniquely identify the player in the game.
 * A contactEmail is given by player and is used to allow for communication with player.
 *
 */
public class Account  implements Serializable {

    private String username;
    private String contactEmail;


    public Account() {
    }

    public Account(String username, String contactEmail) {
        this.username = username;
        this.contactEmail = contactEmail;
    }

<<<<<<< HEAD
=======
    //public ArrayList<>


>>>>>>> ceedd5f6b56cb4f3360a61c496759026fb77a3a7

    /**
     * This method gets the username associated with a given player's account.
     * @return
     *      String representing the account's username.
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * This method gets the contact information (email) associated with a given player's account.
     * @return
     *      String representing the account's email.
     */


    public String getContactEmail() { return this.contactEmail; }





}
>>>>>>> 695b1365b5fa3ced11acc37e7789d71b042cd612
