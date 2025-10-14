package io.tiklab.sward.support.constant;

public class MessageConstant {


    // 系统
    public static final String SW_RPY_CREATE = "SW_RPY_CREATE";	//	创建知识库

    public static final String SW_RPY_UPDATE = "SW_RPY_UPDATE";	// 更新知识库

    public static final String SW_RPY_DELETE = "SW_RPY_DELETE";	//	删除知识库

    public static final String SW_RPY_RESTORE = "SW_RPY_RESTORE";	//	恢复知识库

    public static final String SW_RPY_ARCHIVE = "SW_RPY_ARCHIVE";	//	归档知识库

    public static final String SW_RPY_RECYCLE = "SW_RPY_RECYCLE";	//	移入回收站


    // 项目
    public static final String SW_DOC_CREATE = "SW_DOC_CREATE";	//	创建文档

    public static final String SW_DOC_DELETE = "SW_DOC_DELETE";	//	删除文档

    public static final String SW_DOC_ARCHIVE = "SW_DOC_ARCHIVE";	//	归档文档

    public static final String SW_DOC_RESTORE = "SW_DOC_RESTORE";	//	恢复文档

    // 项目评审
    public static final String SW_REVIVW_CREATE = "SW_REVIVW_CREATE";	//	创建评审

    public static final String SW_REVIVW_REVIVW = "SW_REVIVW_REVIVW";	//	评审文档

    public static final String SW_REVIVW_REVOKE = "SW_REVIVW_REVOKE";	//	撤销评审


    public static String messageConstantToLink(String messageType){
        switch (messageType){
            case SW_RPY_CREATE,SW_RPY_UPDATE,SW_RPY_RESTORE -> {
                return "/repository/${rpyId}/overview";
            }
            case SW_RPY_DELETE -> {
                return null;
            }
            case SW_RPY_ARCHIVE -> {
                return "/setting/archived";
            }
            case SW_RPY_RECYCLE -> {
                return "/setting/recycle";
            }

            case SW_DOC_CREATE,SW_DOC_RESTORE -> {
                return "/repository/${rpyId}/doc/rich/${nodeId}";
            }
            case SW_DOC_DELETE -> {
                return "/repository/${rpyId}/overview";
            }
            case SW_DOC_ARCHIVE -> {
                return "/repository/${rpyId}/set/archivedBin";
            }

            case SW_REVIVW_CREATE,SW_REVIVW_REVIVW -> {
                return "/repository/${rpyId}/set/review/${reviewId}";
            }
            case SW_REVIVW_REVOKE -> {
                return "/repository/${rpyId}/set/review";
            }
            default -> {
                return null;
            }
        }
    }




}
