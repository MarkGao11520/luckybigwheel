package com.zrkj.message.req;

/**
 * ������Ϣ���ࣨ��ͨ�û� -> �����ʺţ�
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class BaseMessage {
	// ������΢�ź�
	private String ToUserName;
	// ���ͷ��ʺţ�һ��OpenID��
	private String FromUserName;
	// ��Ϣ����ʱ�� �����ͣ�
	private long CreateTime;
	// ��Ϣ����
	private String MsgType;
	// ��Ϣid��64λ����
	private long MsgId;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
}
