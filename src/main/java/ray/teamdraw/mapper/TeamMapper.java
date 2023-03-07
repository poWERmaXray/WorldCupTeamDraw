package ray.teamdraw.mapper;

import org.apache.ibatis.annotations.Mapper;
import ray.teamdraw.bean.Team;

import java.util.List;

@Mapper
public interface TeamMapper {
    List<Team> getPromotedTeams();

    Team getHostCountry(String CountryName);
}
