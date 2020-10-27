<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>
<html>
<body>
<%
   Date now = new Date();
   SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
   out.print( "<h2 align=\"center\">" + simpleDateFormat.format(now) + "</h2>");
%>
</body>
</html>
