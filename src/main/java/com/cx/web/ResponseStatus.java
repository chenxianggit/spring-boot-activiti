package com.cx.web;


/**
 * 系统返回信息描述枚举类
 */
public enum  ResponseStatus {


	OPERATION_SUCCESS("操作成功", "operation success", 200),





    USER_HAS_GAGGED("该用户已被禁言","user has gagged",50044),

    USER_IS_GAGGED("因违反用户协议，已被禁止使用此功能", "This feature has been banned for breach of user agreement", 50045),
    
	COMMON_ERROR_RESULT("通用错误返回,请安具体错误返回详情", "common error result", 500999);
	
    public final String message;

    public final String englishMessage;

    public final int status;

    ResponseStatus(String message,String englishMessage,int status){
        this.message = message;
        this.englishMessage = englishMessage;
        this.status = status;
    }

}
