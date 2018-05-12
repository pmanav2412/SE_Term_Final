<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
  
  <meta charset="utf-8">
  <title>Record an Audio</title>
<!--/////////////////////////////////////BOOTSTARP PART//////////////////////////////////////////   -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
 <!--/////////////////////////////////////////////////////////////////////////////   -->
 <link href="https://vjs.zencdn.net/6.6.3/video-js.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.0/css/videojs.record.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/video.js/6.7.2/video.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/RecordRTC/5.4.6/RecordRTC.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/adapterjs/0.15.0/adapter.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/wavesurfer.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/wavesurfer.microphone.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/videojs.wavesurfer.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.2/videojs.record.min.js"></script>

<!--/////////////////////////////////////////////////////////////////////////////  PHOTO ///////////////////////////// -->


  <link href="../node_modules/video.js/dist/video-js.min.css" rel="stylesheet">
  <link href="../dist/css/videojs.record.css" rel="stylesheet">

  <script src="../node_modules/video.js/dist/video.min.js"></script>
  <script src="../node_modules/webrtc-adapter/out/adapter.js"></script>

  <script src="../dist/videojs.record.js"></script>
 
 

  <style>
  
  /* change player background color */
  #myAudio {
      background-color: #9FD6BA;
  }
  /* //////////////////////////////////// */
     #myImage {
      background-color: #efc3e6;
  } 
  
  </style>
</head>
<body>
	           
  <div class="card text-center">
  <div class="card-header">
    <ul class="nav nav-tabs card-header-tabs">
      <li class="nav-item">
        <a class="nav-link active" href="/profile1">Profile</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/home">Friend List</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="/recordAudio">Create Post</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="/postList">Posts</a>
      </li>
      </li>
       <li class="nav-item">
        <a class="nav-link disabled" href="/getnotification">Notification</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Logout</a>
      </li>
    </ul>
  </div>
  </div><br>

<h1 style="margin-left:45%;">Create Post</h1>
 <div><video id="myImage" class="video-js vjs-default-skin" style="margin-left:30%; margin-right:30%;"></video></div><br> 
<div><audio id="myAudio" class="video-js vjs-default-skin" style="margin-left:30%; margin-right:30%;"></audio></div><br>

<script>
var player = videojs("myAudio", {
    controls: true,
    width: 250,
    height: 200,
    plugins: {
        wavesurfer: {
            src: "live",
            waveColor: "#36393b",
            progressColor: "black",
            debug: true,
            cursorWidth: 1,
            msDisplayMax: 20,
            hideScrollbar: true
        },
        record: {
            audio: true,
            video: false,
            maxLength: 20,
            debug: true
        }
    }
}, function(){
    // print version information at startup
    videojs.log('Using video.js', videojs.VERSION,
        'with videojs-record', videojs.getPluginVersion('record'),
        '+ videojs-wavesurfer', videojs.getPluginVersion('wavesurfer'),
        'and recordrtc', RecordRTC.version);
});

// error handling
player.on('deviceError', function() {
    console.log('device error:', player.deviceErrorCode);
});

// user clicked the record button and started recording
player.on('startRecord', function() {
    console.log('started recording!');
});

// user completed recording and stream is available
player.on('finishRecord', function() {
    // the blob object contains the recorded data that
    // can be downloaded by the user, stored on server etc.
    console.log('finished recording: ', player.recordedData);
    
    var reader= new FileReader();
    var base64data;
    reader.readAsDataURL(player.recordedData);
    reader.onloadend=function(){
    	base64data= reader.result;
    	console.log(base64data);
    	$("#recording").val(base64data);
    } 
});
$(document).ready(function(){
	$("#savebutton").on("click",function(){
		$("#audioForm").submit();
	});
});


<!-- ///////////////////////////////////////////// THIS IS PHOTO JS ///////////////////////////////////////// -->


var playerV = videojs("myImage", {
    controls: true,
    width: 150,
    height: 120,
    fluid: false,
    controlBar: {
        volumePanel: false,
        fullscreenToggle: false
    },
    plugins: {
        record: {
            image: true,
            debug: true
        }
    }
}, function(){
    // print version information at startup
    videojs.log('Using video.js', videojs.VERSION,
        'with videojs-record', videojs.getPluginVersion('record'));
});
// error handling
playerV.on('deviceError', function() {
    console.warn('device error:', player.deviceErrorCode);
});
playerV.on('error', function(error) {
    console.log('error:', error);
});
// snapshot is available
playerV.on('finishRecord', function() {
    // the blob object contains the image data that
    // can be downloaded by the user, stored on server etc.
    console.log('snapshot ready: ', playerV.recordedData);
    $("#photo").val(playerV.recordedData);
    
});
$(document).ready(function(){
	$("#savebutton").on("click",function(){
		$("#audioForm").submit();
	});
}); 
</script>


<form id="audioForm" action="/base64Audio" method="post" >
   <input type="hidden" id="recording" name="123">
    <input type="hidden" id="photo" name="1234">
  
   <div class="form-group">
      <label for="usr" style="padding-left:45%; color:blue;"><h3>Post Description:</h3></label>
      <input type="text" style="margin-top:20px;  color:black;" class="form-control" id="usr" name="PostDis">
    </div>
   
</form>
<!-- <button id="savebutton" style>save</button> -->
<button type="button" class="btn btn-primary" id="savebutton" style="margin-left:47%;" >Create Post</button>
</body>
</html>

