package com.doublekit.wiki.repository.codegen;

import com.doublekit.codegen.CodeGeneratorTemplate;
import com.doublekit.codegen.config.ProjectGeneratorConfig;
import com.doublekit.codegen.config.ModuleGeneratorConfig;
import com.doublekit.wiki.repository.entity.RepositoryPo;
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
    protected ModuleGeneratorConfig getModuleGeneratorConfig() {
        ModuleGeneratorConfig config = new ModuleGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setEntity(RepositoryPo.class);
        config.setPkg("com.doublekit.wiki.repository");
        config.setModel("Repository");
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }
}