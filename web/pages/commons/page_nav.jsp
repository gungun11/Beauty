<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/14
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--分页条的开始--%>
<div id="page_nav">
    <%--
        只要不是第一页,才显示首页,上一页
    --%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${ requestScope.page.url }&pageNo=1">首页</a>
        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%-- 页码输出的开始 --%>
    <c:choose>
        <%--第一种情况 : 总页码小于等于5的情况,页码范围是 1 到 总页码--%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${ i == requestScope.page.pageNo }">
                    【${i}】
                </c:if>
                <c:if test="${ i != requestScope.page.pageNo }">
                    <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>
        </c:when>
        <%--
        第二种情况:总页码大于5的情况
        --%>
        <c:otherwise>
            <c:choose>
                <%--第一种小情况: 当前页码为前面3个: 1,2,3,页码的范围是 1 到 5--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:forEach begin="1" end="5" var="i">
                        <c:if test="${ i == requestScope.page.pageNo }">
                            【${i}】
                        </c:if>
                        <c:if test="${ i != requestScope.page.pageNo }">
                            <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <%--第二种小情况: 当前页码是最后3个,8,9,10. 页码范围是 总页码-4 到 总页码--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal -3}">
                    <c:forEach begin="${requestScope.page.pageTotal - 4}" end="${requestScope.page.pageTotal}" var="i">
                        <c:if test="${ i == requestScope.page.pageNo }">
                            【${i}】
                        </c:if>
                        <c:if test="${ i != requestScope.page.pageNo }">
                            <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <%-- 第三种小情况: 就是中间的页码, 页码范围是 当前页码-2 到 当前页码+2 --%>
                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNo - 2}" end="${requestScope.page.pageNo + 2}" var="i">
                        <c:if test="${ i == requestScope.page.pageNo }">
                            【${i}】
                        </c:if>
                        <c:if test="${ i != requestScope.page.pageNo }">
                            <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
    <%-- 页码输出的结束 --%>


    <%--
        不是最后一页,才显示,下一页,末页
    --%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageCount}条记录
    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input id="goToPageNo" type="button" value="确定">

    <script type="text/javascript">
        $(function () {
            // 跳转指定页码的单击事件
            $("#goToPageNo").click(function () {
                // 1 获取输入框里的页码
                var pageNo = $("#pn_input").val();

                <%--var pageTotal = ${requestScope.page.pageTotal};--%>

                // if (pageNo < 1 || pageNo > pageTotal) {
                // 	alert("非法输入");
                // 	return;
                // }

                //2 浏览器跳转
                /**
                 * location是JavaScript语言提供的一个对象,它表示浏览器地址栏对象<br/>
                 * location对象有一个属性叫href.它表示地址栏中有地址<br/>
                 * href属性可读,可写
                 */
                location.href = "${basePath}${ requestScope.page.url }&pageNo=" + pageNo;
                // alert( location.href );
            });
        });
    </script>

</div>
<%-- 分页条的结束 --%>