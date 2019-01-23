package com.beiyelin.filehunter.service

import cn.hutool.core.io.FileUtil
import com.hankcs.hanlp.HanLP
import com.hankcs.hanlp.seg.Segment
import com.hankcs.hanlp.seg.common.Term
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.AutoDetectParser
import org.apache.tika.sax.BodyContentHandler
import org.springframework.stereotype.Service

/**
 @Author: newmann hu
 @Date: created 14:24 2019-01-22
 @Description:
  * */
@Service
class FileParseService {
    static DEFAULT_KEYWORDS_NUMBER = 10
    static DEFAULT_PEOPLE_NAME_NUMBER = 10
    static DEFAULT_ADDRESS_NAME_NUMBER = 10
    static DEFAULT_ORG_NAME_NUMBER = 10
    static DEFAULT_PHASE_NUMBER = 10
    static DEFAULT_ABSTRACT_NUMBER = 5

    static List<String> getKeywords(String content, Integer count = DEFAULT_KEYWORDS_NUMBER){
        return HanLP.extractKeyword(content,count);
    }

    static List<String> getSummary(String content, Integer count = DEFAULT_ABSTRACT_NUMBER){
        return HanLP.extractSummary(content,count)
    }

    static List<String> getPeopleName(String content, Integer count = DEFAULT_PEOPLE_NAME_NUMBER){
        Segment segment = HanLP.newSegment().enableNameRecognize(true).enableTranslatedNameRecognize(true)
            .enableJapaneseNameRecognize(true)
        List<Term> terms =  segment.seg(content)
        List<String> result = new ArrayList<>()

        for(term in terms){
            result.add(term.word)
        }
        return result
    }

    static List<String> getPhase(String content, Integer count = DEFAULT_PHASE_NUMBER){
        return HanLP.extractPhrase(content,count)
    }


    static String extraceText(File file){
        BodyContentHandler handler = new BodyContentHandler(1024*1024*1024)
        AutoDetectParser parser = new AutoDetectParser()
        Metadata metadata = new Metadata()

        if(FileUtil.isEmpty(file)){
            return ""
        }else{
            InputStream stream = FileUtil.getInputStream(file)
            parser.parse(stream,handler,metadata)
            return handler.toString()

        }
    }
}
