<!DOCTYPE html>
<html>
<head>
<style>
table {
  width:75%;
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
    <th>${num}번 노선 목록 검색 결과</th> 
  </tr>
  <#list busInfo as i>
  <tr>
    <td>관할지역: ${i.districtCd}&nbsp;운행지역: ${i.regionName}<br>노선 아이디: ${i.routeId}&nbsp;노선 번호: ${i.routeName}&nbsp;노선 종류: ${i.routeTypeName}&nbsp;<a href="http://localhost:4567/route/${i.routeId}"target="_self">노선 정보 확인하기</a></td>
  </tr>
  </#list>


</table>

</body>
</html>