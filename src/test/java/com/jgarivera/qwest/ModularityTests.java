package com.jgarivera.qwest;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModularityTests {

    ApplicationModules modules = ApplicationModules.of(QwestApplication.class);

    @Test
    void verifiesArchitecture() {
        modules.verify();
    }

    @Test
    void createDocumentation() {
        new Documenter(modules, "docs/modules")
                .writeDocumentation();
    }
}
