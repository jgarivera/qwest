package com.jgarivera.qwest;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import org.jmolecules.archunit.JMoleculesDddRules;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@AnalyzeClasses(packages = "com.jgarivera.qwest")
class ModularityTests {

    ApplicationModules modules = ApplicationModules.of(QwestApplication.class);

    @Test
    void verifies_architecture() {
        modules.verify();
    }

    @ArchTest
    void verifies_ddd_rules(JavaClasses classes) {
        JMoleculesDddRules.all().check(classes);
    }

    @Test
    void creates_documentation() {
        new Documenter(modules, "docs/modules")
                .writeDocumentation();
    }
}
