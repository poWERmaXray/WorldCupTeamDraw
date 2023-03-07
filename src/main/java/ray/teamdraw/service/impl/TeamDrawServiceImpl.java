package ray.teamdraw.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ray.teamdraw.bean.Team;
import ray.teamdraw.beanEnum.ContinentEnum;
import ray.teamdraw.mapper.TeamMapper;
import ray.teamdraw.service.DrawService;

import java.util.*;

@Service
public class TeamDrawServiceImpl implements DrawService {
    @Value("Russia")
    String hostCountry;
    @Autowired
    TeamMapper teamMapper;
    private static final int GROUP_COUNT = 8; // 小组数
    private static final int TEAM_COUNT_PER_GROUP = 4; // 每组球队数
    private static final int LEVEL_COUNT = 4; // 档次数
    private static final int TEAM_COUNT_PER_LEVEL = 8; // 每档球队数
    private static final int CONTINENT_COUNT = 6; // 球队来自不同大洲的大洲数
    private static final int ASIA = 0;
    private static final int AFRICA = 1;
    private static final int Europe = 2;
    private static final int NORTH_AMERICA = 3;
    private static final int SOUTH_AMERICA = 4;
    private static final int OCEANIA = 5;
    private static final int GROUP_ONE = 0;
    private static final int GROUP_TWO = 1;
    private static final int GROUP_THREE = 2;
    private static final int GROUP_FOUR = 3;

    private static final Random random = new Random();

    public List<List<Team>> draw() {
            // 从数据库中读取32支球队信息,并将它们按照档次从低到高排序
            List<Team> teams;
            List<List<Team>> groups = null;
            // 将球队按档次分组
            List<List<Team>> levels;

        while (true) {
            teams = teamMapper.getPromotedTeams();
            teams.sort((teamA, teamB) -> teamA.getCountryRank() - teamB.getCountryRank());
            groups = Lists.newArrayListWithCapacity(GROUP_COUNT);
            levels = Lists.newArrayListWithCapacity(LEVEL_COUNT);
            // 初始化8个小组
            for (int i = 0; i < GROUP_COUNT; i++) {
                groups.add(Lists.newArrayListWithCapacity(TEAM_COUNT_PER_GROUP));
            }

            // 将东道主国家放入A组第一个位置,且已知东道主必为第一级
            int hostCountryIndex = teams.indexOf(teamMapper.getHostCountry(hostCountry));
            groups.get(0).add(teams.remove(hostCountryIndex));

            for (int i = 0; i < LEVEL_COUNT; i++) {
                levels.add(Lists.newArrayListWithCapacity(TEAM_COUNT_PER_LEVEL));
            }
            for (Team team : teams) {
                levels.get(team.getCountryRank() - 1).add(team);
            }

            boolean isContinue = true;
            for (int i = 0; i < GROUP_COUNT; ++i) {
                if (isContinue) {
                    for (int j = 0; j < TEAM_COUNT_PER_GROUP; j++) {
                        if (i == 0 && j == 0) {
                            continue;
                        }
                        Set<ContinentEnum> avoidanceContinents = getCurrentGroupAvoidanceContinents(groups.get(i));
                        List<Team> requiredTeams = getTeamsNotFromAssignContinents(avoidanceContinents, levels.get(j));
                        // 随机从合适的球队中抽取一支
                        Team team = null;
                        // 因为是随机抽签,可能存在符合条件的大洲被提前抽走的情况,此时便会导致抽签失败,做防止崩溃的处理，并重新抽签
                        try {
                            team = requiredTeams.get(random.nextInt(requiredTeams.size()));
                        } catch (IllegalArgumentException e) {
                            isContinue = false;
                            System.out.println("Random draw failed, try again...");
                        }
                        if (null != team) {
                            levels.get(j).remove(team);
                            teams.remove(team);
                            groups.get(i).add(team);
                        }
                    }
                }
            }
            if (teams.isEmpty()){
                break;
            }
        }
        return groups;
    }

    private List<Team> getTeamsNotFromAssignContinents(Set<ContinentEnum> continents, List<Team> level) {
        List<Team> filteredTeams = Lists.newArrayList();
        int continentsCount;
        for (Team team : level) {
            continentsCount = continents.size();
            for (ContinentEnum continent : continents) {
                if ( team.getCountryContinent().equals(continent)){
                    --continentsCount;
                    break;
                }
            }
            // 此时仍然相等表明该国家球队不来自continents中的任何一个洲
            if (continentsCount == continents.size()) {
                filteredTeams.add(team);
            }
        }
        return filteredTeams;
    }

    private Set<ContinentEnum> getCurrentGroupAvoidanceContinents(List<Team> group) {
        Set<ContinentEnum> set = Sets.newHashSet();
        Map<ContinentEnum, Integer> avoidanceCount = Maps.newHashMap();
        for (Team team : group) {
            if (avoidanceCount.containsKey(team.getCountryContinent())){
                Integer value = avoidanceCount.get(team.getCountryContinent()) + 1;
                avoidanceCount.put(team.getCountryContinent(), value);
            }else {
                avoidanceCount.put(team.getCountryContinent(), 1);
            }
        }
        for (Map.Entry<ContinentEnum, Integer> continentEnumIntegerEntry : avoidanceCount.entrySet()) {
            if (continentEnumIntegerEntry.getValue() >= 2) {
                set.add(continentEnumIntegerEntry.getKey());
            }
        }
        return set;
    }
}
