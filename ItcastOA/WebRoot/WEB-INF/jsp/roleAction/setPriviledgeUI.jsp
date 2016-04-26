<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>配置权限</title>
 <%@ include file="/WEB-INF/jsp/public/common.jsp" %>
 	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/file.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />
     <script language="javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
     <script type="text/javascript">
     $(function(){
        //给所有复选框增加单击事件
        $("[name=priviledgeIds]").click(function(){
           //自己选中和取消时候，所有下级也要选中和取消
           $(this).siblings("ul").find("input").attr('checked',this.checked);
           
           //选中一个权限得时候，选中直系所有权限
           if(this.checked){
              $(this).parents("li").children("input").attr('checked',true);
            }else{
               //当取消一个权限的时候，其同级没有选项的时候，上级也要取消
               if($(this).parent().siblings("li").children("input:checked").size() == 0){
               $(this).parent().parent().siblings("input").attr('checked',false);
               
               var start=$(this).parent().parent();
               if(start.parent().siblings("li").children("input:checked").size() == 0){
                 start.parent().parent().siblings("input").attr('checked',false);
                
                }
  
               }
             }
             //取消任意一个就把全选按钮变为空
         var allEle=document.getElementById("cbSelectAll");
         var pIdEle=document.getElementsByName("priviledgeIds");
         allEle.checked=pIdEle.length==checkPriviledgeIdNum(); 
            
        });
        function checkPriviledgeIdNum(){
           var pIdEle=document.getElementsByName("priviledgeIds");
           var count=0;
           for(var i=0;i<pIdEle.length;i++){
           
              if(pIdEle[i]){
                 count++;
              }
              return count;
           }
        }
                   
     });   
     
     </script>

</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 配置权限
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="roleAction_setPriviledge">
    <s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 正在为【${role.name}】配置权限 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<!--表头-->
					<thead>
						<tr align="LEFT" valign="MIDDLE" id="TableTitle">
							<td width="300px" style="padding-left: 7px;">
								<!-- 如果把全选元素的id指定为selectAll，并且有函数selectAll()，就会有错。因为有一种用法：可以直接用id引用元素 -->
								<input type="CHECKBOX" id="cbSelectAll" onclick="$('[name=priviledgeIds]').attr('checked',this.checked);"/>
								<label for="cbSelectAll">全选</label>
							</td>
						</tr>
					</thead>
                   
			   		<!--显示数据列表-->
					<tbody id="TableData">
						<tr class="TableDetail1">
							<!-- 显示权限树 -->
							<td>
							<%--sturct2定义的标签
                                <s:checkboxlist name="priviledgeIds" list="#priviledges" 
                                listKey="id" listValue="name"></s:checkboxlist>
                              --%>
                              <%--直接显示html --%>
                              <!-- 第一级-->
                              <ul id="root">
                              <s:iterator value="#topPriviledges">
                                  <li>
                                  <input type="checkbox" name="priviledgeIds" value="${id}" id="cd_${id}" <s:property value="%{id in priviledgeIds ? 'checked':''}"/>>
                                  <label for="cd_${id}"><span class="folder">${ name }</span></label>
                                    <ul>
                                    <!-- 第二级-->
                                     <s:iterator value="children">
                                        <li>
                                         <input type="checkbox" name="priviledgeIds" value="${id}" id="cd_${id}" <s:property value="%{id in priviledgeIds ? 'checked':''}"/>>
                                         <label for="cd_${id}">><span class="folder">${ name }</span></label>
                                         
                                              <ul>
                                              <!-- 第三级-->
                                             <s:iterator value="children">
                                                 <li>
                                                  <input type="checkbox" name="priviledgeIds" value="${id}" id="cd_${id}" <s:property value="%{id in priviledgeIds ? 'checked':''}"/>>
                                                 <label for="cd_${id}">><span class="folder">${ name }</span></label>
                                                 </li>
                                             </s:iterator>
                                             </ul>
                                         </li>
                                      </s:iterator>
                                     </ul>
                                 </li>
                              </s:iterator>
                              </ul>
                            </td>
						</tr>
					</tbody>
                </table>
            </div>
        </div>
        <script type="text/javascript">
            $("#root").treeview();
        </script>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description">
	说明：<br />
	1，选中一个权限时：<br />
	&nbsp;&nbsp;&nbsp;&nbsp; a，应该选中 他的所有直系上级。<br />
	&nbsp;&nbsp;&nbsp;&nbsp; b，应该选中他的所有直系下级。<br />
	2，取消选择一个权限时：<br />
	&nbsp;&nbsp;&nbsp;&nbsp; a，应该取消选择 他的所有直系下级。<br />
	&nbsp;&nbsp;&nbsp;&nbsp; b，如果同级的权限都是未选择状态，就应该取消选中他的直接上级，并递归向上做这个操作。<br />

	3，全选/取消全选。<br />
	4，默认选中当前岗位已有的权限。<br />
</div>

</body>
</html>
