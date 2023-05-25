package io.tiklab.kanass.repository.codegen;

import io.tiklab.codegen.CodeGeneratorTemplate;
import io.tiklab.codegen.config.CodeGeneratorConfig;
import io.tiklab.codegen.config.ProjectGeneratorConfig;
import io.tiklab.kanass.repository.entity.WikiRepositoryFocusEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectGeneratorConfig.class)
public class WikiWikiRepositoryFocusCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected CodeGeneratorConfig getCodeGeneratorConfig() {
        CodeGeneratorConfig config = new CodeGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setEntity(WikiRepositoryFocusEntity.class);
        config.setPkg("io.tiklab.kanass.repository");
        config.setModel("RepositoryFocus");
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }
}