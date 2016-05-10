<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/views/template/header.jsp" %>

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>All Products</h1>

            <p class="lead">checkout all the awesome products available now!</p>
        </div>

        <!-- hover row, will be highlighted !-->

        <table class="table table-striped table-hover">
            <thead>

            <!-- change background color of that row !-->

            <tr class="bg-success">
                <th>Photo Thumb</th>
                <th>Product Name</th>
                <th>Category</th>
                <th>Condition</th>
                <th>Price</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${products}" var="product">
                <tr>

                    <!-- jspl tag loops through each instance of the list and displays one product at a time !-->

                    <td><img src="<c:url value="/resources/images/${product.productId}.png" />" alt="image"
                             style="width:100%"/></td>
                    <td>${product.productName}</td>
                    <td>${product.productCategory}</td>
                    <td>${product.productCondition}</td>
                    <td>${product.productPrice} GBP</td>

                    <!-- dynamic variable that responds to products product id !-->

                    <td><a href="<spring:url value="/productList/viewProduct/${product.productId}" />"><span
                            class="glyphicon glyphicon-info-sign"></span></a></td>
                </tr>
            </c:forEach>
        </table>

<%@include file="/WEB-INF/views/template/footer.jsp" %>