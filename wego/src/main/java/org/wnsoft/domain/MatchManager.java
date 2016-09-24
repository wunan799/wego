/**
 * Created by wunan on 16-9-22.
 */
package org.wnsoft.domain;

import java.util.ArrayList;
import java.util.List;

public class MatchManager {
    private List<Match> matchList = new ArrayList<>();

    public void addMatch(Match match) {
        if (!matchList.contains(match)) {
            matchList.add(match);
        }
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public Match getMatchById(String matchId) {
        for (Match match : matchList) {
            if (match.getMatchId().equalsIgnoreCase(matchId)) {
                return match;
            }
        }

        return null;
    }
}
