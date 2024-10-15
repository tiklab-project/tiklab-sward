package io.thoughtware.sward.support.service;



import io.thoughtware.licence.appauth.service.ApplyAuthService;
import io.thoughtware.licence.licence.model.Version;
import io.thoughtware.licence.licence.service.VersionService;
import io.thoughtware.message.message.service.MessageNoticeService;
import io.thoughtware.message.setting.service.MessageSendTypeService;
import io.thoughtware.privilege.role.service.RoleService;
import io.thoughtware.security.backups.service.BackupsDbService;
import io.thoughtware.sward.repository.model.WikiRepository;
import io.thoughtware.sward.repository.model.WikiRepositoryQuery;
import io.thoughtware.sward.repository.service.WikiRepositoryService;
import io.thoughtware.user.directory.service.UserDirService;
import io.thoughtware.user.orga.service.OrgaService;
import io.thoughtware.user.user.service.UserService;
import io.thoughtware.user.usergroup.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SettingStaticsServiceImpl implements SettingStaticsService{
    @Autowired
    UserService userService;

    @Autowired
    OrgaService orgaService;

    @Autowired
    UserGroupService userGroupService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserDirService userDirService;
    @Autowired
    MessageNoticeService messageNoticeService;

    @Autowired
    MessageSendTypeService messageSendTypeService;


    @Autowired
    VersionService versionservice;

    @Autowired
    ApplyAuthService applyAuthService;

    @Autowired
    BackupsDbService backupsDbService;

    @Autowired
    SystemUrlService systemUrlService;

    @Autowired
    WikiRepositoryService wikiRepositoryService;
    @Override
    public HashMap<String, Object> findOrgaNum() {
        HashMap<String, Object> numMap = new HashMap<>();

        // 用户
        Integer userNumber = userService.findUserNumber();
        Integer orgaNumber = orgaService.findOrgaNumber();
        Integer userGroupNumber = userGroupService.findUserGroupNumber();
        Integer roleNumber = roleService.findRoleNumber();
        Integer userDirNumber = userDirService.findUserDirNumber();

        numMap.put("user", userNumber);
        numMap.put("orga", orgaNumber);
        numMap.put("userGroup", userGroupNumber);
        numMap.put("role", roleNumber);
        numMap.put("userDir", userDirNumber);
        // 消息
        Integer messageNoticeNumber = messageNoticeService.findNoticeNumber("sward");
        Integer sendTypeNumber = messageSendTypeService.findSendTypeNumber();
        numMap.put("messageNotice", messageNoticeNumber);
        numMap.put("sendType", sendTypeNumber);



        // 版本
        Version version = versionservice.getVersion();
        Integer applyAuthNumber = applyAuthService.findApplyAuthNumber();
        numMap.put("version", version);
        numMap.put("applyAuth", applyAuthNumber);

        // 备份
        String lastBackupsTime = backupsDbService.findLastBackupsTime();
        numMap.put("lastBackups", lastBackupsTime);

        // 地址配置
        Integer systemUrlNumber = systemUrlService.findSystemUrlNumber();
        numMap.put("systemUrl", systemUrlNumber);

        // 查找回收站的知识库个数
        WikiRepositoryQuery wikiRepositoryQuery = new WikiRepositoryQuery();
        wikiRepositoryQuery.setStatus("archived");
        wikiRepositoryQuery.setRecycle(null);
        List<WikiRepository> repositoryList = wikiRepositoryService.findRepositoryList(wikiRepositoryQuery);
        numMap.put("archived", repositoryList.size());

        wikiRepositoryQuery.setStatus(null);
        wikiRepositoryQuery.setRecycle("1");
        repositoryList = wikiRepositoryService.findRepositoryList(wikiRepositoryQuery);
        numMap.put("recycle", repositoryList.size());
        return numMap;
    }
}
