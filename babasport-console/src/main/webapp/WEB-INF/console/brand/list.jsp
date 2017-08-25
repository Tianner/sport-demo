<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>babasport-list</title>
</head>
<script>
	function checkBox(id,status) {
	    var value = "input[name='"+id+"']";
		$(value).attr('checked',status);
    }
</script>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 品牌管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='add.do'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="list.do" method="get" style="padding-top:5px;">
品牌名称: <input type="text" name="name" value="${name}"/>
	<select name="isDisplay">
		<option value="1">是</option>
		<option value="0">否</option>
		<option value="">全部</option>
	</select>
	<script type="text/javascript" >
		$("select[name='isDisplay']").val("${isDisplay}");
	</script>
	<input type="submit" class="query" value="查询"/>
</form>
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="checkBox('ids',this.checked)"/></th>
			<th>品牌ID</th>
			<th>品牌名称</th>
			<th>品牌图片</th>
			<th>品牌描述</th>
			<th>排序</th>
			<th>是否可用</th>
			<th>操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
	

		<c:forEach items="${brandPage.result}" var="brand">
			<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">
				<td><input type="checkbox" value="${brand.id}" name="ids"/></td>
				<td align="center">${brand.id}</td>
				<td align="center">${brand.name}</td>
				<td align="center"><img width="40" height="40" src="${brand.imgUrl}"/></td>
				<td align="center">${brand.description}</td>
				<td align="center">${brand.sort}</td>
				<td align="center">
					<c:if test="${brand.isDisplay == 1}">可用</c:if>
					<c:if test="${brand.isDisplay == 0}">不可用</c:if>
				</td>
				<td align="center">
					<a class="pn-opt" href="edit.do?id=${brand.id}&isDisplay=${isDisplay}&name=${name}&pageNum=${brandPage.pageNum}&pageSize=${brandPage.pageSize}"+>修改</a>
					| <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="deleteById.do?id=${brand.id}&isDisplay=${isDisplay}&name=${name}&pageNum=${brandPage.pageNum}&pageSize=${brandPage.pageSize}">删除</a>
				</td>
			</tr>
		</c:forEach>
	
	</tbody>
</table>
<div class="page pb15">
	<span class="r inb_a page_b">
	
		<font size="2">
			<c:if test="${brandPage.pageNum==1}">
				首页
				上一页
			</c:if>
			<c:if test="${brandPage.pageNum > 1}">
				<a href="list.do?isDisplay=${isDisplay}&name=${name}&pageNum=1&pageSize=${brandPage.pageSize}">首页</a>
				<a href="list.do?isDisplay=${isDisplay}&name=${name}&pageNum=${brandPage.pageNum-1}&pageSize=${brandPage.pageSize}">上一页</a>
			</c:if>
		</font>

		<c:if test="${brandPage.pageNum>=6 and brandPage.pages-brandPage.pageNum>=4}" var="setPage" >
			<c:forEach begin="${brandPage.pageNum-5}" end="${brandPage.pageNum-1}" var="aa" >
				<a href="list.do?isDisplay=${isDisplay}&name=${name}&pageNum=${aa}
				&pageSize=${brandPage.pageSize}">${aa}</a>
			</c:forEach>
		</c:if>
		<c:if test="${brandPage.pageNum>=6 and brandPage.pages-brandPage.pageNum<4}">
			<c:forEach begin="${brandPage.pages-9}" end="${brandPage.pageNum-1}" var="aa">
				<a href="list.do?isDisplay=${isDisplay}&name=${name}&pageNum=${aa}
				&pageSize=${brandPage.pageSize}">${aa}</a>
			</c:forEach>
		</c:if>
		<c:if test="${brandPage.pageNum<6 and brandPage.pageNum>0}">
			<c:forEach begin="1" end="${brandPage.pageNum-1}" var="aa">
				<a href="list.do?isDisplay=${isDisplay}&name=${name}&pageNum=${aa}
				&pageSize=${brandPage.pageSize}">${aa}</a>
			</c:forEach>
		</c:if>
			<strong><a href="javascript:void(0);"><font color="blue">${brandPage.pageNum}</font></a></strong>
		<c:if test="${brandPage.pages-brandPage.pageNum<=4}">
			<c:forEach begin="${brandPage.pageNum+1}" end="${brandPage.pages}" var="aa">
				<a href="list.do?isDisplay=${isDisplay}&name=${name}&pageNum=${aa}
				&pageSize=${brandPage.pageSize}">${aa}</a>
			</c:forEach>
		</c:if>
		<c:if test="${brandPage.pages-brandPage.pageNum>4}">
			<c:forEach begin="${brandPage.pageNum+1}" end="${pageNum+4}" var="aa">
				<a href="list.do?isDisplay=${isDisplay}&name=${name}&pageNum=${aa}
				&pageSize=${brandPage.pageSize}">${aa}</a>
			</c:forEach>
		</c:if>

		<font size="2">
			<c:if test="${brandPage.pages >0 and brandPage.pageNum==brandPage.pages}">
				下一页
				尾页
			</c:if>

			<c:if test="${brandPage.pageNum < brandPage.pages}">
				<a href="list.do?isDisplay=${isDisplay}&name=${name}&pageNum=${brandPage.pageNum-1}&pageSize=${brandPage.pageSize}">下一页</a>
				<a href="list.do?isDisplay=${isDisplay}&name=${name}&pageNum=${brandPage.pages}&pageSize=${brandPage.pageSize}">尾页</a>
			</c:if>
		</font>

		共<var>${brandPage.pages}</var>页 到第<input type="text" size="3" id="pageNum"/>页
		<input type="button" onclick="goPage()"
		value="确定" class="hand btn60x20" id="skip"/>
		<script type="text/javascript" >
			function goPage(){
				location.href ="list.do?isDisplay=${isDisplay}&name=${name}&pageNum="
				+$('#pageNum').val()+"&pageSize=${brandPage.pageSize}";
			}
		</script>
	</span>
</div>
<div style="margin-top:15px;">
	<form id = "deleteForm" action="deleteBatch.do">
	<input class="del-button" type="button" value="删除" onclick="optDelete()"/>
	</form>
</div>
<script type="text/javascript">
	function optDelete() {
	    var v = "";
	    var arr = "[";
		var ids = $("input[name = 'ids']:checked");
		if(ids.length == 0){
		    alert("请选择至少一行");
		    return ;
		}
		for(var i = 0;ids.length>i;i++){
			v = v+"id="+ids[i].value+"&";
			arr = arr+" "+ids[i].value;
		}
		if(confirm("确定删除"+arr+"]吗?")){
			location.href="deleteBatch.do?"+v+"isDisplay=${isDisplay}&name=${name}&pageNum=${brandPage.pageNum}&pageSize=${brandPage.pageSize}";
		}
    }
</script>
</div>
</body>
</html>