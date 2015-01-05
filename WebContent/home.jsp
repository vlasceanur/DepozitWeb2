<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
StringBuilder sb = new StringBuilder();
Map<String, String> links = (HashMap<String, String>)request.getSession().getAttribute("links");
if(links != null)
{
	for(Entry<String, String> entry : links.entrySet())
	{
		String newLink = String.format("<a href=%s>%s</a><br/>", entry.getKey(), entry.getValue());
		sb.append(newLink);
	}
}
%>
<%=sb.toString() %>
</body>
</html>