package ray.teamdraw.bean;

import lombok.Data;
import ray.teamdraw.beanEnum.ContinentEnum;

import java.util.Objects;

@Data
public class Team {
    private Integer countryID; // 编号
    private String countryName; // 国家名称
    private ContinentEnum countryContinent; // 国家所属大洲
    private Integer countryRank; // 国家球队档次分级

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return (countryID.equals(team.countryID)) && (countryName.equals(team.countryName)) && countryContinent == team.countryContinent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryID, countryName, countryContinent, countryRank);
    }

    @Override
    public String toString() {
        return  ' ' + countryName + '-'
                + countryContinent +
                "-" + countryRank +
                ' ';
    }
}
