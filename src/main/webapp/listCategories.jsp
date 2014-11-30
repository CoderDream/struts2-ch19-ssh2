<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>所有分类</title>
	</head>

	<body>
		<h1>所有分类</h1>
		<table>
			<tr>
				<th>分类ID</th>
				<th>分类名</th>
				<th>&nbsp;</th>
			</tr>
			<s:iterator value="categories">
				<tr>
					<td>
						<a href="<s:url action="edit-%{id}"/>"><s:property value="id" /></a>
					</td>
					<td>
						<s:property value="name" />
					</td>
					<td>
						<a href="<s:url action="delete-%{id}"/>">删除</a>
					</td>
				</tr>
			</s:iterator>
		</table>
		<p>
		<a href="<s:url action="edit-" includeParams="none"/>">创建新的分类</a>
	</body>
</html>