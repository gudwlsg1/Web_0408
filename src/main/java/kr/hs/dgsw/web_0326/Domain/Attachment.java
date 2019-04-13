package kr.hs.dgsw.web_0326.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Attachment {
    @Id
    private String stroedPath;

    private String originalName;

    public Attachment(String storedPath, String originalName) {
        this.stroedPath = storedPath;
        this.originalName = originalName;
    }
}
