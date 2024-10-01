package com.jgarivera.qwest.challenges.presentation;

import com.jgarivera.qwest.challenges.application.ChallengeCatalogueService;
import com.jgarivera.qwest.identity.domain.model.User;
import com.jgarivera.qwest.identity.domain.model.UserId;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/challenges")
class ChallengeHostingPageController {

    private final ChallengeCatalogueService catalogueService;

    private ChallengeHostingPageController(ChallengeCatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @GetMapping("/host")
    private String hostChallenge(@ModelAttribute("form") ChallengeHostingForm form) {
        return "pages/challenges/host";
    }

    @PostMapping
    private String hostChallenge(@Valid @ModelAttribute("form") ChallengeHostingForm form,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes,
                                 @AuthenticationPrincipal User user) {
        if (result.hasErrors()) {
            return "pages/challenges/host";
        }

        UserId userId = user.getId();

        Assert.notNull(userId, "User must have id");

        catalogueService.hostChallenge(user.getId().id(), form.getTitle(), form.getVisibility(), form.getDescription());

        redirectAttributes.addFlashAttribute("hosted", true);

        return "redirect:/challenges";
    }
}
