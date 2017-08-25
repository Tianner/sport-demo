<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>babasport-list</title>
<script type="text/javascript">
//上架
function isShow(value){
	//请至少选择一个
	var tags = $("input[name='ids']:checked");
	if(tags.length == 0){
		alert("请至少选择一个");
		return;
	}
	var ids = "";
	for(var i = 0;i<tags.length;i++){
	    var id =tags[i].value;
        if($("#isShow"+id).html().trim() == "上架" && value == "0"){
            ids = ids+"ids="+id+"&";
        }
        if($("#isShow"+id).html().trim() == "下架" && value == "1"){
            ids = ids+"ids="+id+"&";
        }
	}
	if(ids == ""){
	    if(value == "0"){
	    	alert("所选商品已处于下架状态,不需操作");
	    	return;
		}
		if(value == "1"){
	    	alert("所选商品已处于上架状态,不需操作");
	    	return;
		}
	}
	//你确定删除吗
	if(value == "1"){
		if(!confirm("你确定上架吗")){
			return;
		}
	}
	if(value == "0"){
        if(!confirm("你确定下架吗")){
            return;
        }
	}
	ids = ids+"isShow="+value;
    $.ajax({
        type: "POST",
        url: "changeIsShow.do",
        data: ids,
        success: function(msg){
            if(msg == "true"){
                for(var i = 0;i<tags.length;i++){
                    var id =tags[i].value;
                    if($("#isShow"+id).html().trim() == "上架" && value == "0"){
                        $("#isShow"+id).html("下架");
                    }
                    if($("#isShow"+id).html().trim() == "下架" && value == "1"){
                        $("#isShow"+id).html("上架");
                    }
                }
                if(value == "0"){
                    alert("下架成功");
                }
                if(value == "1"){
                    alert("上架成功");
                }
			}else{
                if(value == "0"){
                	alert("下架失败");
				}
                if(value == "1"){
                    alert("上架失败");
                }
			}
        }
    });

}
function checkBox(name,status) {
    var value = "input[name='"+name+"']";
    $(value).attr('checked',status);
}
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 商品管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="window.location.href='add.do'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="list.do" method="get" style="padding-top:5px;">
名称: <input type="text" name="name" value="${name}"/>
	<select name="brandId">
		<option value="">请选择品牌</option>
		<c:if test="${brands.size()>0}">
			<c:forEach items="${brands}" var="brand">
				<option value="${brand.key}">${brand.value}</option>
			</c:forEach>
		</c:if>
	</select>
	<script type="text/javascript">
        $("select[name='brandId']").val("${brandId}");
	</script>
	<select name="isShow">
		<option value="">请选择</option>
		<option value="1">上架</option>
		<option value="0">下架</option>
	</select>
	<script type="text/javascript">
		$("select[name='isShow']").val("${isShow}");
	</script>
	<input type="submit" class="query" value="查询"/>
</form>
<form id="jvForm">
<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="checkBox('ids',this.checked)"/></th>
			<th>商品编号</th>
			<th>商品名称</th>
			<th>图片</th>
			<th width="4%">新品</th>
			<th width="4%">热卖</th>
			<th width="4%">推荐</th>
			<th width="4%">上下架</th>
			<th width="12%">操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach var="product" items="${productPage.result}" >
		<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td><input type="checkbox" name="ids" value="${product.id}"/></td>
			<td>${product.id}</td>
			<td align="left">${product.name}</td>
			<td align="center"><img width="50" height="50" src="${product.imgUrl}"/></td>
			<td align="center">
				<c:if test="${product.isNew==1}">是</c:if>
				<c:if test="${product.isNew==0}">否</c:if>
			</td>
			<td align="center">
				<c:if test="${product.isHot==1}">是</c:if>
				<c:if test="${product.isHot==0}">否</c:if>
			</td>
			<td align="center">
				<c:if test="${product.isCommend==1}">是</c:if>
				<c:if test="${product.isCommend==0}">否</c:if>
			</td>
			<td align="center">
				<span id = "isShow${product.id}">
					<c:if test="${product.isShow==1}">上架</c:if>
					<c:if test="${product.isShow==0}">下架</c:if>
				</span>
			</td>
			<td align="center">
				<a href="#" class="pn-opt">查看</a> |
				<a href="#" class="pn-opt">修改</a> |
				<a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="deleteProductById.do?id0=${product.id}&isShow=${isShow}&name=${name}&brandId=${brandId}&pageNum=${productPage.pageNum}&pageSize=${productPage.pageSize}">删除</a>|
				<a href="../sku/list.do?productId=${product.id}" class="pn-opt">库存</a>
			</td>
		</tr>
		</c:forEach>
		</tr>
		<%--<c:forEach var="product" items="${productPage.result}">
		</c:forEach>--%>

	</tbody>
</table>
<div class="page pb15">
	<span class="r inb_a page_b">
		<font size="2">
			<c:if test="${productPage.pageNum==1}">
				首页
				上一页
			</c:if>
			<c:if test="${productPage.pageNum > 1}">
				<a href="list.do?isShow=${isShow}&name=${name}&brandId=${brandId}&pageNum=1&pageSize=${productPage.pageSize}">首页</a>
				<a href="list.do?isShow=${isShow}&name=${name}&brandId=${brandId}&pageNum=${productPage.pageNum-1}&pageSize=${productPage.pageSize}">上一页</a>
			</c:if>
		</font>

		<c:if test="${productPage.pageNum>=6 and productPage.pages-productPage.pageNum>=4}"  >
			<c:forEach begin="${productPage.pageNum-5}" end="${productPage.pageNum-1}" var="aa" >
				<a href="list.do?isShow=${isShow}&name=${name}&brandId=${brandId}&pageNum=${aa}&pageSize=${productPage.pageSize}">${aa}</a>
			</c:forEach>
		</c:if>
		<c:if test="${productPage.pageNum>=6 and productPage.pages-productPage.pageNum<4}">
			<c:forEach begin="${productPage.pages-9}" end="${productPage.pageNum-1}" var="aa">
				<a href="list.do?isShow=${isShow}&name=${name}&brandId=${brandId}&pageNum=${aa}&pageSize=${productPage.pageSize}">${aa}</a>
			</c:forEach>
		</c:if>
		<c:if test="${productPage.pageNum<6 and productPage.pageNum>0}">
			<c:forEach begin="1" end="${productPage.pageNum-1}" var="aa">
				<a href="list.do?isShow=${isShow}&name=${name}&brandId=${brandId}&pageNum=${aa}&pageSize=${productPage.pageSize}">${aa}</a>
			</c:forEach>
		</c:if>
			<strong><a href="javascript:void(0);"><font color="blue">${productPage.pageNum}</font></a></strong>
		<c:if test="${productPage.pages-productPage.pageNum<=4}">
			<c:forEach begin="${productPage.pageNum+1}" end="${productPage.pages}" var="aa">
				<a href="list.do?isShow=${isShow}&name=${name}&brandId=${brandId}&pageNum=${aa}&pageSize=${productPage.pageSize}">${aa}</a>
			</c:forEach>
		</c:if>
		<c:if test="${productPage.pages-productPage.pageNum>4 and productPage.pageNum<=6}">
			<c:forEach begin="${productPage.pageNum+1}" end="${10}" var="aa">
				<a href="list.do?isShow=${isShow}&name=${name}&brandId=${brandId}&pageNum=${aa}&pageSize=${productPage.pageSize}">${aa}</a>
			</c:forEach>
		</c:if>
		<c:if test="${productPage.pages-productPage.pageNum>4 and productPage.pageNum>6}">
			<c:forEach begin="${productPage.pageNum+1}" end="${productPage.pageNum+4}" var="aa">
				<a href="list.do?isShow=${isShow}&name=${name}&brandId=${brandId}&pageNum=${aa}&pageSize=${productPage.pageSize}">${aa}</a>
			</c:forEach>
		</c:if>

		<font size="2">
			<c:if test="${productPage.pages >0 and productPage.pageNum==productPage.pages}">
				下一页
				尾页
			</c:if>

			<c:if test="${productPage.pageNum < productPage.pages}">
				<a href="list.do?isShow=${isShow}&name=${name}&brandId=${brandId}&pageNum=${productPage.pageNum+1}&pageSize=${productPage.pageSize}">下一页</a>
				<a href="list.do?isShow=${isShow}&name=${name}&brandId=${brandId}&pageNum=${productPage.pages}&pageSize=${productPage.pageSize}">尾页</a>
			</c:if>
		</font>

		共<var>${productPage.pages}</var>页 到第<input type="text" size="3" id="pageNum"/>页
		<input type="button" onclick="goPage()"
			   value="确定" class="hand btn60x20" id="skip"/>
		<script type="text/javascript" >
			function goPage(){
                location.href ="list.do?isShow=${isShow}&name=${name}&brandId=${brandId}&pageNum="
                    +$('#pageNum').val()+"&pageSize=${brandPage.pageSize}";
            }
		</script>
	</span>
</div>
<div style="margin-top:15px;">
	<input class="del-button" type="button" value="删除" onclick="optDelete();"/>
	<input class="add" type="button" value="上架" onclick="isShow(1);"/>
	<input class="del-button" type="button" value="下架" onclick="isShow(0);"/>
</div>
</form>
</div>
</body>
</html>