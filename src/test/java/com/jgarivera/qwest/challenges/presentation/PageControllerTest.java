package com.jgarivera.qwest.challenges.presentation;

import com.jgarivera.qwest.TestUUIDFactoryConfiguration;
import com.jgarivera.qwest.challenges.domain.Challenge;
import com.jgarivera.qwest.challenges.domain.ChallengeId;
import com.jgarivera.qwest.challenges.domain.ChallengeRepository;
import com.jgarivera.qwest.shared.UUIDFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(PageController.class)
@Import(TestUUIDFactoryConfiguration.class)
class PageControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UUIDFactory uuidFactory;

    @MockBean
    ChallengeRepository challengeRepository;

    @Test
    void it_gets_challenges_page() throws Exception {
        List<Challenge> challenges = List.of(
                new Challenge(new ChallengeId(uuidFactory.create()), "First challenge"),
                new Challenge(new ChallengeId(uuidFactory.create()), "Second challenge")
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
