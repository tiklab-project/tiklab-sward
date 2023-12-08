package io.thoughtware.sward.support.model;


import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 项目下事项类型模型
 */
@ApiModel
@Join
@Mapper
public class WorkTypeDm extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="projectId",desc="所属项目")
    private String projectId;

    @ApiProperty(name="workType",desc="事项类型")
    @Mappings({
            @Mapping(source = "workType.id",target = "workTypeId")
    })
    @JoinQuery(key = "id")
    private WorkType workType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

}
