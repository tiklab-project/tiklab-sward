package io.tiklab.sward.support.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.user.user.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 事项模型
 */
@ApiModel
@Mapper
@Join
public class WorkItem extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private String id;

    @ApiProperty(name = "code",desc = "编码")
    private String code;



    @NotNull
    @ApiProperty(name="title",desc="标题",required = true)
    private java.lang.String title;




    @NotNull
    @ApiProperty(name="project",desc="所属项目",required = true)
    @Mappings({
            @Mapping(source = "project.id",target = "projectId")
    })
    @JoinQuery(key = "id")
    private Project project;




    @ApiProperty(name="workTypeSys",desc="所属系统的事项类型")
    @Mappings({
            @Mapping(source = "workTypeSys.id",target = "workTypeSysId")
    })
    @JoinQuery(key = "id")
    private WorkType workTypeSys;




    @ApiProperty(name="workStatusNode",desc="所属系统事项状态id")
    @Mappings({
            @Mapping(source = "workStatusNode.id",target = "workStatusNodeId")
    })
    @JoinQuery(key = "id")
    private StateNode workStatusNode;









    @ApiProperty(name="builder",desc="创建人")
    @Mappings({
            @Mapping(source = "builder.id",target = "builderId")
    })
    @JoinQuery(key = "id")
    private User builder;

    @ApiProperty(name="assigner",desc="经办人")
    @Mappings({
            @Mapping(source = "assigner.id",target = "assignerId")
    })
    @JoinQuery(key = "id")
    private User assigner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public WorkType getWorkTypeSys() {
        return workTypeSys;
    }

    public void setWorkTypeSys(WorkType workTypeSys) {
        this.workTypeSys = workTypeSys;
    }

    public StateNode getWorkStatusNode() {
        return workStatusNode;
    }

    public void setWorkStatusNode(StateNode workStatusNode) {
        this.workStatusNode = workStatusNode;
    }

    public User getBuilder() {
        return builder;
    }

    public void setBuilder(User builder) {
        this.builder = builder;
    }

    public User getAssigner() {
        return assigner;
    }

    public void setAssigner(User assigner) {
        this.assigner = assigner;
    }
}

