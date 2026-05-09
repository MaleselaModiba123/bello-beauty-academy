package repositories;

import models.Certificate;
import java.util.List;
import java.util.Optional;

public interface CertificateRepository extends Repository<Certificate, String> {

    // Get all certificates issued to a specific student
    List<Certificate> findByStudentId(String studentId);

    // Find a certificate by its unique certificate number — used for verification
    Optional<Certificate> findByCertificateNumber(String certificateNumber);
}