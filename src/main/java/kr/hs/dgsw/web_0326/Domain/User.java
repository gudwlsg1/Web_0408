package kr.hs.dgsw.web_0326.Domain;

import kr.hs.dgsw.web_0326.Protocol.AttachmentProtocol;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;
    private String stroedPath;
    private String originalName;

    @CreationTimestamp
    private LocalDateTime joined;
    @UpdateTimestamp
    private LocalDateTime modified;

    public User(String userId, String password){
        this.name = userId;
        this.password = password;
    }

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, String storedPath, String originalName){
        this.name = name;
        this.email = email;
        this.password = password;
        this.stroedPath = storedPath;
        this.originalName = originalName;
    }
}
