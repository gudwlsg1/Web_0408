package kr.hs.dgsw.web_0326.Repository;

import kr.hs.dgsw.web_0326.Domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, String> {
}
