<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>���з���</title>
	</head>

	<body>
		<h1>���з���</h1>
		<table>
			<tr>
				<th>����ID</th>
				<th>������</th>
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
						<a href="<s:url action="delete-%{id}"/>">ɾ��</a>
					</td>
				</tr>
			</s:iterator>
		</table>
		<p>
		<a href="<s:url action="edit-" includeParams="none"/>">�����µķ���</a>
	</body>
</html>