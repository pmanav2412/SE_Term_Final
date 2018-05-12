<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

<title>Insert title here</title>
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
  <div>
  <audio autoplay>
<source src="${manavA}" type="audio/webm">
</audio>
  </div>
  <div align="center">
 <div> <img src="${manavP}" alt="Post page image" class="rounded-circle" class="center" style="border:solid 1px black; width:150px; height:200px; "/></div>
  <h1 style="text-align:center;">${dis}</h1>
  </div>
  <div>
  
  <c:forEach var="com" items="${Comments}">
								
			<div class="alert alert-primary" role="alert" Style="margin-bottom:2px; margin-right:70%; margin-left:5%;">
  				${com.username} 
			</div>
			<div class="alert alert-light" role="alert" Style="length:30%; margin-top:0px; border: 2px solid gray;
    border-radius:12px; margin-right:50%; margin-left:10%">
             ${com.comment}
			</div>
									
  </c:forEach>
  <form action="/addComment?pid=${pid}" method="Post">
            <input type="text" style="margin-top:20px; margin-top:20px;border-radius: 12px; margin-right:40%; margin-left:10%; length:40%;" name="comment" minlength="2" placeholder="Comment here....."><br>
            <h4 style="color:red;">${error}</h4>
            <button type="submit" class="btn btn-primary" style="margin-top:20px; margin-top:20px;border-radius: 12px; margin-right:50%; margin-left:10%" > SAVE </button>
        </form>
      
   </div>
</body>
</html>