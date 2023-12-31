package br.com.rafaelsaca.backend.domain;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CnabService {
    private final Path fileStorageLocation;

    public CnabService(@Value("${file.upload-dir}") String fileUploadDir) {
        this.fileStorageLocation = Paths.get(fileUploadDir);
    }

    // upload do arquivo no diretório
    public void uploadCnabFile(MultipartFile file) throws Exception {
        var fileName = StringUtils.cleanPath(file.getOriginalFilename());
        var targetLocation = fileStorageLocation.resolve(fileName);
        file.transferTo(targetLocation);
    }
}
