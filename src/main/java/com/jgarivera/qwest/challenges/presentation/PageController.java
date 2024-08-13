package com.jgarivera.qwest.challenges.presentation;

import com.jgarivera.qwest.challenges.domain.Challenge;
import com.jgarivera.qwest.challenges.domain.ChallengeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class PageController {

    private final ChallengeRepository repository;

    private PageController(ChallengeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/challenges")
    private String challenges(Model model) {
        Iterable<Challenge> challenges = repository.findAll();

        model.addAttribute("challenges", challenges);

        return "pages/challenges/list";
    }

    @GetMapping("/challenge-details")
    private String challengeDetails(Model model) {
        return "pages/challenges/details";
    }
}
