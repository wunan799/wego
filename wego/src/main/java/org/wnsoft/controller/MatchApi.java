/**
 * Created by wunan on 16-9-21.
 */
package org.wnsoft.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.wnsoft.domain.Match;
import org.wnsoft.dto.MatchDto;
import org.wnsoft.dto.PubMatchDto;
import org.wnsoft.entity.User;
import org.wnsoft.service.MatchService;
import org.wnsoft.utils.WnException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping(value = "/api", produces = "application/json; charset=UTF-8")
public class MatchApi {
    @Autowired
    private MatchService matchService;

    @RequestMapping(value = "/match/add.do", method = RequestMethod.POST)
    @ResponseBody
    public String addMatch(@RequestBody MatchDto matchDto) {
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
        return match.getMatchId();
    }

    @RequestMapping(value = "/match/publish.do", method = RequestMethod.POST)
    @ResponseBody
    public void publishMatch(@RequestBody PubMatchDto pubMatchDto) {
        matchService.publishMatch(pubMatchDto.getMatchId()
                , pubMatchDto.getToUser(), pubMatchDto.getToParty()
                , pubMatchDto.getAgentId());
    }

    @RequestMapping(value = "/user/get.do", method = RequestMethod.GET)
    public ModelAndView getUserByCode(String code, String state) {
        User user = matchService.getUser(code);
        ModelAndView mav = new ModelAndView("oauth");
        mav.addObject("user", JSON.toJSONString(user));
        byte[] tmpState = Base64.getDecoder().decode(state);

        try {
            mav.addObject("target", new String(tmpState, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new WnException(e);
        }

        return mav;
    }

    @RequestMapping(value = "/match/get.do", method = RequestMethod.GET)
    @ResponseBody
    public MatchDto getMatch(String matchId) {
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
        return matchDto;
    }

    @RequestMapping(value = "/match/signup.do", method = RequestMethod.GET)
    @ResponseBody
    public void signupMatch(String matchId, String userId) {
        matchService.signupMatch(matchId, userId);
    }
}
