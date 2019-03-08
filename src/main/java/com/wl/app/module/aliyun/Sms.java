package com.wl.app.module.aliyun;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.wl.app.service.util.ExpiryMap;
import com.wl.app.service.util.RandomUtil;

public class Sms {
	
	public final static String SMS_SIGN = "专线邦";
	public final static String ALIYUN_SMS_VCODE_TEMPLATE_CODE = "SMS_153327832";
	public final static String ALIYUN_SMS_CONTACT_TEMPLATE_CODE = "SMS_159627336";
	public final static String SEND_SUCCESS = "999999";
	public final static String SEND_ERROR = "100000";
	

	private final static Sms me = new Sms();
	private static ExpiryMap<String, String> verificationCodeMap = new ExpiryMap<>();
	
	private Sms() {}
	
	public static Sms me() {
		return me;
	}
	/**
	 * 发送验证码
	 * @param mobilePhone
	 * @return
	 */
	public String sendVerificationCode(String mobilePhone) {
		String vcode = RandomUtil.generateVerificationCode();
		SendSmsResponse response = null;
		try {
			response = AliyunSms.sendSmsVcode(mobilePhone, SMS_SIGN, ALIYUN_SMS_VCODE_TEMPLATE_CODE, vcode);
			if(response.getCode().equals("OK")) {
				verificationCodeMap.put(mobilePhone, vcode);
				System.out.println("发送的验证码为:"+vcode);
				return SEND_SUCCESS;
			}else {
				System.out.println(response.getMessage());
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
		System.out.println("发送的验证码为:"+vcode);
		return SEND_ERROR;
	}
	
	/**
	 * 发送业务通知短信
	 * @param mobilePhone
	 * @return
	 */
	public String sendContact(String mobilePhone,String userMobilePhone) {
		SendSmsResponse response = null;
		try {
			response = AliyunSms.sendSmsContact(mobilePhone, SMS_SIGN, ALIYUN_SMS_CONTACT_TEMPLATE_CODE, userMobilePhone);
			System.out.println(response.getMessage());
			if(response.getCode().equals("OK")) {
				return SEND_SUCCESS;
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return SEND_ERROR;
	}
	
	/**
	 * 验证输入的验证码是否正确
	 * @param mobilePhone
	 * @param vcode
	 * @return
	 */
	public boolean checkVerificationCode(String mobilePhone,String vcode) {
		
		Object value = verificationCodeMap.isInvalid(mobilePhone);
		
		if(value == null || "".equals(value.toString()) || "-1".equals(value.toString())) {
			return false;
		}
		
		if(vcode == null || "".equals(vcode) || vcode.length() != RandomUtil.VCODE) {
			return false;
		}
		
		if(vcode.equals(value.toString())) {
			verificationCodeMap.remove(mobilePhone);
			return true;
		}
		return false;
	}
}
