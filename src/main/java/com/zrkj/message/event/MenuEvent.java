package com.zrkj.message.event;

/**
 * �Զ���˵��¼�
 * 
 * @author liufeng
 * @date 2013-11-04
 */
public class MenuEvent extends BaseEvent {
	// �¼�KEYֵ�����Զ���˵��ӿ���KEYֵ��Ӧ
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
