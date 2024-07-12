<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>    
<% Map count=(Map)request.getAttribute("count"); %>
<!DOCTYPE html>
<html>

 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['day', 'member', 'artist','membership'],
          ['5/1',  	2,      3,		1],
          ['5/8',  	6,     7,		3],
          ['5/15',  8,     7,		3],
          ['5/22',  4,     8,		3],
          ['5/31',  <%=count.get("mc")%>,     <%=count.get("ac")%>,		<%=count.get("msc")%>]
        ]);

        var options = {
          title: 'Member Quantity',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>

<style>
.myprofile-container{
	display: flex;
}
.quantity_space{
	display: flex;
	justify-content: space-between;
	font-size: 50px;
	margin : 5px;
}
.managerhomecount{
	position: relative;
    margin: 8px 16px 16px;
    min-width: 0px;
    border-radius: 8px 8px 8px 8px;
    padding: 8px 16px 8px 16px;
    outline: 1px solid #e0e0e0;
    outline-offset: -1px;
}
</style>
<body>
<div class="myprofile-container">
<%@ include file="/WEB-INF/views/manager/manageraside.jsp" %>
	<main class="main">
		<section class="myInfo">
			<div class="flex_col">
				<div class="quantity_space">
					<div class="managerhomecount">
						<span style="font-size: 30px;">일반 회원수</span>
						<h4><%=count.get("mc")%></h4>
					</div>
					<div class="managerhomecount">
						<span style="font-size: 30px;">아티스트 회원수</span>
						<h4><%=count.get("ac")%></h4>
					</div>
					<div class="managerhomecount">
						<span style="font-size: 30px;">멤버십 회원수</span>
						<h4><%=count.get("msc")%></h4>
					</div>
				</div>
 				<div id="curve_chart" style="width: 100%; height: 900px"></div>
 			</div>
 		</section>
 	</main>
</div>
</body>
</html>