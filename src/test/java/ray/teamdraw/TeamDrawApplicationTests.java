package ray.teamdraw;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ray.teamdraw.bean.Team;
import ray.teamdraw.beanEnum.ContinentEnum;
import ray.teamdraw.mapper.TeamMapper;
import ray.teamdraw.service.DrawService;
import ray.teamdraw.service.impl.TeamDrawServiceImpl;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class TeamDrawApplicationTests {

    @Autowired
    TeamDrawServiceImpl teamDrawService;

    @Test
    void contextLoads() {
        List<List<Team>> draw = teamDrawService.draw();
        draw.forEach(System.out::println);
    }

    @Autowired
    TeamMapper teamMapper;

    @Value("Russia")
    String hostCountry;

    @Test
    void sqlTest() {
//        List<Team> teams = teamMapper.getPromotedTeams();
//        teams.forEach(System.out::println);

        System.out.println(teamMapper.getHostCountry(hostCountry));
    }

    @Test
    void enumTest() {
        ContinentEnum continent = ContinentEnum.Asia;
        System.out.println(continent.toString());
    }

}
