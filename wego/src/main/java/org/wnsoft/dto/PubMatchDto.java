/**
 * Created by wunan on 16-9-22.
 */
package org.wnsoft.dto;

import java.io.Serializable;
import java.util.List;

public class PubMatchDto implements Serializable {
    private String matchId;
    private int agentId;
    private List<String> toUser;
    private List<String> toParty;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public List<String> getToUser() {
        return toUser;
    }

    public void setToUser(List<String> toUser) {
        this.toUser = toUser;
    }

    public List<String> getToParty() {
        return toParty;
    }

    public void setToParty(List<String> toParty) {
        this.toParty = toParty;
    }
}
