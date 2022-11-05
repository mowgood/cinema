<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예매</title>
<style>
.content{
	border:2px solid #cce5e9;	
	font-size:1.0em;
	width: 900px;   	
}
label{
	font-family: 'Noto Sans KR', sans-serif;
	font-size:20px;
	line-height:1;
	color: #222;
	font-weight:normal;}
table{
	margin-left:150px;
	width:600px;
	height:500px;}
</style>
</head>
<body>
	<div class="wrapper">		
		<div class="content" id="content">
			<form name="reserveForm" method="post" action="/CinemaPlus/movie/reservation.do">		
				<c:if test="${!empty scheduleList }">
					<label>영화 제목</label>
					<select name="RESTITLE" id="RESTITLE">
						<c:forEach var="schedule" items="${scheduleList }">
							<option value=${schedule.scMVTITLE}>${schedule.scMVTITLE}</option>
						</c:forEach>
					</select>
					<label>영화 날짜</label>
					<select name="RESDATE" id="RESDATE">
						<c:forEach var="schedule" items="${scheduleList }">
							<option value=${schedule.scMVDATE}>${schedule.scMVDATE}</option>
						</c:forEach>
					</select>
					<label>영화 시간</label>
					<select name="RESTIME" id="RESTIME">
						<c:forEach var="schedule" items="${scheduleList }">
							<option value=${schedule.scMVTIME}>${schedule.scMVTIME}</option>
						</c:forEach>
					</select>
				</c:if>
	      							                      									              			
	    		<label>인원수 선택 : </label>
	    		<select name="RESAMOUNT" id="RESAMOUNT">
	       		 	<option value="1">1명</option>
	        		<option value="2">2명</option>
	       		 	<option value="3">3명</option>
	        		<option value="4">4명</option>
	        		<option value="5">5명</option>
	   		 	</select>  	
	   		 		 	
	    		<table border="4" bordercolor="#EAEAEA"aglin="center">      		
					<tr>
	            		<td><label><input type="checkbox" value="1" name="RESSEAT"/>1</label></td>
	            		<td><label><input type="checkbox" value="2" name="RESSEAT" disabled="disabled"/>2</label></td>
	            		<td><label><input type="checkbox" value="3" name="RESSEAT"/>3</label></td>
	            		<td><label><input type="checkbox" value="4" name="RESSEAT" disabled="disabled"/>4</label></td>
	            		<td><label><input type="checkbox" value="5" name="RESSEAT"/>5</label></td>
	            		<td><label><input type="checkbox" value="6" name="RESSEAT" disabled="disabled"/>6</label></td>
	        		</tr>
	        		<tr>
	            		<td><label><input type="checkbox" value="7" name="RESSEAT"/>7</label></td>
	            		<td><label><input type="checkbox" value="8" name="RESSEAT" disabled="disabled"/>8</label></td>
	            		<td><label><input type="checkbox" value="9" name="RESSEAT"/>9</label></td>
	            		<td><label><input type="checkbox" value="10" name="RESSEAT" disabled="disabled"/>10</label></td>
	            		<td><label><input type="checkbox" value="11" name="RESSEAT"/>11</label></td>
	            		<td><label><input type="checkbox" value="12" name="RESSEAT" disabled="disabled"/>12</label></td>
	        		</tr>
					<tr>
	            		<td><label><input type="checkbox" value="13" name="RESSEAT"/>13</label></td>
	            		<td><label><input type="checkbox" value="14" name="RESSEAT" disabled="disabled"/>14</label></td>
	            		<td><label><input type="checkbox" value="15" name="RESSEAT"/>15</label></td>
	            		<td><label><input type="checkbox" value="16" name="RESSEAT" disabled="disabled"/>16</label></td>
	            		<td><label><input type="checkbox" value="17" name="RESSEAT"/>17</label></td>
	            		<td><label><input type="checkbox" value="18" name="RESSEAT" disabled="disabled"/>18</label></td>
	        		</tr>        		
	        		<tr>
	            		<td><label><input type="checkbox" value="19" name="RESSEAT"/>19</label></td>
	            		<td><label><input type="checkbox" value="20" name="RESSEAT" disabled="disabled"/>20</label></td>
	            		<td><label><input type="checkbox" value="21" name="RESSEAT"/>21</label></td>
	            		<td><label><input type="checkbox" value="22" name="RESSEAT" disabled="disabled"/>22</label></td>
	            		<td><label><input type="checkbox" value="23" name="RESSEAT"/>23</label></td>
	            		<td><label><input type="checkbox" value="24" name="RESSEAT" disabled="disabled"/>24</label></td>
	        		</tr>
	        		<tr>
	            		<td><label><input type="checkbox" value="25" name="RESSEAT"/>25</label></td>
	            		<td><label><input type="checkbox" value="26" name="RESSEAT" disabled="disabled"/>26</label></td>
	            		<td><label><input type="checkbox" value="27" name="RESSEAT"/>27</label></td>
	            		<td><label><input type="checkbox" value="28" name="RESSEAT" disabled="disabled"/>28</label></td>
	            		<td><label><input type="checkbox" value="29" name="RESSEAT"/>29</label></td>
	            		<td><label><input type="checkbox" value="30" name="RESSEAT" disabled="disabled"/>30</label></td>
	        		</tr>
	        		<tr>
	            		<td><label><input type="checkbox" value="31" name="RESSEAT"/>31</label></td>
	            		<td><label><input type="checkbox" value="32" name="RESSEAT" disabled="disabled"/>32</label></td>
	            		<td><label><input type="checkbox" value="33" name="RESSEAT"/>33</label></td>
	            		<td><label><input type="checkbox" value="34" name="RESSEAT" disabled="disabled"/>34</label></td>
	            		<td><label><input type="checkbox" value="35" name="RESSEAT"/>35</label></td>
	            		<td><label><input type="checkbox" value="36" name="RESSEAT" disabled="disabled"/>36</label></td>
	        		</tr>
	        		<tr>
	            		<td><label><input type="checkbox" value="37" name="RESSEAT"/>37</label></td>
	            		<td><label><input type="checkbox" value="38" name="RESSEAT" disabled="disabled"/>38</label></td>
	            		<td><label><input type="checkbox" value="39" name="RESSEAT"/>39</label></td>
	            		<td><label><input type="checkbox" value="40" name="RESSEAT" disabled="disabled"/>40</label></td>
	            		<td><label><input type="checkbox" value="41" name="RESSEAT"/>41</label></td>
	            		<td><label><input type="checkbox" value="42" name="RESSEAT" disabled="disabled"/>42</label></td>
	        		</tr>
	        		<tr>
	            		<td><label><input type="checkbox" value="43" name="RESSEAT"/>43</label></td>
	            		<td><label><input type="checkbox" value="44" name="RESSEAT" disabled="disabled"/>44</label></td>
	            		<td><label><input type="checkbox" value="45" name="RESSEAT"/>45</label></td>
	            		<td><label><input type="checkbox" value="46" name="RESSEAT" disabled="disabled"/>46</label></td>
	            		<td><label><input type="checkbox" value="47" name="RESSEAT"/>47</label></td>
	            		<td><label><input type="checkbox" value="48" name="RESSEAT" disabled="disabled"/>48</label></td>
	        		</tr>
	    		<input type="submit"  value="예매하기">
			</form>
		</div>
	</div>
</body>
</html>