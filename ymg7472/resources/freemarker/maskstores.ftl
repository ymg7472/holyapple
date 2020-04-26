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
<th>name</th>
<th>addr</th>
<th>type</th>
<th>lat</th>
<th>lng</th>
</tr>
<#list stores as i>
<tr>
<td>${i.code}</td>
<td>${i.name}</td>
<td>${i.addr}</td>
<td>${i.type}</td>
<td>${i.lat}</td>
<td>${i.lng}</td>
</tr>
</#list>
</table>


</body>
</html>