package com.jgarivera.qwest;

import com.jgarivera.qwest.shared.application.TestDatabaseConfiguration;
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
