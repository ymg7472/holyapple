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
<th>remain_stat</th>
<th>created_at</th>
<th>stock_at</th>
</tr>
<#list Info as i>
<tr>
<#if i.code?has_content>
<td>${i.code}</td>
</#if>
<#if i.name?has_content>
<td>${i.name}</td>
</#if>
<#if i.addr?has_content>
<td>${i.addr}</td>
</#if>
<#if i.type?has_content>
<td>${i.type}</td>
</#if>
<#if i.lat?has_content>
<td>${i.lat}</td>
</#if>
<#if i.lng?has_content>
<td>${i.lng}</td>
</#if>
<#if i.remain_stat?has_content>
<td>${i.remain_stat}</td
</#if>
<#if i.created_at?has_content>>
<td>${i.created_at}</td>
</#if>
<#if i.stock_at?has_content>
<td>${i.stock_at}</td>
</#if>
</tr>
</#list>
</table>


</body>
</html>