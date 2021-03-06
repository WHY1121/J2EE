<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>表单模板列表</title>
    <%@ include file="/WEB-INF/jsp/public/common.jsp" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 模板管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="250px">模板名称</td>
				<td width="250px">所用流程</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="documentTemplateList">
        <s:iterator value="apList">
			<tr class="TableDetail1 template">
				<td>${name}&nbsp;</td>
				<td>${processName}&nbsp;</td>
				<td><s:a onClick="return delConfirm()" action="applicationTemplateAction_delete?id=%{id}">删除</s:a>
					<s:a action="applicationTemplateAction_editUI?id=%{id}">修改</s:a>
					<s:a action="applicationTemplateAction_download?id=%{id}">下载</s:a>
				</td>
			</tr>
		</s:iterator>	
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
			<s:a action="applicationTemplateAction_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png"/></s:a>
        </div>
    </div>
</div>

</body>
</html>
