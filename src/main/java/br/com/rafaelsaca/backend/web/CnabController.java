package br.com.rafaelsaca.backend.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("cnab")
public class CnabController {

    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        return "Processamento iniciado !";

    }
}
