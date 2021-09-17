package com.doublekit.wiki.repository.codegen;

import com.doublekit.codegen.CodeGeneratorTemplate;
import com.doublekit.codegen.config.ModuleGeneratorConfig;
import com.doublekit.codegen.config.ProjectGeneratorConfig;
import com.doublekit.wiki.repository.entity.DocumentPo;
import com.doublekit.wiki.repository.entity.DocumentTemplatePo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectGeneratorConfig.class)
public class DocumentTemplateCodeGeneratorTest extends CodeGeneratorTemplate {

    @Autowired
    ProjectGeneratorConfig projectGeneratorConfig;

    @Override
    protected ModuleGeneratorConfig getModuleGeneratorConfig() {
        ModuleGeneratorConfig config = new ModuleGeneratorConfig();
        config.setProjectGeneratorConfig(projectGeneratorConfig);
        config.setEntity(DocumentTemplatePo.class);
        config.setPkg("com.doublekit.wiki.repository");
        config.setModel("DocumentTemplate");
        return config;
    }

    @Test
    @Override
    public void generateForAll() {
        super.generateForAll();
    }
}