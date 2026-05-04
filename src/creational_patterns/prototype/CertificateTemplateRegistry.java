package creational_patterns.prototype;

import java.util.HashMap;
import java.util.Map;

public class CertificateTemplateRegistry {

    private final Map<String, CertificateTemplate> prototypes = new HashMap<>();

    public void registerTemplate(String key, CertificateTemplate template) {
        prototypes.put(key, template);
    }

    // always return a clone, never the original
    public CertificateTemplate getClone(String key) {
        CertificateTemplate template = prototypes.get(key);
        if (template == null)
            throw new IllegalArgumentException("No template registered for key: " + key);
        return template.clone();
    }

    public boolean hasTemplate(String key) {
        return prototypes.containsKey(key);
    }
}