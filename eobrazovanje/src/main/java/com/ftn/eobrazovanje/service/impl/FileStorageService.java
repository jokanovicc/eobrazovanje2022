package com.ftn.eobrazovanje.service.impl;
import java.io.IOException;
import java.util.stream.Stream;

import com.ftn.eobrazovanje.model.FileDB;
import com.ftn.eobrazovanje.model.Student;
import com.ftn.eobrazovanje.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    public FileDB store(MultipartFile file, Student student) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), student);
        return fileDBRepository.save(FileDB);
    }
    public FileDB getFile(Long id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles(Long studentId) {
        return fileDBRepository.findAllByStudent_Id(studentId).stream();
    }

}
