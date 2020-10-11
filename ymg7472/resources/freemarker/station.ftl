<!DOCTYPE html>
<html>
<head>
<META HTTP-EQUIV="refresh" CONTENT="20">
<style>
table {
  width:100%;
}
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 15px;
  text-align: left;
}
#t01 tr:nth-child(even) {
  background-color: #eee;
}
#t01 tr:nth-child(odd) {
 background-color: #fff;
}
#t01 th {
  background-color: black;
  color: white;
}
</style>
</head>
<body>

<table id="t01">
<tr>
<th>버스 도착 정보</th>
</tr>
<#list stationArrivalInfo as i>
<tr>
<td>1번째 버스 : ${i.plateNo1}<br>${i.predictTime1}분후 도착 (${i.locationNo1}번째 전 정류소)<br>차내 혼잡상태: 여유</td>
</tr>
<#if i.locationNo2 != "">
<tr>
<td>2번째 버스 : ${i.plateNo1}<br>${i.predictTime2}분후 도착 (${i.locationNo2}번째 전 정류소)<br>차내 혼잡상태: 여유</td>
</tr>
<#else>
<tr>
<td>2번째 버스 : 대기중</td>
</tr>
</#if>
</#list>

</table>

</body>
</html>