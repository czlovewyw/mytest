package org.cz.security;

public class LicConstParameter {

	// license与看门狗权限值常量
	public static final String AUTHENTICATION_RESULT = "AUTHENTICATION_RESULT";
	public static final String AUTHENTICATION_YES = "YES";
	public static final String AUTHENTICATION_NO = "NO";
	public static final String LICENSE_EXPIRE_DATE = "sysExpireDate";
	public static final String LICENSE_DEVICE_NUMBER = "licEnableDeviceNumber";
	public static final String LICENSE_CAMERA_NUMBER = "licEnableRegionCameraNumber";
	public static final String LICENSE_REMAIN_DAYS = "remainingDays";
	public static final String LICENSE_WARN_DAYS = "daysOflicenseDatedWarn";
	public static final String DOG_REMAIN_DAYS = "dogRemainDay";
	public static final String LICENSE_USING_DOG = "isUsingDog";
	public static final String LICENSE_USING_DOG_YES = "YES";
	public static final String LICENSE_USING_DOG_NO = "NO";
	public static final String LICENSE_USING_DOG_FLAG = "isHasUsingDog";
	public static final String LICENSE_USING_DOG_FLAG_YES = "YES";
	public static final String LICENSE_USING_DOG_FLAG_NO = "NO";
	public static final String AUTHENTICATION_WITHOUT_DOG = "AUTHENTICATION_WITHOUT_DOG";
	public static final String AUTHENTICATION_WITHOUT_DOG_YES = "YES";
	public static final String AUTHENTICATION_WITHOUT_DOG_NO = "NO";
	public static final String LICENSE_TOMCAT_COMMAND_WIN = "taskkill /f /im tomcat*.exe /im java.exe";
	public static final String LICENSE_TOMCAT_COMMAND_LINUX = "kill -9 `ps -ef | grep Djava.uti | grep -v tail | grep -v vi | grep -v grep | awk '{print $2}'`";
	public static final String IS_MAC_LOGIN = "isMacLogin";

	public static final String DEFAULT_CHARSET = "UTF-8";
	// RSA 常量
	public static final String RSA_PUB_KEY_FILE = "/WEB-INF/classes/copyright/RSAPublicKey.xml";
	public static final String DIRECTORY_INIT_PARAM = "/WEB-INF/classes/copyright/init-param.txt";

	// license文件路径
	public static final String LICENSE_FILE = "/copyright/license.cyt";
}
