package io.tiklab.sward.repository.model;

import io.tiklab.core.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class WikiRepositoryPermissions extends BaseModel {


    private Boolean delete = false;

    private String deleteCode = "wi_setting_wiki_delete";

    private Boolean edit = false;

    private String editCode = "wi_setting_wiki_update";

    private Boolean setting = false;

    private String settingCode = "wi_setting_wiki_setting";

    public List<String> findPermissionList(){
        List<String> permissions = new ArrayList<>();
        permissions.add(deleteCode);
        permissions.add(editCode);
        permissions.add(settingCode);
        return permissions;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Boolean getSetting() {
        return setting;
    }

    public void setSetting(Boolean setting) {
        this.setting = setting;
    }


    public String getDeleteCode() {
        return deleteCode;
    }

    public void setDeleteCode(String deleteCode) {
        this.deleteCode = deleteCode;
    }

    public String getEditCode() {
        return editCode;
    }

    public void setEditCode(String editCode) {
        this.editCode = editCode;
    }

    public String getSettingCode() {
        return settingCode;
    }

    public void setSettingCode(String settingCode) {
        this.settingCode = settingCode;
    }

    public void addPipelinePermission(WikiRepositoryPermissions permissions, String permissionsString){

        if (permissionsString.contains(permissions.getDeleteCode())){
            permissions.setDelete(true);
        }

        if (permissionsString.contains(permissions.getEditCode())){
            permissions.setEdit(true);
        }

        if (permissionsString.contains(permissions.getSettingCode())){
            permissions.setSetting(true);
        }
    }
}
