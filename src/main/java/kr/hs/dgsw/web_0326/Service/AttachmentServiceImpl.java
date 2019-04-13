package kr.hs.dgsw.web_0326.Service;

import kr.hs.dgsw.web_0326.Domain.Comment;
import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_0326.Repository.AttachmentRepository;
import kr.hs.dgsw.web_0326.Repository.CommentRepository;
import kr.hs.dgsw.web_0326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public AttachmentProtocol upload(MultipartFile uploadFile) {
        String destFilename = "D:/school/3Grade/web/web_0326/upload/"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"))
                + UUID.randomUUID().toString() + "_"
                + uploadFile.getOriginalFilename();

        try{
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            uploadFile.transferTo(destFile);
            return new AttachmentProtocol(destFilename, uploadFile.getOriginalFilename());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public HttpServletResponse download(HttpServletResponse response, String type, Long id) {
        try
        {
            String filepath = "";
            String filename = "";

            switch (type) {
                case "user" :
                    User user = userRepository.findById(id).orElse(null);

                    if(user != null) {
                        filepath = user.getStroedPath();
                        filename = user.getOriginalName();
                    }
                    break;

                case "comment" :
                    Comment comment = commentRepository.findById(id).orElse(null);

                    if(comment != null) {
                        filepath = comment.getStroedPath();
                        filename = comment.getOriginalName();
                    }
                    break;
            }


            File file = new File(filepath);
            if(!file.exists()) {
                return null;
            }

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());

            if(mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
            response.setContentLength((int)file.length());

            InputStream is = null;
            is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, response.getOutputStream());

            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
