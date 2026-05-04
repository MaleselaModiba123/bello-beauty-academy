package tests;

import creational_patterns.prototype.CertificateTemplate;
import creational_patterns.prototype.CertificateTemplateRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestPrototype {

    private CertificateTemplate template;

    @BeforeEach
    void setUp() {
        template = new CertificateTemplate(
                "TMPL-001", "Standard Template",
                "/logo.png", "/sig.png", "gold", "Playfair Display");
        template.addMetadata("academyName", "Bello Beauty Academy");
    }

    // cloning

    @Test
    void cloneIsNotTheSameReference() {
        CertificateTemplate clone = template.clone();
        assertNotSame(template, clone);
    }

    @Test
    void cloneHasIdenticalFieldValues() {
        CertificateTemplate clone = template.clone();
        assertEquals(template.getTemplateId(),   clone.getTemplateId());
        assertEquals(template.getTemplateName(), clone.getTemplateName());
        assertEquals(template.getBorderStyle(),  clone.getBorderStyle());
        assertEquals(template.getFontFamily(),   clone.getFontFamily());
    }

    // deep copy

    @Test
    void mutatingCloneDoesNotAffectOriginal() {
        CertificateTemplate clone = template.clone();
        clone.personalise("Amira Patel", "Classic Lash Course", "2026-03-15", "BBA-001");
        assertFalse(template.getMetadata().containsKey("studentName"));
    }

    // registry

    @Test
    void registryReturnsDistinctClonesEachTime() {
        CertificateTemplateRegistry registry = new CertificateTemplateRegistry();
        registry.registerTemplate("LASH", template);
        CertificateTemplate clone1 = registry.getClone("LASH");
        CertificateTemplate clone2 = registry.getClone("LASH");
        assertNotSame(clone1, clone2);
    }

    @Test
    void registryThrowsForUnknownKey() {
        CertificateTemplateRegistry registry = new CertificateTemplateRegistry();
        assertThrows(IllegalArgumentException.class, () -> registry.getClone("UNKNOWN"));
    }
}