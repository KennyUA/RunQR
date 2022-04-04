package com.example.runqr.placeholder;

import com.example.runqr.LeaderboardItem;

import java.util.Comparator;

public class LeaderboardItemComparator implements Comparator<LeaderboardItem> {
    @Override
    public int compare(LeaderboardItem item1, LeaderboardItem item2) {
        return -(Integer.compare(item1.getScoreInt(), item2.getScoreInt()));
    }
}
