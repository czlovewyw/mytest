<%@ page contentType="text/html; charset=utf-8"%>
<%@ page
		import="java.lang.*"%>
<%@page
		import="java.security.cert.X509Certificate"%>
<%
	String path = request.getContextPath();
%>
<html>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">
<%
	String myinfo = null;
	String sn = "";
	try {
		X509Certificate[] certs = (X509Certificate[]) request
				.getAttribute("javax.servlet.request.X509Certificate");
		if (certs == null) {
			out.println("错误！请提交证书！");
			return;
		}
		X509Certificate gaX509Cert = null;
		gaX509Cert = certs[0];
		//获取序列号
		sn = gaX509Cert.toString();
		sn = sn.substring(sn.indexOf("CN="), sn.indexOf(","));
		String[] dnSplit1 = sn.split(" ");
		String strUsername = dnSplit1[0].substring(
				dnSplit1[0].indexOf("=") + 1).trim();
		String cardID = dnSplit1[1].trim();
		out.println(strUsername+":"+cardID);

	} catch (Exception e) {
		out.println("错误！" + e.getMessage());//－－此处即为获取并显示统一提示信息的方法。
	}
%>
</body>
</html>