package com.beiyelin.filehunter.service;

import cn.hutool.core.io.FileUtil;
import com.beiyelin.filehunter.bean.FileBean;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

/**
 * @Author: newmann hu
 * @Date: created 8:04 2019-01-22
 * @Description:
 **/
@Service
public class FileSearchService {

    public File[] getFiles(String dirName){
        return FileUtil.ls(dirName);
    }

    public ObservableList<FileBean> getFilesObservable(String dirName){
        File[] files = getFiles(dirName);
        ObservableList<FileBean> result = FXCollections.observableArrayList();
        Arrays.stream(files).forEach(file ->{
            FileBean fileBean;
            fileBean = new FileBean();
            fileBean.setName(file.getName());
            fileBean.setDirName(file.getAbsolutePath());
            fileBean.setCreateTime(String.valueOf(file.lastModified()));
            result.add(fileBean);
        });
        return result;
    }

}
