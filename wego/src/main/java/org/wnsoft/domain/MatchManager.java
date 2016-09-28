/**
 * Created by wunan on 16-9-22.
 */
package org.wnsoft.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wnsoft.repository.JedisRepo;
import org.wnsoft.utils.SerializeHelper;
import org.wnsoft.utils.WnException;

import java.util.ArrayList;
import java.util.List;

public class MatchManager {
    private static final String MATCH_MANAGER = "Match-Manager";
    private static Logger logger = LoggerFactory.getLogger(MatchManager.class);
    private List<Match> matchList = new ArrayList<>();
    private JedisRepo jedisRepo = new JedisRepo();

    public MatchManager() {
        List<String> idList = jedisRepo.loadList(MATCH_MANAGER);

        for (String matchId : idList) {
            try {
                matchList.add(jedisRepo.load(matchId));
            } catch (WnException e) {
                logger.warn("Load match exception: {}", e.toString());
                jedisRepo.remove(MATCH_MANAGER, matchId);
            }
        }
    }

    public void addMatch(Match match) {
        if (!matchList.contains(match)) {
            matchList.add(match);
            jedisRepo.save(match.getMatchId(), match);
            jedisRepo.append(MATCH_MANAGER, match.getMatchId());
        }
    }

    public void delMatch(String matchId) {
        for (int i = 0; i < matchList.size(); ++i) {
            Match match = matchList.get(i);

            if (match.getMatchId().equalsIgnoreCase(matchId)) {
                jedisRepo.remove(MATCH_MANAGER, matchId);
                jedisRepo.del(matchId);
                matchList.remove(i);
                break;
            }
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

        throw new WnException(WnException.ERROR_NOTFOUND, "没有找到指定的比赛信息");
    }

    public void saveMatch(Match match) {
        jedisRepo.save(match.getMatchId(), match);
    }
}
