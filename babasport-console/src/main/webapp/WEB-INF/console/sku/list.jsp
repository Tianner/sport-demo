<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>babasport-list</title>
</head>
<script>
	/*function toEidt(row) {

	    var v = $("#marketPrice"+row).val();
	    alert(v);
		var ele = $(row).parent().parent();
		if($(row).html() == "修改"){
			$(row).html("放弃")
			$(ele).find("input:disabled").removeAttr("disabled");
		}else{
		    location.href = "list.do?productId=
		}
    }*/
	function toEidt(id) {
	    if(	$("#marketPrice"+id).attr("disabled")){
	        $("#modify"+id).html("放弃");
			$("#marketPrice"+id).attr("disabled",false);
			$("#price"+id).attr("disabled",false);
			$("#stock"+id).attr("disabled",false);
			$("#upperLimit"+id).attr("disabled",false);
			$("#deliveFee"+id).attr("disabled",false);
			var copyId = "copy"+id;
			var value = $("#marketPrice"+id).val()+",!"
						+$("#price"+id).val()+",!"
						+$("#stock"+id).val()+",!"
						+$("#upperLimit"+id).val()+",!"
						+$("#deliveFee"+id).val();
			$("#deliveFee"+id).after("<input type='hidden' id ="+copyId+" value="+value+">");
		}else{
            $("#modify"+id).html("修改");
            $("#marketPrice"+id).attr("disabled",true);
            $("#price"+id).attr("disabled",true);
            $("#stock"+id).attr("disabled",true);
            $("#upperLimit"+id).attr("disabled",true);
            $("#deliveFee"+id).attr("disabled",true);
			var arr = $("#copy"+id).val().split(",!");
            $("#marketPrice"+id).val(arr[0]);
            $("#price"+id).val(arr[1]);
            $("#stock"+id).val(arr[2]);
            $("#upperLimit"+id).val(arr[3]);
            $("#deliveFee"+id).val(arr[4]);
            $("#copy"+id).remove();
		}
    }
    function doSave(id) {
	    if($("#modify"+id).html() == "修改"){
	        return;
		}
		$.post(
		    "updateSku.do",
			{
				"id":id,
				"marketPrice":$("#marketPrice"+id).val(),
				"price":$("#price"+id).val(),
				"stock":$("#stock"+id).val(),
				"upperLimit":$("#upperLimit"+id).val(),
				"deliveFee":$("#deliveFee"+id).val()
			},
			function(data){
				if(data == "true"){
					var value = $("#marketPrice"+id).val()+",!"
						+$("#price"+id).val()+",!"
						+$("#stock"+id).val()+",!"
						+$("#upperLimit"+id).val()+",!"
						+$("#deliveFee"+id).val();
					$("#copy"+id).val(value);
					toEidt(id);
				}else{
				    alert("修改失败!请检查数据");
				}
			}
		)
    }
</script>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 库存管理 - 列表</div>
	<div class="clear"></div>
</div>
<div class="body-box">
<form method="post" id="tableForm">
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
			<th>商品编号</th>
			<th>商品颜色</th>
			<th>商品尺码</th>
			<th>市场价格</th>
			<th>销售价格</th>
			<th>库       存</th>
			<th>购买限制</th>
			<th>运       费</th>
			<th>是否赠品</th>
			<th>操       作</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
				<c:forEach var="sku" items="${skus}">
				<td><input type="checkbox" id="id${sku.id}" value="${sku.id}"/></td>
				<td>${sku.productId}</td>
				<td align="center">${sku.size.split("-")[1]}</td>
				<td align="center">${sku.size.split("-")[0]}</td>
				<td align="center"><input type="text" id="marketPrice${sku.id}" value="${sku.marketPrice}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="price${sku.id}" value="${sku.price}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="stock${sku.id}" value="${sku.stock}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="upperLimit${sku.id}" value="${sku.upperLimit}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="deliveFee${sku.id}" value="${sku.deliveFee}" disabled="disabled" size="10"/></td>
				<td align="center">不是</td>
				<td align="center">
					<a href="javascript:void(0);" id = "modify${sku.id}" onclick="toEidt(${sku.id})" class="pn-opt">修改</a>
					|
					<a href="javascript:void(0);" id = "save${sku.id}" onclick="doSave(${sku.id})" class="pn-opt" >保存</a>
				</td>
			</tr>
			</c:forEach>
	</tbody>
</table>
</form>
</div>
</body>
</html>