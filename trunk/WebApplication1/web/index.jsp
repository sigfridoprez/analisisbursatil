<%-- 
    Document   : index
    Created on : 17/02/2009, 06:46:22 PM
    Author     : sperezc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <%
    foo.DBTest tst = new foo.DBTest();
    tst.init();
  %>

  <h2>Results</h2>
    Foo <%= tst.getFoo() %><br/>
    Bar <%= tst.getBar() %>


    </body>
</html>
