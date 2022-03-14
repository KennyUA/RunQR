
package com.example.runqr;

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

