<html>
<head>
    <title>Administration</title>
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
<caption>네이버뉴스</caption>
<tr>
<th>제목</th>
<th>기사</th>
<th>날짜</th>
</tr>
<#list 0 ..< news.length() as i>
<tr>
<td>${news.get(i).title}</td>
<td>${news.get(i).contents}</td>
<td>${news.get(i).date}</td>
</tr>
</#list>
</table>


</body>
</html>