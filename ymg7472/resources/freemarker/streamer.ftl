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

	body {background-color: powderblue;}

      </style>
   </head>
   <body>
      <table>
         <tr>
            <td><img src="${profileimg}"/>
            </td>
            <td>
               <table>
                  <tr>
                     <td>스트리머 아이디: ${id}</td>
                  </tr>
                  <tr>
                     <td>스트리머 로그인: ${login}</td>
                  </tr>
                  <tr>
                     <td>스트리머 이름: ${display_name}</td>
                  </tr>
                  <tr>
                     <td>설명: ${description}</td>
                  </tr>
                  <tr>
                     <td>조회수: ${views}</td>
                  </tr>
               </table>
            </td>
         </tr>
      </table>
      <table>
		      <tr>
		      <td>
		      	<script src= "https://player.twitch.tv/js/embed/v1.js"></script>
      <div id="SamplePlayerDivID"></div>
      <script type="text/javascript">
         var options = {
           width: 854,
           height: 480,
           channel: "${login}",
         };
         var player = new Twitch.Player("SamplePlayerDivID", options);
         player.setVolume(0.5);
      </script>
		      </td>
		      <td>
		      <iframe frameborder="0"
        scrolling="no"
        id="chat_embed"
        src="https://www.twitch.tv/embed/${login}/chat?parent=localhost"
        height="500"
        width="350">
</iframe>
</td>
		      </tr>
      </table>
      <#list key as i>
      <iframe
         src="https://clips.twitch.tv/embed?clip=${i}&parent=localhost&autoplay=false"
         height="360"
         width="640"
         frameborder="0"
         scrolling="no"
         allowfullscreen="true">
      </iframe>
      </#list>
   </body>
</html>