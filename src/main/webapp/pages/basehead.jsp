<%@ page import="cn.yyn.yiju.bean.UserInfo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>页头</title>
    <!--    下面是几个导入的包-->
    <link type="text/css" href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/js.js"></script>
</head>
<body>
<!--头部最上方的框-->
<div class="header">
    <div class="width1190">
        <div class="fl" style="font-size: 14px">您好，欢迎来到<a
                href="${pageContext.request.contextPath}/index.jsp">易居住房信息平台！</a></div>
        <div class="fr">
            <a href="${pageContext.request.contextPath}/pages/login.jsp" style="font-size: 14px">
                <strong>登录</strong></a> |
            <a href="${pageContext.request.contextPath}/pages/signup.jsp" style="font-size: 14px">
                <strong>注册</strong></a>
            <a href="#" style="font-size: 14px" target="_blank"><strong>你好 ！${user.truename}</strong></a>
            <a style="font-size: 14px">欢迎使用</a>　|
            <a href="${pageContext.request.contextPath}/user/geren.do"
               style="font-size: 14px"><strong>个人中心</strong></a> |
            <a href="${pageContext.request.contextPath}/user/edit.do"
               style="font-size: 14px"><strong>退出</strong></a> |
            <a href="javascript:;" onclick="" style="font-size: 14px">加入收藏</a> |
            <a href="javascript:;" onclick="" style="font-size: 14px">设为首页</a>
        </div>
        <div class="clears"></div>
    </div><!--width1190/-->
</div>
<!--头部最上方的框-->
<!--Logo栏和手机号栏-->
<div class="logo-phone">
    <div class="width1190">
        <table align="center" width="100%">
            <tr>
                <td>
                    <h1 class="logo"><a href="${pageContext.request.contextPath}/house/findFourHouse.do"><img
                            src="${pageContext.request.contextPath}/images/logo.png" width="163" height="59"/></a></h1>
                </td>
                <%if (request.getParameter("jspType") != null) {%>
                <td>
                    <div class="searchbox">
                        <div class="mod_select">
                            <div class="select_box">
                                <span class="select_txt">房屋</span>
                            </div>
                        </div>
                        <%--FIXME 这里是搜索栏，需要实现相应的模糊搜索功能 --%>
                        <form action="#">
                            <input type="text" name="house_title" id="searchPlaceholder" class="import"
                                   placeholder="请输入搜索信息">
                            <input type="submit" value="搜   索" class="btn-search">
                        </form>
                    </div>
                </td>
                <%}%>
                <td align="center">
                    <div class="phones"><strong>000-00000000</strong></div>
                    <div class="clears"></div>
                </td>

            </tr>
        </table>
    </div><!--width1190/-->
</div><!--logo-phone/-->
<!--Logo栏和手机号栏-->

<%
    String url = request.getRequestURI();
    if (!"/yiju_war_exploded/pages/login.jsp".equals(url)) {
%>
<!--导航栏-->
<div class="list-nav">
    <div class="width1190">
        <ul class="nav">
            <li><a href="${pageContext.request.contextPath}/house/findFourHouse.do">首页</a></li>
            <li><a href="${pageContext.request.contextPath}/house/findAllHouseByType.do?houseType=0">新房</a></li>
            <li><a href="${pageContext.request.contextPath}/house/findAllHouseByType.do?houseType=1">二手房</a></li>
            <li><a href="${pageContext.request.contextPath}/house/findAllHouseByType.do?houseType=2">租房</a></li>
            <li class="zhiding"><a href="#">指定购房</a></li>
            <li><a href="${pageContext.request.contextPath}/pages/housePost1.jsp">发布房源</a></li>
            <li><a href="#">公告中心</a></li>
            <li><a href="#">关于我们</a></li>
            <div class="clears"></div>
        </ul><!--nav-->
        <div class="clears"></div>
    </div><!--width1190-->
</div><!--list-nav-->
<!--导航栏End-->
<%}%>
</body>
</html>
