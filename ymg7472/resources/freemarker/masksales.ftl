<html>
<head>
 <style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 15px;
  text-align: middle;
}
table#t01 tr:nth-child(even) {
  background-color: #eee;
}
table#t01 tr:nth-child(odd) {
 background-color: #fff;
}
table#t01 th {
  background-color: black;
  color: white;
}
</style>
</head>
<body>



<table id="t01">
<tr>
<th>code</th>
<th>remain_stat</th>
<th>created_at</th>
<th>stock_at</th>
</tr>
<#list sales as i>
<tr>
<td>${i.code}</td>
<td>${i.remain_stat}</td>
<td>${i.created_at}</td>
<td>${i.stock_at}</td>
</tr>
</#list>
</table>


</body>
</html>