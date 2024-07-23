package com.jgarivera.qwest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestDatabaseConfiguration.class)
class QwestApplicationTests {

    @Test
    void contextLoads() {
    }

}
