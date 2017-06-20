package com.zrkj.message.event;

/**
 * ɨ���������ά���¼�
 * 
 * @author liufeng
 * @date 2013-11-04
 */
public class QRCodeEvent extends BaseEvent {
	// �¼�KEYֵ
	private String EventKey;
	// ���ڻ�ȡ��ά��ͼƬ
	private String Ticket;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}
}
