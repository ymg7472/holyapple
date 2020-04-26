<html>
<head>
    <meta charset="UTF-8">
    <title>간단한 지도 표시하기</title>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=6msw8dshkf"></script>
</head>
<body>
<div id="map" style="width:100%;height:100%;"></div>

<script>
var MARKER_SPRITE_POSITION = {
<#list sss as i>
        "${i.name}": [${i.lat}, ${i.lng}, "${i.remain_stat}"], <#sep>
</#list>

    };
	
 var map = new naver.maps.Map('map', {
    center: new naver.maps.LatLng(37.3595704, 127.105399),
    zoom: 10
});


var markers = [],
    infoWindows = [];
    
var bounds = map.getBounds(),
    southWest = bounds.getSW(),
    northEast = bounds.getNE(),
    lngSpan = northEast.lng() - southWest.lng(),
    latSpan = northEast.lat() - southWest.lat();
   
    

for (var key in MARKER_SPRITE_POSITION) {

	var position = new naver.maps.LatLng(MARKER_SPRITE_POSITION[key][0], MARKER_SPRITE_POSITION[key][1]);
	var stat = MARKER_SPRITE_POSITION[key][2];
    var marker = new naver.maps.Marker({
        map: map,
        position: position,
        title: key,
        zIndex: 100
    });
    var infoWindow = new naver.maps.InfoWindow({
         content: '<div style="width:150px;text-align:center;padding:10px;">현 위치는 <br> <b>"'+ key +'"</b>.  <br> 재고량: "'+ stat +'"</div>'
    });

    markers.push(marker);
    infoWindows.push(infoWindow);
};

function showMarker(map, marker) {

    if (marker.setMap()) return;
    marker.setMap(map);
}

function hideMarker(map, marker) {

    if (!marker.setMap()) return;
    marker.setMap(null);
}

function updateMarkers(map, markers) {

    var mapBounds = map.getBounds();
    var marker, position;

    for (var i = 0; i < markers.length; i++) {

        marker = markers[i]
        position = marker.getPosition();

        if (mapBounds.hasLatLng(position)) {
            showMarker(map, marker);
        } else {
            hideMarker(map, marker);
        }
    }
}
naver.maps.Event.addListener(map, 'idle', function() {
    updateMarkers(map, markers);
});
function getClickHandler(seq) {
    return function(e) {
        var marker = markers[seq],
            infoWindow = infoWindows[seq];

        if (infoWindow.getMap()) {
            infoWindow.close();
        } else {
            infoWindow.open(map, marker);
        }
    }
}

for (var i=0, ii=markers.length; i<ii; i++) {
    naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
}

</script>
</body>
</html>