package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.UtilisateurApi;
import com.dp.dpshopbackend.dto.UtilisateurDto;
import com.dp.dpshopbackend.services.UtilisateurService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin
public class UtilisateurController implements UtilisateurApi {

    private final UtilisateurService utilisateurService;

    @Autowired
    ServletContext context;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public ResponseEntity<UtilisateurDto> save(UtilisateurDto utilisateurDto) {
        return ResponseEntity.ok(utilisateurService.save(utilisateurDto));
    }

    @Override
    public ResponseEntity<UtilisateurDto> findById(Long id) {
        return ResponseEntity.ok(utilisateurService.findById(id));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public ResponseEntity<List<UtilisateurDto>> getAllUtilisateursOrderByIdDesc() {
        List<UtilisateurDto> utilisateurDtoList = utilisateurService.findByOrderByIdDesc();
        return new ResponseEntity<>(utilisateurDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UtilisateurDto> getUtilisateurByUsername(String username) {
        return null;
    }

    @Override
    public byte[] getPhoto(Long id) throws Exception {
        UtilisateurDto user = utilisateurService.findById(id);
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/") + user.getPhoto()));
    }


    @Override
    public void uploadUserPhoto(MultipartFile file, Long id) throws IOException {
        UtilisateurDto utilisateurDto = utilisateurService.findById(id);
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/Images/" + File.separator + newFileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

            utilisateurDto.setPhoto(filename);

            utilisateurService.save(utilisateurDto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public ResponseEntity<UtilisateurDto> updateUtilisateur(Long idUser, UtilisateurDto utilisateurDto) {
        utilisateurDto.setId(idUser);
        return new ResponseEntity<>(utilisateurService.save(utilisateurDto), HttpStatus.OK);
    }

    @Override
    public void delete(Long id) {
        utilisateurService.delete(id);
    }
}
