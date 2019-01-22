package com.beiyelin.filehunter.controller;

import com.beiyelin.filehunter.bean.FileBean;
import com.beiyelin.filehunter.service.FileSearchService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author: newmann hu
 * @Date: created 20:16 2019-01-21
 * @Description:
 **/
@FXMLController
public class IndexController implements Initializable {

    @FXML
    private TableView<FileBean> tblFileList;

    @FXML
    private TableColumn<FileBean,String> tbcFileName;

    @FXML
    private TableColumn<FileBean,String> tbcFileAbstract;
    @FXML
    private TableColumn<FileBean,String> tbcFilePathName;

    @Autowired
    private FileSearchService fileSearchService;
    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void searchFiles(){
        final String dirName = "e:\\dsm-baiduyun\\partner\\";
        tbcFileName.setCellValueFactory(new PropertyValueFactory<FileBean,String>("name"));
        tbcFileAbstract.setCellValueFactory(new PropertyValueFactory<FileBean,String>("createTime"));
        tbcFilePathName.setCellValueFactory(new PropertyValueFactory<FileBean,String>("dirName"));
        tblFileList.setItems(fileSearchService.getFilesObservable(dirName));

    }
}
