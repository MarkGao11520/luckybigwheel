package com.zrkj.tools;

import com.zrkj.pojo.User;
import com.zrkj.pojo.WeixinOauth2Token;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * �߼��ӿڹ�����
 * 
 * @author liufeng
 * @date 2013-11-9
 */
public class AdvancedUtil {
	private static Logger log = LoggerFactory.getLogger(AdvancedUtil.class);


	/**
	 * ��ȡ��ҳ��Ȩƾ֤
	 * 
	 * @param appId �����˺ŵ�Ψһ��ʶ
	 * @param appSecret �����˺ŵ���Կ
	 * @param code
	 * @return WeixinAouth2Token
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
		WeixinOauth2Token wat = null;
		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// ��ȡ��ҳ��Ȩƾ֤
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("��ȡ��ҳ��Ȩƾ֤ʧ�� errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return wat;
	}

	/**
	 * Õ®π˝Õ¯“≥ ⁄»®ªÒ»°”√ªß–≈œ¢
	 *
	 * @param accessToken Õ¯“≥ ⁄»®Ω”ø⁄µ˜”√∆æ÷§
	 * @param openId ”√ªß±Í ∂
	 * @return SNSUserInfo
	 */
	@SuppressWarnings( { "deprecation", "unchecked" })
	public static User getUserInfo(String accessToken, String openId) {
		User snsUserInfo = null;
		// ∆¥Ω”«Î«Ûµÿ÷∑
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		// Õ®π˝Õ¯“≥ ⁄»®ªÒ»°”√ªß–≈œ¢
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				snsUserInfo = new User();
				// ”√ªßµƒ±Í ∂
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				// Í«≥∆
				snsUserInfo.setNikeName(jsonObject.getString("nickname"));
				// ”√ªßÕ∑œÒ
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				// ”√ªßÃÿ»®–≈œ¢
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("ªÒ»°”√ªß–≈œ¢ ß∞‹ errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return snsUserInfo;
	}



}
