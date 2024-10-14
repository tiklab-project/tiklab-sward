package io.tiklab.sward.confluence.service;

import io.tiklab.sward.repository.model.WikiRepository;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Map;

/**
 * 导入第三方数据服务接口
 */
public interface ConfluenceImportData719Service {


    String writeData(List<Element> elements, Map<String, WikiRepository> CurrentProject, Map<String, Integer> Percent);

}