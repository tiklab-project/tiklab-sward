package io.thoughtware.sward.confluence.service;

import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.sward.repository.util.UncompressUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * jira 数据导入服务
 */
@Service
@EnableTransactionManagement
public class ConfluenceImportDataServiceImpl implements ConfluenceImportDataService {

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    ConfluenceImportData719Service confluenceImportData719Service;
    @Autowired
    private TransactionTemplate transactionTemplate;


    @Value("${unzip.path}")
    String unzipAddress;

    public final ExecutorService executorService = Executors.newCachedThreadPool();

    public static Map<String, Integer> Percent = new HashMap();

    public static Map<String, WikiRepository> CurrentProject = new HashMap();

    @Override
    public void importJiraData(InputStream inputStream) {
        transactionTemplate.execute((status) -> {
            // 在这里执行需要在事务中的操作
            return setData(inputStream);
        });

    }

    public String setData(InputStream inputStream) {
        BufferedReader unZIP=null;
        String createUserId = LoginContext.getLoginId();
        Percent.put(createUserId + "total", 0);
        Percent.put(createUserId + "currentNum", 0);
        Percent.put(createUserId + "status", 0);
        try {
            unZIP = new UncompressUtil().unZIP(inputStream, unzipAddress);
            String path=unzipAddress+"/entities.xml";
            SaxParseServiceImpl saxParseService = new SaxParseServiceImpl();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            try (FileInputStream fis = new FileInputStream(path);
                 Reader reader = new InvalidXMLCharFilter(new InputStreamReader(fis, "UTF-8"))) {
                // Wrap the Reader in InputSource
                InputSource is = new InputSource(reader);
                // Parse the XML using SAX parser
                parser.parse(is, saxParseService);
            }

            List<Element> elements = saxParseService.getElementList();
            System.out.println(elements);
            confluenceImportData719Service.writeData(elements, CurrentProject, Percent);
            Percent.put(createUserId + "status", 1);
            return "success";
        } catch (Exception e) {
            throw new ApplicationException(e);
        }

    }

    public JdbcTemplate getJdbcTemplet(){
      return  jpaTemplate.getJdbcTemplate();
    }

}
