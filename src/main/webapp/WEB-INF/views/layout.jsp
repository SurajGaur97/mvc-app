<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title><tiles:insertAttribute name="title" ignore="true" /></title>  
</head>  
<body>  
        <div>
        	<tiles:insertAttribute name="header" />
        </div>  
        <div style="float:left; padding:10px; width:15%;">
        	<tiles:insertAttribute name="menu" />
        </div>  
        <div style="float:left; padding:10px; width:80%; border-left:1px solid pink;">  
        	<tiles:insertAttribute name="body" />
        </div>  
        <div style="clear:both">
        	<tiles:insertAttribute name="footer" />
        </div>  
</body>  
</html>