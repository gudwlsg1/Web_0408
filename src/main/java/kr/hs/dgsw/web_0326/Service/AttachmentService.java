package kr.hs.dgsw.web_0326.Service;

import com.sun.xml.internal.ws.api.message.Attachment;
import kr.hs.dgsw.web_0326.Protocol.AttachmentProtocol;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface AttachmentService {
    AttachmentProtocol upload(MultipartFile uploadFile);
    HttpServletResponse download(HttpServletResponse response, String type, Long id);
}
