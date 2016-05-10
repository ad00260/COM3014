<%--
  Created by IntelliJ IDEA.
  User: Anisha
  Date: 08/05/2016
  Time: 01:41
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Administrator Page</h1>

            <p class="lead">this is the administrator page</p>
        </div>

        <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>
            Welcome: ${pageContext.request.userPrincipal.name} | <a href="<c:url value="/j_spring_security_logout" />">Logout</a>
        </h2>
        </c:if>
        <h3>
            <a href="<c:url value="/admin/productInventory" />">Product Inventory</a>
        </h3>

        <p>here you can view, check and modify the product inventory!</p>


<%@include file="/WEB-INF/views/template/footer.jsp" %>