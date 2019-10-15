<%@ page import="test.java.DbManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %><%--
  Created by IntelliJ IDEA.
  User: rauan
  Date: 2019-10-14
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
    DbManager db = new DbManager();
    Connection con = db.getConnection();
    if (con == null) {
      out.println("system failed");
    } else {
      String sql = "SELECT * FROM Employee";

      Statement statement = con.createStatement();
      ResultSet result = statement.executeQuery(sql);

      int count = 0;

      while (result.next()){
        String lname = result.getString("Lname");
        String fname = result.getString("Fname");
        String gender = result.getString("Gender");
        String salary = result.getString("Salary");


        String output = "<p>User #%d: %s - %s - %s - %s</p>";
        out.println(String.format(output, ++count, lname, fname, gender, salary, "\n"));
      }
    }

  %>
  </body>
</html>
