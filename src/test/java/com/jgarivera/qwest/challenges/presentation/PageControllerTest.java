package com.jgarivera.qwest.challenges.presentation;

import com.jgarivera.qwest.challenges.domain.model.Challenge;
import com.jgarivera.qwest.challenges.domain.model.ChallengeRepository;
import com.jgarivera.qwest.challenges.domain.model.ChallengeVisibility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(PageController.class)
class PageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ChallengeRepository challengeRepository;

    @Test
    @WithMockUser
    void it_gets_challenges_page() throws Exception {
        when(challengeRepository.nextId())
                .thenCallRealMethod();

        List<Challenge> challenges = List.of(
                new Challenge(challengeRepository.nextId(), "First challenge", ChallengeVisibility.PUBLIC),
                new Challenge(challengeRepository.nextId(), "Second challenge", ChallengeVisibility.PUBLIC)
        );

        when(challengeRepository.findAll())
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
