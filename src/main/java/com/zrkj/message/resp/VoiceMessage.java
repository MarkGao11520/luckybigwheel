package com.zrkj.message.resp;

/**
 * ������Ϣ
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class VoiceMessage extends BaseMessage {
	// ����
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
