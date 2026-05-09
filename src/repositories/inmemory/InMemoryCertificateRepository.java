package repositories.inmemory;

import models.Certificate;
import repositories.CertificateRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// In-memory implementation of CertificateRepository using a HashMap
public class InMemoryCertificateRepository implements CertificateRepository {

    // HashMap stores certificates with their certificateId as the key
    private final Map<String, Certificate> storage = new HashMap<>();

    @Override
    public void save(Certificate certificate) {
        if (certificate == null) throw new IllegalArgumentException("Certificate must not be null.");
        storage.put(certificate.getCertificateId(), certificate);
    }

    @Override
    public Optional<Certificate> findById(String id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Certificate> findAll() {
        return Collections.unmodifiableList(
                storage.values().stream().collect(Collectors.toList())
        );
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public List<Certificate> findByStudentId(String studentId) {
        if (studentId == null) return Collections.emptyList();
        return storage.values().stream()
                .filter(c -> c.getStudent().getUserId().equals(studentId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Certificate> findByCertificateNumber(String certificateNumber) {
        if (certificateNumber == null) return Optional.empty();
        return storage.values().stream()
                .filter(c -> c.getCertificateNumber().equals(certificateNumber))
                .findFirst();
    }
}