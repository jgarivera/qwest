package com.jgarivera.qwest.challenges.presentation;

import com.jgarivera.qwest.challenges.application.ChallengeCatalogueService;
import com.jgarivera.qwest.challenges.domain.model.Challenge;
import com.jgarivera.qwest.challenges.domain.model.ChallengeRepository;
import com.jgarivera.qwest.challenges.domain.model.ChallengeVisibility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(PageController.class)
class PageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ChallengeRepository repository;
    @MockBean
    ChallengeCatalogueService catalogueService;

    @Test
    @WithMockUser
    void it_gets_challenges_page() throws Exception {
        when(repository.nextId())
                .thenCallRealMethod();

        List<Challenge> challenges = List.of(
                new Challenge(repository.nextId(), "First challenge", ChallengeVisibility.PUBLIC),
                new Challenge(repository.nextId(), "Second challenge", ChallengeVisibility.PUBLIC)
        );

        when(catalogueService.getChallenges())
                .thenReturn(challenges);

        mockMvc.perform(get("/challenges"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/challenges/list"))
                .andExpectAll(
                        content().string(containsString("First challenge")),
                        content().string(containsString("Second challenge"))
                );
    }

    @Test
    @WithMockUser
    void it_gets_host_challenge_page() throws Exception {
        mockMvc.perform(get("/challenges/host"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/challenges/host"))
                .andExpectAll(
                        content().string(containsString("Host")),
                        content().string(containsString("Cancel"))
                );
    }

    @ParameterizedTest
    @CsvSource({
            "Challenge title,Challenge description, 1",
            "Challenge title,,2"
    })
    @WithMockUser
    void it_succeeds_to_host_challenge(@AggregateWith(ChallengeHostingFormAggregator.class) ChallengeHostingForm form) throws Exception {
        mockMvc.perform(post("/challenges")
                        .with(csrf())
                        .param("title", form.getTitle())
                        .param("description", form.getDescription())
                        .param("visibility", String.valueOf(form.getVisibility()))
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/challenges"))
                .andExpect(flash().attribute("hosted", true));

        verify(catalogueService, times(1))
                .hostChallenge(anyString(), anyInt(), nullable(String.class));
    }

    @ParameterizedTest
    @CsvSource({
            ",Challenge description, 1",
            ",,2",
            "Challenge title,Challenge description, 3",
            "Challenge title,,3"
    })
    @WithMockUser
    void it_fails_to_host_challenge(@AggregateWith(ChallengeHostingFormAggregator.class) ChallengeHostingForm form) throws Exception {
        mockMvc.perform(post("/challenges")
                        .with(csrf())
                        .param("title", form.getTitle())
                        .param("description", form.getDescription())
                        .param("visibility", String.valueOf(form.getVisibility()))
                )
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());

        verify(catalogueService, never())
                .hostChallenge(anyString(), anyInt(), anyString());
    }
}
