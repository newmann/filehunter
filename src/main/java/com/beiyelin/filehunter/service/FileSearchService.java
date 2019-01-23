package com.beiyelin.filehunter.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONSupport;
import com.alibaba.fastjson.JSON;
import com.beiyelin.filehunter.bean.FileBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.util.*;

import static com.beiyelin.filehunter.service.FileParseService.*;

/**
 * @Author: newmann hu
 * @Date: created 8:04 2019-01-22
 * @Description:
 **/
@Service
public class FileSearchService {

    static String UNKNOWN_FILE_TYPE = "unknown file type";
    static List<String> noParseFileTypes = new ArrayList<>(Arrays.asList("application/x-zip-compressed",UNKNOWN_FILE_TYPE));

    @Autowired
    private FileParseService fileParseService;

    public List<File> getFiles(String dirName){
        return FileUtil.loopFiles(dirName
                , new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if(pathname.isDirectory()){
                    return true;
                }
                else
                {
                    String name = pathname.getName();
                    if(name.endsWith(".pptx") || name.endsWith(".docx") || name.endsWith("pdf") || name.endsWith("ppt")){
                        return true;
                    }else{
                        return  false;
                    }
                }
            }}
            );
    }

    public ObservableList<FileBean> getFilesObservable(String dirName) {
        List<File> files = getFiles(dirName);
        ObservableList<FileBean> result = FXCollections.observableArrayList();

        files.stream().forEach(file ->{
            FileBean fileBean;
            fileBean = new FileBean();
            fileBean.setName(file.getName());
            fileBean.setDirName(file.getAbsolutePath());
            fileBean.setCreateTime(String.valueOf(file.lastModified()));

            String fileType = StrUtil.emptyToNull(getFileType(file));
            System.out.println(fileType);
            if (!noParseFileTypes.contains(fileType)) {
                fileBean.setFileType(getFileType(file));
                String fileContent = extraceText(file);
                fileBean.setKeywords(CollUtil.join(getKeywords(fileContent), "，"));
                fileBean.setSummary(CollUtil.join(getPhase(fileContent), "，"));

//                fileBean.setSummary(CollUtil.join(getPeopleName(fileContent), "，"));
//                fileBean.setSummary(CollUtil.join(getSummary(fileContent), "，"));
            }
            System.out.println(JSON.toJSONString(fileBean));

            result.add(fileBean);
        });
        return result;
    }

    public String getFileType(File file){
        try {
            return Files.probeContentType(file.toPath());
        }catch (Exception ex){
            return UNKNOWN_FILE_TYPE;
        }

//        return FileTypeUtil.getType(file);
    }

}
