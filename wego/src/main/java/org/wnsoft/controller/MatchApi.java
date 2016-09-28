/**
 * Created by wunan on 16-9-21.
 */
package org.wnsoft.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wnsoft.domain.Match;
import org.wnsoft.dto.MatchDto;
import org.wnsoft.dto.PubMatchDto;
import org.wnsoft.entity.User;
import org.wnsoft.service.MatchService;
import org.wnsoft.utils.WnResult;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ListIterator;

@Controller
@RequestMapping(value = "/api", produces = "application/json; charset=UTF-8")
public class MatchApi {
    private Logger logger = LoggerFactory.getLogger(MatchApi.class);

    @Autowired
    private MatchService matchService;

    @RequestMapping(value = "/match/add.do", method = RequestMethod.POST)
    @ResponseBody
    public WnResult addMatch(@RequestBody MatchDto matchDto) {
        Match match = new Match(matchDto.getTitle()
                , matchDto.getTime(), matchDto.getPitch()
                , matchDto.getOpponent(), matchDto.getShirtColor());
        match.setPitchAddress(matchDto.getPitchAddress());
        match.setContent(matchDto.getContent());
        match.setCreatorId(matchDto.getCreatorId());
        matchService.addMatch(match);
        PubMatchDto pubMatchDto = new PubMatchDto();
        pubMatchDto.setAgentId(1);
        pubMatchDto.setMatchId(match.getMatchId());
        List<String> toPartyList = new ArrayList<>();
        toPartyList.add("2");
        pubMatchDto.setToParty(toPartyList);
        this.publishMatch(pubMatchDto);
        return new WnResult(match.getMatchId());
    }


    @RequestMapping(value = "/match/del.do", method = RequestMethod.GET)
    @ResponseBody
    public WnResult delMatch(String matchId) {
        matchService.delMatch(matchId);
        return WnResult.SUCCESS;
    }

    @RequestMapping(value = "/match/publish.do", method = RequestMethod.POST)
    @ResponseBody
    public WnResult publishMatch(@RequestBody PubMatchDto pubMatchDto) {
        matchService.publishMatch(pubMatchDto.getMatchId()
                , pubMatchDto.getToUser(), pubMatchDto.getToParty()
                , pubMatchDto.getAgentId());
        return WnResult.SUCCESS;
    }

    @RequestMapping(value = "/match/list.do", method = RequestMethod.GET)
    @ResponseBody
    public WnResult getMatchList() {
        List<Match> matchList = matchService.getMatchList();
        List<MatchDto> matchDtoList = new ArrayList<>(matchList.size());
        ListIterator<Match> iterator = matchList.listIterator(matchList.size());

        //按最近发布的顺序获取
        while (iterator.hasPrevious()) {
            Match match = iterator.previous();
            MatchDto matchDto = new MatchDto();
            matchDto.setMatchId(match.getMatchId());
            matchDto.setTitle(match.getTitle());
            matchDto.setTime(match.getTime());
            matchDto.setPitch(match.getPitch());
            matchDto.setPitchAddress(match.getPitchAddress());
            matchDto.setOpponent(match.getOpponent());
            matchDto.setContent(match.getContent());
            matchDto.setStatus(match.getStatus());
            matchDto.setScore(match.getScore());
            matchDto.setOppScore(match.getOppScore());
            matchDtoList.add(matchDto);
        }

        return new WnResult(matchDtoList);
    }

    @RequestMapping(value = "/user/callback.do", method = RequestMethod.GET)
    public ModelAndView onOauthCallback(String code, String state) {
        logger.info("微信授权回调，code：{}，state：{}", code, state);
        String redirectUrl = new String(Base64.getDecoder().decode(state));
        User user = matchService.getOauthUser(code);

//        try {
//            redirectUrl += "&user=" + URLEncoder.encode(
//                    JSON.toJSONString(user), "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new WnException(e);
//        }

        ModelAndView mav = new ModelAndView("redirect");
        mav.addObject("user", JSON.toJSONString(user));
        mav.addObject("redirect", redirectUrl);
        logger.debug("授权完成后重定向：{}", redirectUrl);
        return mav;
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
        matchDto.setCreatorId(match.getCreatorId());
        matchDto.setShirtColor(match.getShirtColor());
        return new WnResult(matchDto);
    }

    @RequestMapping(value = "/match/signin.do", method = RequestMethod.GET)
    @ResponseBody
    public WnResult signinMatch(String matchId, String userId) {
        matchService.signinMatch(matchId, userId);
        return WnResult.SUCCESS;
    }

    @RequestMapping(value = "/match/signout.do", method = RequestMethod.GET)
    @ResponseBody
    public WnResult signoutMatch(String matchId, String userId) {
        matchService.signoutMatch(matchId, userId);
        return WnResult.SUCCESS;
    }

    @RequestMapping(value = "/match/players.do", method = RequestMethod.GET)
    @ResponseBody
    public WnResult getMatchPlayers(String matchId) {
        return new WnResult(matchService.getMatchPlayers(matchId));
    }

    @RequestMapping(value = "/department/get.do", method = RequestMethod.GET)
    @ResponseBody
    public WnResult getDepartment(int id) {
        return new WnResult(matchService.getDepartment(id));
    }
}
