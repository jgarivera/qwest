package com.jgarivera.qwest.challenges.presentation;

import com.jgarivera.qwest.challenges.application.ChallengeCatalogueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/challenges")
class ChallengeListPageController {

    private final ChallengeCatalogueService catalogueService;

    private ChallengeListPageController(ChallengeCatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @GetMapping
    private String challenges(Model model) {
        model.addAttribute("challenges", catalogueService.getChallenges());

        return "pages/challenges/list";
    }

    @GetMapping("/{id}")
    private String challengeDetails(Model model) {
        return "pages/challenges/details";
    }
}
