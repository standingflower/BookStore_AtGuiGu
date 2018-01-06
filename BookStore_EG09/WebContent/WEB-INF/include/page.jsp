<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="page_nav">
			<!-- 
					第一种情况，总页数小于等于5
						begin=1  end=总页数
					第二种情况，当前页 小于等于 3
						begin=1  end=5
					第三种情况，当前页 大于3
						begin=当前页-2   end=当前页+2
						如果end > 总页数
			 -->
			 <c:choose>
			 	<c:when test="${page.totalPage <= 5}">
			 		<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="${page.totalPage }"></c:set>
			 	</c:when>
			 	<c:when test="${page.pageNumber <= 3 }">
			 		<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="5"></c:set>
			 	</c:when>
			 	<c:otherwise>
			 		<c:set var="begin" value="${page.pageNumber-2 }"></c:set>
			 		<c:set var="end" value="${page.pageNumber+2 }"></c:set>
			 		<c:if test="${end > page.totalPage }">
						<c:set var="end" value="${page.totalPage}"></c:set>
						<c:set var="begin" value="${end-4 }"></c:set>
			 		</c:if>
			 	</c:otherwise>
			 </c:choose>
			<a href="${page.path }&pageNumber=1">首页</a>
			<a href="${page.path }&pageNumber=${page.pageNumber-1 }">上一页</a>
			
			<!-- 遍历页码 -->
			<c:forEach begin="${begin }" end="${end }" var="index">
				<c:choose>
					<c:when test="${page.pageNumber == index }">
						<span style="color: red">[${index}]</span>
					</c:when>
					<c:otherwise>
						<a href="${page.path }&pageNumber=${index }">${index }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<a href="${page.path }&pageNumber=${page.pageNumber+1 }">下一页</a>
			<a href="${page.path }&pageNumber=${page.totalPage }">末页</a>
			共${page.totalPage }页，${page.totalRecord }条记录 到第<input value="${page.pageNumber }" name="pn" id="pn_input"/>页
			<input id="pn_btn" type="button" value="确定">
			<script type="text/javascript">
				//为按钮绑定一个单击响应函数
				$("#pn_btn").click(function(){
					//获取到要跳转的页码
					var pageNumber = $("#pn_input").val();
					//跳转到指定页面
					//${pageContext.request.contextPath}/manager/BookManagerServlet?method=findBook&pageNumber=
						
					//通过修改window.location属性跳转到另一个页面
					window.location = "${page.path }&pageNumber="+pageNumber;
					
				});
			
			</script>
		</div>