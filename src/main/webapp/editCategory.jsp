<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- 通过判断category是否为null，可以知道是创建分类还是编辑分类 -->
<s:if test="category!=null">
	<s:text id="title" name="编辑分类" />
</s:if>
<s:else>
	<s:text id="title" name="创建分类" />
</s:else>
<html>
	<head>
		<title><s:property value="#title" /></title>
	</head>

	<body>
		<h1><s:property value="#title" /></h1>

		<s:form action="save" method="post">
			<!-- 通过隐藏字段来保存分类的id，如果是创建分类，该字段的值为空 -->
			<s:hidden name="category.id" value="%{category.id}" />
			<s:textfield label="分类名称" name="category.name" />
			<s:submit value="保存" />
		</s:form>
	</body>
</html>
