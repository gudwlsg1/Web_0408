package kr.hs.dgsw.web_0326.Protocol;

import lombok.Data;

@Data
public class AttachmentProtocol {
    private String stroedPath;
    private String originalName;

    public AttachmentProtocol(String stroedPath, String originalName){
        this.stroedPath = stroedPath;
        this.originalName = originalName;
    }
}
