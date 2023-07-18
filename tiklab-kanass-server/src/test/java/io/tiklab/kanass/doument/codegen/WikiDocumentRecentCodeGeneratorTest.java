package io.tiklab.kanass.doument.codegen;

import io.tiklab.codegen.CodeGeneratorTemplate;
import io.tiklab.codegen.config.CodeGeneratorConfig;
import io.tiklab.codegen.config.ProjectGeneratorConfig;
import io.tiklab.kanass.document.entity.DocumentRecentEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectGeneratorConfig.class)
public class WikiDocumentRecentCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected CodeGeneratorConfig getCodeGeneratorConfig() {
        CodeGeneratorConfig config = new CodeGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setEntity(DocumentRecentEntity.class);
        config.setPkg("io.tiklab.kanass.document");
        config.setModel("DocumentRecent");
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }
}