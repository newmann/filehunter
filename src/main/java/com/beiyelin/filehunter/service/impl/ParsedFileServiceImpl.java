package com.beiyelin.filehunter.service.impl;

import cn.hutool.core.util.StrUtil;
import com.beiyelin.filehunter.entity.ParsedFile;
import com.beiyelin.filehunter.repository.ParsedFileDao;
import com.beiyelin.filehunter.service.ParsedFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @Author: newmann hu
 * @Date: created 19:01 2019-01-23
 * @Description:
 **/
@Service(value="parsedFileService")
public class ParsedFileServiceImpl implements ParsedFileService {
    @Autowired
    private ParsedFileDao parsedFileDao;

    @Override
    @Transactional
    public void delete(@NonNull Long id) {
        parsedFileDao.deleteById(id);
    }

    @Override
    @Transactional
    public long deleteByPathName(@NonNull String pathName) {
        return parsedFileDao.deleteByPathName(pathName);
    }

    @Override
    public ParsedFile insert(@NonNull ParsedFile parsedFile) {
        return parsedFileDao.save(parsedFile);
    }

    @Override
    public ParsedFile update(@NonNull ParsedFile parsedFile) {
        return parsedFileDao.save(parsedFile);
    }

    @Override
    public Optional<ParsedFile> findByPathName(@NonNull String pathName) {
        return parsedFileDao.findByPathName(pathName);
    }

    @Override
    public List<ParsedFile> findAllByKeywords(@NonNull List<String> keywords) {
        List<String> condition = new ArrayList<>();
        keywords.stream().forEach(keyword ->{
            condition.add(" keywords like '%".concat(keyword).concat("%'"));
        });

        final String tag = " or ";
        String conditionStr = StrUtil.removeSuffix(tag,StrUtil.join(tag,condition));

        return parsedFileDao.fetchByCondition(conditionStr);
    }

    @Override
    public long deleteAllByPath(@NonNull String path) {
        return parsedFileDao.deleteAllByPath(path);
    }

}
