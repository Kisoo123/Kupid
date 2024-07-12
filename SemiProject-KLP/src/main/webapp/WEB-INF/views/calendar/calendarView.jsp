<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List,java.text.SimpleDateFormat,com.kupid.feed.model.dto.Feed,com.kupid.feed.model.service.FeedService,com.kupid.member.model.dto.MemberDto"  %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
	<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.13/index.global.min.js'></script>
</head>
<script>

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        headerToolbar: {
            left: 'prev,next today',
            center: 'title'
        },
        initialView:'dayGridMonth',
        navLinks: true, // can click day/week names to navigate views
        selectable: true,
        selectMirror: true,
        /* select: function(arg) {
            console.log(arg);

            var title = prompt('입력할 일정:');
            if (title) {
                calendar.addEvent({
                    title: title,
                    start: arg.start,
                    end: arg.end,
                    allDay: arg.allDay,
                    backgroundColor: "yellow",
                    textColor: "blue"
                });
            }
            calendar.unselect();
        } ,*/
        eventClick: function(arg) {
			window.open("<%=request.getContextPath()%>/calendar/calendarbirthday.do?groupNo=<%=request.getParameter("groupNo")%>","_blank","width=500,height=600");
        },
        editable: true,
        dayMaxEvents: true, // allow "more" link when too many events

        events: function(fetchInfo, successCallback, failureCallback) {
            //================ ajax 데이터 불러올 부분 =====================//
	        $.ajax({
	        url: '<%=request.getContextPath()%>/calendar/calendarbirth.do', // 이벤트 데이터를 불러올 서버의 URL
	        method: 'POST',
	        data:{"groupNo":<%=request.getParameter("groupNo")%>},
	        dataType: 'json',
	        success: function(data) {

	        	let events=[];
	        	data.forEach(function(eventData,index) {
	        		console.log(eventData.scStart);
	                var event = {
	                    id: eventData.scId,
	                    title: eventData.scTitle,
	                    start: eventData.scStart,
	                    end: eventData.scEnd
	                };
	            	events.push(event); // 이벤트를 events 배열에 추가
	            });
	            successCallback(events);
	        },
	        error: function() {
	            failureCallback();
	        }
    });
        }  
    });
    calendar.render();
});


  

</script>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<body>
	<a href="<%=request.getContextPath()%>/feed/feedView.do?groupno=<%=request.getParameter("groupNo")%>">피드</a>

	    <div id='calendar'></div>
	
</body>
</html>