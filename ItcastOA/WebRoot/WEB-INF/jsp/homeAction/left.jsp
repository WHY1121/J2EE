<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>导航菜单</title>
<%@ include file="/WEB-INF/jsp/public/common.jsp"%>
<script language="JavaScript" src="script/menu.js"></script>
<link type="text/css" rel="stylesheet" href="style/blue/menu.css" />
</head>
<body style="margin: 0">
<div id="Menu">
    <ul id="MenuUl">
        <li class="level1">
            <div onClick="menuClick(this)" class="level1Style"><img src="style/images/MenuIcon/FUNC20001.gif" class="Icon" />个人办公</div>
            <ul style="display: none;" class="MenuLevel2">
                <li class="level2">
                    <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 个人考勤</div>
                </li>
                <li class="level2">
                    <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 日程安排</div>
                </li>
                <li class="level2">
                    <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 工作计划</div>
                </li>
                <li class="level2">
                    <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 工作日记</div>
                </li>
                <li class="level2">
                	<div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 通讯录</div>
                </li>
            </ul>
        </li>
    </ul>
</div>
</body>
</html>
