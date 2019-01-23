package com.beiyelin.filehunter.service;

import com.beiyelin.filehunter.entity.ParsedFile;
import org.springframework.lang.NonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @Author: newmann hu
 * @Date: created 19:00 2019-01-23
 * @Description:
 **/
public interface ParsedFileService {

    void delete(@NonNull Long id);
    long deleteByPathName(@NonNull String pathName);
    long deleteAllByPath(@NonNull String path);

    ParsedFile insert(@NonNull ParsedFile parsedFile);

    ParsedFile update(@NonNull ParsedFile parsedFile);

    Optional<ParsedFile> findByPathName(@NonNull String pathName);

    List<ParsedFile> findAllByKeywords(@NonNull List<String> keywords);

}
