<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<title>희망 도서관 독서채팅</title>

<style type="text/css">
body {
	background-image: url("../img/pattern.png");  
}
#main{
    width : 60%; height :800px;
    margin : auto;
}
#idbox > #id{
    width : 40%;
}
#main textarea{
    display : block;
    resize: none;
    width: 99%;
    height: 90%;
    margin: auto;
}

#textbox > #msg{
    width : 85%;
    line-height: 30px;
}
#btnSend {
	float: right;
	line-height: 30px;
}

#btnDisconn
 {
 	float: right;
 }
 
</style>
</head>
<body onload="test();">

<div id="main">
 	<h1>독서토론방</h1>
 	<label id="idbox" for="id">ID : 
 		<input id="id" value="${ id }" disabled>
 		<button id="btnDisconn">나가기</button>
 	</label>
    <textarea id="content" readonly="readonly"></textarea>
    <br>
    <label id="textbox" for="msg"> 
	    <input type="text" id="msg"/>
	    <button id="btnSend">보내기</button>
    </label>
    
    
</div>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript">
var url = "ws://192.168.0.80:1337";
var ws = new WebSocket(url);
var id = $('#id').val();
function test(){
        console.log('open');
        var msg = JSON.stringify({'type': 'open', 'id': id});
        console.log('send:' + msg);
        ws.send(msg);
        $('#id').attr('disabled', 'disabled');
}
  $(function(){
        ws.onmessage = function(evt){
            console.log('msg:' + evt.data );
            var msg = JSON.parse(evt.data);
            var txt = "";
            if(msg.type == "open"){
                txt = msg.id + '님이 입장하셨습니다.';
            }else if(msg.type == "msg"){
             	txt = msg.id + ":" + msg.text;
            }else if(msg.type == "close"){
                txt = msg.id + '님이 퇴장하셨습니다.';
                
            }
            $('#content').val( $('#content').val() +'\n' + txt );
        }
        ws.onclose = function(evt){
            console.log('close');
            $('#id').removeAttr('disabled');
            var txt = 'connection closed.';
            $('#content').val( $('#content').val() +'\n' + txt );
            location.href="../index.do";
        }
        ws.onerror = function(evt){
            console.log('err:' + evt);
        }
    });
    $('#btnDisconn').click(function(){
    	var msg = JSON.stringify({'type': 'close', 'id': id});
        console.log('send:' + msg);
        ws.send(msg);
        end();
    });

    function end() {
    	ws.close();
    }
   
    function send(txt){
        var msg = {type :'msg', id: id, text : txt};
        if(msg.text != "") {
        	ws.send(JSON.stringify(msg));
        }
    }
    $('#btnSend').bind('click', function(){
        send($('#msg').val());
        $('#msg').val('');
    });
    $('#msg').bind('keydown', function(evt){
        if(evt.keyCode == 13){
            send($(this).val());
            $(this).val('');
        }
        window.setInterval(function() {
            var elem = document.getElementById('content');
            elem.scrollTop = elem.scrollHeight;
        }, 0);
    });
    
</script>
</body>
</html>