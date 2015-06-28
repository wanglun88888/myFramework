<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
    String csv = request.getParameter("csv");
    System.out.println(csv);
    if(!"".equals(csv)) {
            response.setHeader("Content-type","text/csv");
            response.setHeader("Content-disposition", "attachment;filename=chart.csv");
            response.getWriter().print(csv);
    }
%>