package com.jgarivera.qwest.challenges.presentation;

import com.jgarivera.qwest.challenges.application.ChallengeCatalogueService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/challenges")
class PageController {

    private final ChallengeCatalogueService catalogueService;

    private PageController(ChallengeCatalogueService catalogueService) {
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

    @GetMapping("/host")
    private String hostChallenge(@ModelAttribute("form") ChallengeHostingForm form) {
        return "pages/challenges/host";
    }

    @PostMapping
    private String hostChallenge(
            @Valid @ModelAttribute("form") ChallengeHostingForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "pages/challenges/host";
        }

        catalogueService.hostChallenge(form.getTitle(), form.getVisibility(), form.getDescription());

        redirectAttributes.addFlashAttribute("hosted", true);

        return "redirect:/challenges";
    }
}
