<%@page import="java.util.Map.Entry"%>
<%@page import="pojos.Item"%>
<%@page import="java.util.List"%>
<%@page import="pojos.Warehouse"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
StringBuilder sb = new StringBuilder();
Map<Warehouse, List<Item>> info = (Map<Warehouse, List<Item>>)request.getAttribute("items");

String table = "<table>#</table>";
String trth1 = "<tr><th colspan=\"4\">#</th></tr>";
String trth2 = "<tr>" +
				"<th>ID</th>" +
				"<th>NAME</th>"+
				"<th>VOLUME</th>"+
				"<th>PRICE</th>"+
				"</tr>";
String trtd = "<tr class=\"info\">"+
				"<td>#1</td>" +
				"<td>#2</td>" +
				"<td>#3</td>" +
				"<td>#4</td>" +
			   "</tr>";

for(Entry<Warehouse, List<Item>> e : info.entrySet())
{
		Warehouse wh = e.getKey();
		String content = trth1.replace("#", wh.getId() + " - " + wh.getName() + " | " + wh.getLocation());
		content+= trth2;
		for(Item i: e.getValue())
		{
			String ntrtd = trtd.replace("#1", i.getId() +"")
						 .replace("#2", i.getName())
						 .replace("#3", i.getVolume() + "")
						 .replace("#4", i.getPrice() + "");			
			content+=ntrtd;
		}	
		sb.append(table.replace("#", content));
}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Browse Items</title>
<style type="text/css">
	
	tr, th, td
	{
		border-style: solid;
		border-width: 1px;
		padding: 3px 10px;
	}
	table
	{
		border-collapse: collapse;
		margin: 30px auto;
	}
	body
	{
		text-align: center;
	}
	.info
	{
		color: blue;
	}
	
</style>
</head>
<body>
<%=sb.toString() %>
</body>
</html>