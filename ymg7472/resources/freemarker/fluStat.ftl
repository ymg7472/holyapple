<!DOCTYPE html>
<html>
<head>
<style>
table {
  width:50%;
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
    <th>지역</th> 
    <th>발생건수</th>  
  </tr>
  <#list fluStat as i>
  <tr>
    <td>${i.scc.location}</td>
    <td>${i.case1}</td>
  </tr>
  </#list>


</table>

</body>
</html>