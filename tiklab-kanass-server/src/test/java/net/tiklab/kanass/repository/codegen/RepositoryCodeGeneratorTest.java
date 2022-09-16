package net.tiklab.kanass.repository.codegen;

import net.tiklab.codegen.CodeGeneratorTemplate;
import net.tiklab.codegen.config.ProjectGeneratorConfig;
import net.tiklab.codegen.config.CodeGeneratorConfig;
import net.tiklab.kanass.repository.entity.RepositoryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectGeneratorConfig.class)
public class RepositoryCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected CodeGeneratorConfig getCodeGeneratorConfig() {
        CodeGeneratorConfig config = new CodeGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setEntity(RepositoryEntity.class);
        config.setPkg("net.tiklab.kanass.repository");
        config.setModel("Repository");
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }
}