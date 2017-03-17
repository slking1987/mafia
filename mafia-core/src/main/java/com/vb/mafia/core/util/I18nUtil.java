package com.vb.mafia.core.util;

import com.vb.mafia.core.constant.ConfigConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class I18nUtil {
	private static MessageSource messageSource;

	public static String getMsg(String name, Object... params) {
		return messageSource.getMessage(name, params, ConfigConstant.DEFAULT_LOCALE);
	}

	public static String getMsg(String name, String defaultMsg, Object... params)
	{
		return messageSource.getMessage(name, params, defaultMsg, ConfigConstant.DEFAULT_LOCALE);
	}

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		I18nUtil.messageSource = messageSource;
	}

}
