<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="../../favicon.ico" />

<link rel="stylesheet" href="<c:url value="resource/css/bubbles.css" />">
<link rel="stylesheet" href="<c:url value="resource/css/bootstrap.min.css" />">
<script src="<c:url value="resource/js/jquery-3.2.1.min.js" />"></script>
<script src="<c:url value="resource/js/bootstrap.min.js" />"></script>


<script type="text/javascript">
	$(document).ready(function() {
		
		ajax_process("");
		
		$('#txt_isay').keypress(function(e) {
			if(e.which === 13) {
				conversation();
			}
		});
		
		$('#btn_isay').click(function() {
			conversation();
		});
	});
	
	function conversation() {
		var _isay = $('#txt_isay').val();
		
		var isay = '<p class="triangle-border right">'  + _isay + '</p>';
		
		$('#said').append(isay);
		$('#txt_isay').val('');
		
		ajax_process(_isay);
	}
	
	function ajax_process(_isay) {
		$.ajax({
			type : 'POST',
			url : 'watsonsay',
			data : 'isay=' + _isay,
			sucess : function(data) {
				consol.log(data);
				
				var watsonsay = 
					'<p class="triangle-border left">'  + data.output.text + '</p>';
					
				$('#said').append(watsonsay);
				
				$('html, body').animate({scrollTop: $(document).height});
			}
		});
	}
</script>

</head>

<body>

	<div id="said">
		
	</div>
	
	<input type="text" id="txt_isay" />
	<button type="button" id="btn_isay" autofocus="autofocus"> Submit </button>
</body>
</html>