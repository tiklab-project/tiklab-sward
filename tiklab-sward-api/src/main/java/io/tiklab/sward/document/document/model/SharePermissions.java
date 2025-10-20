package io.tiklab.sward.document.document.model;

import io.tiklab.core.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class SharePermissions extends BaseModel {


    private Boolean delete = false;

    private String deleteCode = "wi_setting_doc_share_delete";

    private Boolean view = false;

    private String viewCode = "wi_setting_doc_share_find";

    public List<String> findPermissionList(){
        List<String> permissions = new ArrayList<>();
        permissions.add(deleteCode);
        permissions.add(viewCode);
        return permissions;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public String getDeleteCode() {
        return deleteCode;
    }

    public void setDeleteCode(String deleteCode) {
        this.deleteCode = deleteCode;
    }

    public Boolean getView() {
        return view;
    }

    public void setView(Boolean view) {
        this.view = view;
    }

    public String getViewCode() {
        return viewCode;
    }

    public void setViewCode(String viewCode) {
        this.viewCode = viewCode;
    }

    public void addPipelinePermission(SharePermissions permissions, String permissionsString){

        if (permissionsString.contains(permissions.getDeleteCode())){
            permissions.setDelete(true);
        }
        if (permissionsString.contains(permissions.getViewCode())){
            permissions.setView(true);
        }
    }
}
