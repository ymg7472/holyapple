<!DOCTYPE html>
<html>
   <head>
      <style>
         table, th, td {
         border-collapse: collapse;
         }
         th, td {
         padding: 5px;
         text-align: left;
         font-size: 25px;
         }
      </style>
   </head>
   <body>
      <table>
         <#list StreamList as i>
         <tr>
            <td>${i?counter}. <b>${i.user_name}</b>: ${i.title}&nbsp;<button onclick="window.location.href='http://localhost:4567/streamer/${i.user_id}';">
               GO
               </button>
            </td>
         </tr>
         </#list>
      </table>
   </body>
</html>