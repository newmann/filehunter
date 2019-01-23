package com.beiyelin.filehunter.repository;

import com.beiyelin.filehunter.entity.ParsedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @Author: newmann hu
 * @Date: created 18:57 2019-01-23
 * @Description:
 **/
@Repository
public interface ParsedFileDao extends PagingAndSortingRepository<ParsedFile,Long> {

    Optional<ParsedFile> findByPathName(String pathName);

    long deleteByPathName(String pathName);

    long deleteAllByPath(String path);

    @Query(value = "select * from " + ParsedFile.TABLE_NAME +"  where ?1",nativeQuery = true)
    List<ParsedFile> fetchByCondition(String condition);
}
