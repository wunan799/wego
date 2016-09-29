/**
 * Created by wunan on 16-9-22.
 */
package org.wnsoft.service;

import org.wnsoft.domain.Match;
import org.wnsoft.domain.MatchManager;
import org.wnsoft.entity.Department;
import org.wnsoft.entity.MpNewsMsg;
import org.wnsoft.entity.User;
import org.wnsoft.utils.WnException;
import org.wnsoft.wx.DepartmentManager;
import org.wnsoft.wx.MessageManager;
import org.wnsoft.wx.TokenManager;
import org.wnsoft.wx.UserManager;
import sun.util.resources.cldr.tr.TimeZoneNames_tr;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.*;

public class MatchService {
    public static final String REDIRECT_URL = "http://wego.au-syd.mybluemix.net";
    private MatchManager matchManager = new MatchManager();
    private MessageManager messageManager = new MessageManager();
    private TokenManager tokenManager = new TokenManager();
    private UserManager userManager = new UserManager();
    private DepartmentManager departmentManager = new DepartmentManager();

    private final String[] imageArray = {
            "2Aa7FZzrzIjJzFxYBP3UVPfywWnl-tuE2OabjQA3NoOry5cdRA6fY_yLcftdGVndU",
            "2vruqjvjuoH5FPuWY0J3G6HVZ-45teqptGCD-hEAJFr3GbXcNgu7J8wenSUItoqgm",
            "2ODOvjl2DSWujCTcstkf1SvhD4mu3jmFbKCOJpiHzA17Cfi9u-P5AFsNgaNzhlPdi",
            "2JY8CWna0LCQbYpJiqchf2b5IAjAWvbPu_HX6u5QgalStiSKtFaEgNxKgBCPArsep",
            "27ouYCPCW_1hmjSHaKXmKDIEC_BiI-pgVCr4klNrrqGyXHac4Af7QG1kvaorNgKnk",
            "2WkjZT1jvc07UgURMB_RXYgCjDSgHgKhnqogkhWJSKxYMXhtQatAPqRkqXlWNT_nD",
            "2H4P5LakpKJJmHZZE11uKKihvpWat1xZMRI1t7NDUpfdAl75w991b_vWNolI9Ck_y",
            "2oIjYXZVfIm1i9bmru9PlVKMCH9k_5LQ2FQZJDMjy6Wa90Kubtg4gqSA5Lp4SSlcI"
    };

    public void addMatch(Match match) {
        matchManager.addMatch(match);
    }

    public void delMatch(String matchId) {
        matchManager.delMatch(matchId);
    }

    public void publishMatch(String matchId, List<String> toUserList
            , List<String> toPartyList, int agentId) {
        Match match = matchManager.getMatchById(matchId);
        MpNewsMsg mpNewsMsg = new MpNewsMsg();

        if ((toUserList != null) && (!toUserList.isEmpty())) {
            String toUser = "";

            for (String tmp : toUserList) {
                toUser += tmp + "|";
            }

            mpNewsMsg.setTouser(toUser);
        }

        if ((toPartyList != null) && !toPartyList.isEmpty()) {
            String toParty = "";

            for (String tmp : toPartyList) {
                toParty += tmp + "|";
            }

            mpNewsMsg.setToparty(toParty);
        }

        mpNewsMsg.setAgentid(agentId);
        MpNewsMsg.Articles articles = new MpNewsMsg.Articles();
        articles.setTitle(match.getTitle());
        int imageIndex = (new Random().nextInt()) % imageArray.length;
        articles.setThumb_media_id(imageArray[Math.abs(imageIndex)]);
        String signupUrl = REDIRECT_URL + "/signup.html?match=" + matchId;
        String playersUrl = REDIRECT_URL + "/players.html?match=" + matchId;
        String content = "<div class=\"text\"><table cellspacing=\"20\" cellpadding=\"5\">";
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG
                , DateFormat.SHORT, Locale.CHINESE);
        df.setTimeZone(TimeZone.getTimeZone("CTT"));//asia/shanghai
        String matchTime = df.format(new Date(match.getTime()));
        content += "<tr><td>时间</td><td>" +  matchTime + "</td></tr>";
        content += "<tr><td>球场</td><td>" + match.getPitch() + "</td></tr>";

        if (match.getPitchAddress() != null) {
            content += "<tr><td>地址</td><td>" + match.getPitchAddress() + "</td></tr>";
        }

        if (match.getShirtColor() != null) {
            content += "<tr><td>球衣</td><td>" + match.getShirtColor() + "</td></tr>";
        }

        if (match.getOpponent() != null) {
            content += "<tr><td>对手</td><td>" + match.getOpponent() + "</td></tr>";
        }

        if (match.getContent() != null) {
            content += "<tr><td>备注</td><td>" + match.getContent() + "</td></tr>";
        }

        content += "</table><a href=\"" + signupUrl + "\" " +
                "target=\"_self\" style=\"padding:10px;\">比赛报名</a>" +
                "<a href=\"" + playersUrl + "\" target=\"_self\"" +
                " style=\"margin-left:35px;\">已报名队员</a</div>";

        articles.setContent(content);
        articles.setShow_cover_pic("1");
        articles.setDigest("比赛时间：" + matchTime);

        if (match.getCreatorId() != null) {
            articles.setAuthor(userManager.getUserById(match.getCreatorId()
                    , tokenManager.getToken()).getName());
        }

        mpNewsMsg.getMpnews().addArticles(articles);
        messageManager.publish(mpNewsMsg, tokenManager.getToken());
    }

    public List<Match> getMatchList() {
        return matchManager.getMatchList();
    }

    public User getOauthUser(String code) {
        return userManager.getOauthUser(code, tokenManager.getToken());
    }

    public Match getMatch(String matchId) {
        return matchManager.getMatchById(matchId);
    }

    public void signinMatch(String matchId, String userId) {
        Match match = matchManager.getMatchById(matchId);
        match.addPlayer(userManager.getUserById(userId
                , tokenManager.getToken()));
        matchManager.saveMatch(match);
    }

    public void signoutMatch(String matchId, String userId) {
        Match match = matchManager.getMatchById(matchId);
        match.delPlayer(userId);
        matchManager.saveMatch(match);
    }

    public List<User> getMatchPlayers(String matchId) {
        Match match = matchManager.getMatchById(matchId);
        return match.getPlayerList();
    }

    public Department getDepartment(int id) {
        return departmentManager.getDepartment(tokenManager.getToken(), id);
    }

    public List<User> getDepartUsers(int id) {
        List<User> userList = departmentManager.getDepartmentUsers(
                tokenManager.getToken(), id);

        for (User user : userList) {
            userManager.cacheUser(user);
        }

        return userList;
    }
}
