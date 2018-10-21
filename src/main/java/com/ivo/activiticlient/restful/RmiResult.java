package com.ivo.activiticlient.restful;

import java.io.Serializable;

/**
 * @author wangjian
 * @date 2018/10/12
 */
public class RmiResult implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String order;

    private String sourceName;

    private String drafterName;

    private String draftGroupName;

    private String draftDate;

    private String senderName;

    private String sendDate;

    private String handleDate;

    private String handlerName;

    private String handlerGroupName;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDrafterName() {
        return drafterName;
    }

    public void setDrafterName(String drafterName) {
        this.drafterName = drafterName;
    }

    public String getDraftGroupName() {
        return draftGroupName;
    }

    public void setDraftGroupName(String draftGroupName) {
        this.draftGroupName = draftGroupName;
    }

    public String getDraftDate() {
        return draftDate;
    }

    public void setDraftDate(String draftDate) {
        this.draftDate = draftDate;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(String handleDate) {
        this.handleDate = handleDate;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getHandlerGroupName() {
        return handlerGroupName;
    }

    public void setHandlerGroupName(String handlerGroupName) {
        this.handlerGroupName = handlerGroupName;
    }

}
