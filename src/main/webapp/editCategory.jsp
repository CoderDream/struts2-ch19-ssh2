<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- ͨ���ж�category�Ƿ�Ϊnull������֪���Ǵ������໹�Ǳ༭���� -->
<s:if test="category!=null">
	<s:text id="title" name="�༭����" />
</s:if>
<s:else>
	<s:text id="title" name="��������" />
</s:else>
<html>
	<head>
		<title><s:property value="#title" /></title>
	</head>

	<body>
		<h1><s:property value="#title" /></h1>

		<s:form action="save" method="post">
			<!-- ͨ�������ֶ�����������id������Ǵ������࣬���ֶε�ֵΪ�� -->
			<s:hidden name="category.id" value="%{category.id}" />
			<s:textfield label="��������" name="category.name" />
			<s:submit value="����" />
		</s:form>
	</body>
</html>
