<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>babasport-edit</title>
</head>
<script>
	function uploadPic(){
	    $("#jvForm").ajaxSubmit({
			url:"/uploadFile.do",
			type:"post",
			dataType:"json",
			success:function (data) {
				$("#imgUrl").val(data.imgUrl);
				$("#allUrl").attr('src',data.ipRoot+data.imgUrl);
            }
		});
	    
	}
</script>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 品牌管理 - 添加</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='v_list.shtml';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form id="jvForm" action="doEdit.do" method="post" enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						品牌名称:</td><td width="80%" class="pn-fcontent">
						<input type="hidden" name="id" value="${brand.id}"/>
						<input type="text" class="required" name="name" maxlength="100" value="${brand.name}"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						上传商品图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">
						注:该尺寸图片必须为90x150。
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
						<img width="100" height="100" id="allUrl" src = "${brand.imgUrl}"/>
						<input type="hidden" id="imgUrl" name = "imgUrl" value="${brand.imgUrl}"/>
						<input type="file" name="file" onchange="uploadPic()"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						品牌描述:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="description" maxlength="80"  size="60" value="${brand.description}"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						排序:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="sort" value="${brand.sort}" maxlength="80"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						是否可用:</td><td width="80%" class="pn-fcontent">
						<input type="radio" name="isDisplay" value="1"/>可用
						<input type="radio" name="isDisplay" value="0"/>不可用
					<script type="text/javascript">
						$("input[value=${brand.isDisplay}]").attr("checked","checked");
					</script>
					</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="hidden" name="isDisplay0" value="${pageBrand.isDisplay}"/>
						<input type="hidden" name="name0" value="${pageBrand.name}"/>
						<input type="hidden" name="pageSize" value="${pageSize}"/>
						<input type="hidden" name="pageNum" value="${pageNum}"/>
						<input type="submit" class="submit" value="提交"/> &nbsp; <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>