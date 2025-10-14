package io.tiklab.sward.support.message.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.message.message.model.SendMessageNotice;
import io.tiklab.message.message.service.SendMessageNoticeService;
import io.tiklab.privilege.vRole.model.VRoleDomain;
import io.tiklab.rpc.annotation.Exporter;
import io.tiklab.sward.node.node.model.Node;
import io.tiklab.sward.node.node.service.NodeService;
import io.tiklab.sward.repository.model.WikiRepository;
import io.tiklab.sward.repository.service.WikiRepositoryService;
import io.tiklab.sward.support.constant.MessageConstant;
import io.tiklab.sward.support.message.model.SwardMessage;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.tiklab.sward.support.constant.MessageConstant.*;

@Exporter
@Service
public class SwardSendMessageServiceImpl implements SwardSendMessageService {

    @Autowired
    WikiRepositoryService wikiRepositoryService;

    @Autowired
    NodeService nodeService;

    @Autowired
    UserProcessor userService;

    @Autowired
    SendMessageNoticeService dispatchNoticeService;

    @Value("${external.url:null}")
    String baseUrl;

    @Value("${app.name:sward}")
    String appName;

    @Override
    public void sendMessage(SwardMessage swardMessage){
        String rpyId = swardMessage.getRpyId();
        String noticeId = swardMessage.getNoticeId();
        WikiRepository repository = wikiRepositoryService.findRepository(swardMessage.getRpyId());

        User user = userService.findUser(swardMessage.getUserId());
        String userName = StringUtils.isEmpty(user.getNickname()) ? user.getName() : user.getNickname();

        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);

        String sendType = swardMessage.getSendType();
        switch (sendType){
            case SW_RPY_CREATE, SW_RPY_RESTORE, SW_RPY_ARCHIVE, SW_RPY_RECYCLE ->{
                map.put("rpyId", rpyId);
                map.put("rpyName", repository.getName());
            }
            case SW_RPY_UPDATE, SW_RPY_DELETE ->{
                map.put("rpyId", rpyId);
                map.put("rpyName", swardMessage.getRpyName());
            }
            default -> {
                throw new RuntimeException("未知消息类型:"+sendType);
            }
        }

        SendMessageNotice dispatchNotice = new SendMessageNotice();
        dispatchNotice.setId(noticeId);
        String jsonString = JSONObject.toJSONString(map);
        dispatchNotice.setEmailData(jsonString);
        dispatchNotice.setDingdingData(jsonString);
        dispatchNotice.setSiteData(jsonString);
        dispatchNotice.setQywechatData(jsonString);
        dispatchNotice.setDomainId(rpyId);
        dispatchNotice.setLink(MessageConstant.messageConstantToLink(sendType));
        dispatchNotice.setBaseUrl(baseUrl);
        dispatchNotice.setAction((String) map.get("rpyName"));
        dispatchNotice.setSendId(LoginContext.getLoginId());
        dispatchNoticeService.sendMessageNotice(dispatchNotice);
    }


    @Override
    public void sendDmMessage(SwardMessage swardMessage){

        String rpyId = swardMessage.getRpyId();
        String noticeId = swardMessage.getNoticeId();
        WikiRepository repository = wikiRepositoryService.findRepository(swardMessage.getRpyId());

        User user = userService.findUser(swardMessage.getUserId());
        String userName = StringUtils.isEmpty(user.getNickname()) ? user.getName() : user.getNickname();

        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);

        SendMessageNotice dispatchNotice = new SendMessageNotice();
        VRoleDomain vRoleDomain = dispatchNotice.getvRoleDomain();
        if (Objects.isNull(vRoleDomain)){
            vRoleDomain = new VRoleDomain();
        }

        String nodeId = swardMessage.getNodeId();
        Node node = nodeService.findOne(nodeId);
        map.put("rpyId", rpyId);
        map.put("nodeId", nodeId);
        map.put("rpyName", repository.getName());
        String sendType = swardMessage.getSendType();
        switch (sendType){
            case SW_DOC_CREATE, SW_DOC_RESTORE, SW_DOC_ARCHIVE ->{
                map.put("docName", node.getName());
                dispatchNotice.setAction((String) map.get("docName"));
            }
            case  SW_DOC_DELETE ->{
                map.put("docName", swardMessage.getNodeName());
                dispatchNotice.setAction((String) map.get("docName"));
            }
            case  SW_REVIVW_CREATE,SW_REVIVW_REVIVW,SW_REVIVW_REVOKE ->{
                map.put("reviewName", swardMessage.getReviewName());
                map.put("reviewId", swardMessage.getReviewId());
                dispatchNotice.setAction((String) map.get("reviewName"));
                if (!Objects.isNull(node)){
                    map.put("docName", node.getName());
                    dispatchNotice.setAction((String) map.get("docName"));
                }
                vRoleDomain.setModelId(swardMessage.getReviewId());
                String status = swardMessage.getStatus();
                if (!StringUtils.isEmpty(status)){
                    if (status.equals("pass")){
                        map.put("status", "通过");
                        map.put("reviewColor", "info");
                    }else {
                        map.put("status", "未通过");
                        map.put("reviewColor", "warning");
                    }
                }

            }
            default -> {
                throw new RuntimeException("未知消息类型:"+sendType);
            }
        }

        dispatchNotice.setId(noticeId);
        String jsonString = JSONObject.toJSONString(map);
        dispatchNotice.setEmailData(jsonString);
        dispatchNotice.setDingdingData(jsonString);
        dispatchNotice.setSiteData(jsonString);
        dispatchNotice.setQywechatData(jsonString);
        dispatchNotice.setDomainId(rpyId);
        dispatchNotice.setLink(MessageConstant.messageConstantToLink(sendType));
        dispatchNotice.setBaseUrl(baseUrl);
        dispatchNotice.setSendId(LoginContext.getLoginId());




        dispatchNoticeService.sendDmMessageNotice(dispatchNotice);
    }

}
