package creational_patterns.prototype;

import java.util.HashMap;
import java.util.Map;

public class CertificateTemplate implements Cloneable {

    private String templateId;
    private String templateName;
    private String logoPath;
    private String signaturePath;
    private String borderStyle;
    private String fontFamily;
    private final Map<String, String> metadata;

    public CertificateTemplate(String templateId, String templateName, String logoPath,
                               String signaturePath, String borderStyle, String fontFamily) {
        this.templateId    = templateId;
        this.templateName  = templateName;
        this.logoPath      = logoPath;
        this.signaturePath = signaturePath;
        this.borderStyle   = borderStyle;
        this.fontFamily    = fontFamily;
        this.metadata      = new HashMap<>();
    }

    public void addMetadata(String key, String value) {
        metadata.put(key, value);
    }

    public void personalise(String studentName, String courseName,
                            String issueDate, String certificateNumber) {
        metadata.put("studentName",       studentName);
        metadata.put("courseName",        courseName);
        metadata.put("issueDate",         issueDate);
        metadata.put("certificateNumber", certificateNumber);
    }

    // deep copy — a new HashMap is constructed so mutating the clone never affects the prototype
    @Override
    public CertificateTemplate clone() {
        try {
            super.clone();
            CertificateTemplate deepClone = new CertificateTemplate(
                    this.templateId, this.templateName, this.logoPath,
                    this.signaturePath, this.borderStyle, this.fontFamily);
            new HashMap<>(this.metadata).forEach(deepClone::addMetadata);
            return deepClone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning failed", e);
        }
    }

    public String              getTemplateId()    { return templateId; }
    public String              getTemplateName()  { return templateName; }
    public String              getLogoPath()      { return logoPath; }
    public String              getSignaturePath() { return signaturePath; }
    public String              getBorderStyle()   { return borderStyle; }
    public String              getFontFamily()    { return fontFamily; }
    public Map<String, String> getMetadata()      { return new HashMap<>(metadata); }

    public void setTemplateId(String templateId) { this.templateId = templateId; }

    @Override
    public String toString() {
        return String.format("CertificateTemplate{id=%s, name=%s, border=%s, font=%s, metadata=%s}",
                templateId, templateName, borderStyle, fontFamily, metadata);
    }
}