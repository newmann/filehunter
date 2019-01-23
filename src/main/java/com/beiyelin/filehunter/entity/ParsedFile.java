package com.beiyelin.filehunter.entity;

import lombok.Data;

import javax.persistence.*;

import static com.beiyelin.filehunter.entity.ParsedFile.TABLE_NAME;

/**
 * @Author: newmann hu
 * @Date: created 15:56 2019-01-23
 * @Description:
 **/
@Entity
@Table(name=TABLE_NAME)
@Data
public class ParsedFile {
    public  static final String TABLE_NAME = "parse_file";
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,length = 1024)
    private String path;

    @Column(unique = true,nullable = false,length = 2048)
    private String pathName;

    @Column(nullable = false,length = 1024)
    private String fileName;

    @Column(columnDefinition = "TEXT")
    private String keywords;

    @Column(columnDefinition = "TEXT")
    private String fileContent;

    private Long lastUpdateTime;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(columnDefinition = "TEXT")
    private String peopleNames;

    @Column(columnDefinition = "TEXT")
    private String addressNames;

    @Column(columnDefinition = "TEXT")
    private String orgNames;

    @Column(columnDefinition = "TEXT")
    private String phrase;

}
