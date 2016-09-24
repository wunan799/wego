/**
 * Created by wunan on 16-9-22.
 */
package org.wnsoft.dto;

import java.io.Serializable;

public class MatchDto implements Serializable {
    private String matchId;
    private String title;
    private String content;
    private long time;
    private String pitch;         //球场
    private String pitchAddress;  //球场地址
    private String opponent;      //对手
    private int status;           //0-未开始 1-进行中 2-结束 3-取消
    private int score;
    private int oppScore;         //对手分数

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public String getPitchAddress() {
        return pitchAddress;
    }

    public void setPitchAddress(String pitchAddress) {
        this.pitchAddress = pitchAddress;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getOppScore() {
        return oppScore;
    }

    public void setOppScore(int oppScore) {
        this.oppScore = oppScore;
    }
}
