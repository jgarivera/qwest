package com.jgarivera.qwest.challenges.presentation;

import com.jgarivera.qwest.challenges.application.ChallengeCatalogueService;
import com.jgarivera.qwest.challenges.domain.model.Challenge;
import com.jgarivera.qwest.challenges.domain.model.ChallengeRepository;
import com.jgarivera.qwest.challenges.domain.model.HostId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ChallengeListPageController.class)
class ChallengeListPageControllerTest {

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

        var hostId = new HostId(UUID.randomUUID());

        List<Challenge> challenges = List.of(
                new Challenge.Builder(repository.nextId(), hostId, "First challenge").build(),
                new Challenge.Builder(repository.nextId(), hostId, "Second challenge").build()
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
}
