package com.cemisoft.gateways;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchTest {
    @Test
    @DisplayName("Services And Repositories Should Not Depend on Web Layer")
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.cemisoft.gateways");

        noClasses()
                .that()
                .resideInAnyPackage("com.cemisoft.gateways.service..")
                .or()
                .resideInAnyPackage("com.cemisoft.gateways.repository..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("com.cemisoft.gateways.web..")
                .because("Services and repositories shoould not depend on web layer")
                .check(importedClasses);
    }
}
