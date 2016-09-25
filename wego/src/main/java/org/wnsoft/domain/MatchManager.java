/**
 * Created by wunan on 16-9-22.
 */
package org.wnsoft.domain;

import org.wnsoft.repository.JedisRepo;
import org.wnsoft.utils.SerializeHelper;
import org.wnsoft.utils.WnException;

import java.util.ArrayList;
import java.util.List;

public class MatchManager {
    private static final String MATCH_MANAGER = "Match-Manager";
    private List<Match> matchList = new ArrayList<>();
    private JedisRepo jedisRepo = new JedisRepo();

    public MatchManager() {
        List<byte[]> bytes = jedisRepo.loadList(MATCH_MANAGER);

        try {
            for (byte[] tmp : bytes) {
                String matchId = SerializeHelper.byteToObject(tmp);
                matchList.add(jedisRepo.load(matchId));
            }
        } catch (Exception e) {
            throw new WnException(e);
        }
    }

    public void addMatch(Match match) {
        if (!matchList.contains(match)) {
            matchList.add(match);
            jedisRepo.save(match.getMatchId(), match);
            jedisRepo.append(MATCH_MANAGER, match.getMatchId());
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
}
