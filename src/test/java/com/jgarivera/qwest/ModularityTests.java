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
    void verifiesArchitecture() {
        modules.verify();
    }

    @Test
    void createDocumentation() {
        new Documenter(modules, "docs/modules")
                .writeDocumentation();
    }

    @ArchTest
    void verifiesDddRules(JavaClasses classes) {
        JMoleculesDddRules.all().check(classes);
    }
}
