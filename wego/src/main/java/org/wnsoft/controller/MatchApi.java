/**
 * Created by wunan on 16-9-21.
 */
package org.wnsoft.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wnsoft.domain.Match;
import org.wnsoft.dto.MatchDto;
import org.wnsoft.dto.PubMatchDto;
import org.wnsoft.entity.User;
import org.wnsoft.service.MatchService;
import org.wnsoft.utils.WnResult;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api", produces = "application/json; charset=UTF-8")
public class MatchApi {
    @Autowired
    private MatchService matchService;

    @RequestMapping(value = "/match/add.do", method = RequestMethod.POST)
    @ResponseBody
    public WnResult addMatch(@RequestBody MatchDto matchDto) {
        Match match = matchService.addMatch(matchDto.getTitle()
                , matchDto.getContent(), matchDto.getTime()
                , matchDto.getPitch(), matchDto.getOpponent());
        match.setOpponent(matchDto.getOpponent());
        match.setPitchAddress(matchDto.getPitchAddress());
        PubMatchDto pubMatchDto = new PubMatchDto();
        pubMatchDto.setAgentId(1);
        pubMatchDto.setMatchId(match.getMatchId());
        List<String> toPartyList = new ArrayList<>();
        toPartyList.add("2");
        pubMatchDto.setToParty(toPartyList);
        this.publishMatch(pubMatchDto);
        return new WnResult(match.getMatchId());
    }

    @RequestMapping(value = "/match/publish.do", method = RequestMethod.POST)
    @ResponseBody
    public WnResult publishMatch(@RequestBody PubMatchDto pubMatchDto) {
        matchService.publishMatch(pubMatchDto.getMatchId()
                , pubMatchDto.getToUser(), pubMatchDto.getToParty()
                , pubMatchDto.getAgentId());
        return WnResult.SUCCESS;
    }

    @RequestMapping(value = "/user/get.do", method = RequestMethod.GET)
    @ResponseBody
    public WnResult getOauthUser() {
        User user = matchService.getOauthUser();
        return new WnResult(user);
    }

    @RequestMapping(value = "/user/callback.do", method = RequestMethod.GET)
    @ResponseBody
    public WnResult onOauthCallback(HttpServletRequest request
            , String code, String state) {
        matchService.(code, state);
        Map map = request.getParameterMap();
        JSONObject jsonObject = new JSONObject(map);
        JSONObject paramObject = jsonObject.getJSONObject("params");
        paramObject.put("user", )
        return WnResult.SUCCESS;
    }

    @RequestMapping(value = "/match/get.do", method = RequestMethod.GET)
    @ResponseBody
    public WnResult getMatch(String matchId) {
        Match match = matchService.getMatch(matchId);
        MatchDto matchDto = new MatchDto();
        matchDto.setMatchId(matchId);
        matchDto.setTitle(match.getTitle());
        matchDto.setTime(match.getTime());
        matchDto.setPitch(match.getPitch());
        matchDto.setPitchAddress(match.getPitchAddress());
        matchDto.setOpponent(match.getOpponent());
        matchDto.setContent(match.getContent());
        matchDto.setStatus(match.getStatus());
        matchDto.setScore(match.getScore());
        matchDto.setOppScore(match.getOppScore());
        return new WnResult(matchDto);
    }

    @RequestMapping(value = "/match/signup.do", method = RequestMethod.GET)
    @ResponseBody
    public WnResult signupMatch(String matchId, String userId) {
        matchService.signupMatch(matchId, userId);
        return WnResult.SUCCESS;
    }

    @RequestMapping(value = "/match/players.do", method = RequestMethod.GET)
    @ResponseBody
    public WnResult getMatchPlayers(String matchId) {
        return new WnResult(matchService.getMatchPlayers(matchId));
    }
}
