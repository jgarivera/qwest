package com.jgarivera.qwest;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import org.jmolecules.archunit.JMoleculesArchitectureRules;
import org.jmolecules.archunit.JMoleculesDddRules;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@AnalyzeClasses(packagesOf = QwestApplication.class)
class ModularityTests {

    ApplicationModules modules = ApplicationModules.of(QwestApplication.class);

    @ArchTest
    void verifies_architecture(JavaClasses classes) {
        modules.verify();

        JMoleculesArchitectureRules.ensureOnionSimple()
                .check(classes);
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
