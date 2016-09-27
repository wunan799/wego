/**
 * Created by wunan on 16-9-22.
 */
package org.wnsoft.domain;

import org.wnsoft.entity.User;
import org.wnsoft.utils.WnException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Match implements Serializable {
    private String matchId;
    private String title;
    private String content;
    private long time;
    private String pitch;         //球场
    private String pitchAddress;  //球场地址
    private String opponent;      //对手
    private int status = 0;       //0-未开始 1-进行中 2-结束 3-取消
    private int score;
    private int oppScore;         //对手分数
    private List<User> playerList = new ArrayList<>();      //报名队员
    private String shirtColor;
    private String creatorId;

    public Match(String matchId) {
        this.matchId = matchId;
    }

    public Match(String title, long time, String pitch
            , String opponent, String shirtColor) {
        this.title = title;
        this.time = time;
        this.pitch = pitch;
        this.opponent = opponent;
        this.shirtColor = shirtColor;
        this.matchId = UUID.randomUUID().toString();
    }

    public void setTitle(String title) {
        checkStatus();
        this.title = title;
    }

    public void setContent(String content) {
        checkStatus();
        this.content = content;
    }

    public void setTime(long time) {
        checkStatus();
        this.time = time;
    }

    public void setPitch(String pitch) {
        checkStatus();
        this.pitch = pitch;
    }

    public void setPitchAddress(String pitchAddress) {
        checkStatus();
        this.pitchAddress = pitchAddress;
    }

    public void setOpponent(String opponent) {
        checkStatus();
        this.opponent = opponent;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setOppScore(int oppScore) {
        this.oppScore = oppScore;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getTime() {
        return time;
    }

    public String getPitch() {
        return pitch;
    }

    public String getPitchAddress() {
        return pitchAddress;
    }

    public String getOpponent() {
        return opponent;
    }

    public int getStatus() {
        return status;
    }

    public int getScore() {
        return score;
    }

    public int getOppScore() {
        return oppScore;
    }

    public String getShirtColor() {
        return shirtColor;
    }

    public void setShirtColor(String shirtColor) {
        this.shirtColor = shirtColor;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public void addPlayer(User user) {
        if (!hasPlayer(user.getUserid())) {
            playerList.add(user);
        }
    }

    public void delPlayer(String userId) {
        for (User user : playerList) {
            if (user.getUserid().equalsIgnoreCase(userId)) {
                playerList.remove(user);
                break;
            }
        }
    }

    public boolean hasPlayer(String userId) {
        boolean find = false;

        for (User tmp : playerList) {
            find = tmp.getUserid().equalsIgnoreCase(userId);

            if (find) {
                break;
            }
        }

        return find;
    }

    public List<User> getPlayerList() {
        return playerList;
    }

    public String getMatchId() {
        return matchId;
    }

    private void checkStatus() {
        if (status != 0) {
            throw new WnException("比赛已经开始，不能更改信息");
        }
    }
}
