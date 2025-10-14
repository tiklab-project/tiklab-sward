package io.tiklab.sward.support.message.service;

import io.tiklab.sward.support.message.model.SwardMessage;

public interface SwardSendMessageService {

    /**
     * 发送系统消息
     * @param swardMessage 消息
     */
    void sendMessage(SwardMessage swardMessage);


    /**
     * 发送项目消息
     * @param swardMessage 消息
     */
    void sendDmMessage(SwardMessage swardMessage);

}
