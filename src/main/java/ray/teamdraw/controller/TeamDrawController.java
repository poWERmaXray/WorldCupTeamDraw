package ray.teamdraw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ray.teamdraw.service.DrawService;
import ray.teamdraw.service.impl.TeamDrawServiceImpl;

@RestController
public class TeamDrawController {

    @Autowired
    TeamDrawServiceImpl drawService;

    @RequestMapping("/draw")
    String drawFootballTeam() {
        String s = drawService.draw().toString();
        System.out.println(s);
        return s;
    }
}
