<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>导航菜单</title>
<%@ include file="/WEB-INF/jsp/public/common.jsp"%>
<script language="JavaScript" src="script/menu.js"></script>
<link type="text/css" rel="stylesheet" href="style/blue/menu.css" />
<script type="text/javascript">
function menuClick(menu){
//$(".MenuLevel2").not($(menu).siblings("ul")).hide();
    $(menu).siblings("ul").toggle();
}
</script>
</head>
<body style="margin: 0">
<div id="Menu">
    <ul id="MenuUl">
    <!-- 一级菜单 -->
     <s:iterator value="#application.priviledges">
     <s:if test="#session.user.hasPriviledgeByName(name)">
        <li class="level1">
            <div onClick="menuClick(this)" class="level1Style"><img src="style/images/MenuIcon/${icon}" class="Icon" />${name}</div>
            <ul style="display: none;" class="MenuLevel2">
            <!-- 二级菜单 -->
            <s:iterator value="children">
             <s:if test="#session.user.hasPriviledgeByName(name)">
                <li class="level2">
                    <div class="level2Style"><img src="style/images/MenuIcon/menu_arrow_single.gif" /> 
                    <a target="right" href="${pageContext.request.contextPath}/${url}.action">${name}</a>
                    </div>
                </li>
                </s:if>
             </s:iterator>
            </ul>
        </li>
        </s:if>
     </s:iterator>
    </ul>
</div>
</body>
</html>
