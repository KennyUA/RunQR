
package com.example.runqr;

/**
 * This class represents a ProfileItem object in the RunQR game.
 * This class is used by ProfileCustomList to pair the name of a player statistic such as high score qr code, number of scanned codes, etc with the appropriate value
 *
 */

public class ProfileItem {
    private String item = "";
    private String value = "";

    public ProfileItem(String item, String value) {
        this.item = item;
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String player) {
        this.item = item;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String score) {
        this.value = value;
    }
}

