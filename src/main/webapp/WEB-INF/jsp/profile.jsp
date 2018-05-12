
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-W3C//DTD HTML 4.01 transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
	<head>
		<meta http-equiv="Content-Typt" content="text/html; charset=ISO-8859-1">
		<title>your Profile</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>



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
       <li class="nav-item">
        <a class="nav-link disabled" href="/getnotification">Notification</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Logout</a>
      </li>
    </ul>
  </div>
  </div><br>
  

	            
		<nav class="navbar navbar-toggleable-md navbar-light bg-success">
		  <a class="navbar-brand" href="#" style="text-align:center">${Users.name}</a>
		</nav>

		<div class="container py-5">
			<div class="row">
				<div class="col-12">
					<img src="${Users.imageProfile}" alt="Profile page image" class="rounded-circle" style="border:solid 1px black; width:30px; height:40px;"/>
					<h2 class="py-3" style="border:solid 1px black; border-radius:12px; margin:1% 20% 4% 20%;">${Users.dis} </h2>
					
				</div>
			</div>
		</div><br><br>
		
		<c:choose> 
	     <c:when test="${mode=='FRIEND_POST' }">
		<div class="table-responsive">
				<h3 style="color:black; padding-left:40%"> Posts</h3>
					<table class="table table-striped table-bordered" style="color:black">
						<thead>
							<tr>
							<th>PostID</th>
							<th>Post Name</th>
								</tr>
						</thead>
						<tbody>
							
							<c:forEach var="man" items="${manav}">
								<tr>
								 <td>${man.postID}</td> 
								<td><c:out value="${man.postDis}" /></td>
							 <td> <a href=/seePost?pid=${man.postID}>See Post</a></td> 
							</tr>
							</c:forEach>
						</tbody>
					</table>
		
		</div>
			</c:when>
	
</c:choose> 
<c:choose> 
	     <c:when test="${mode=='NOTIFICATION' }">
		<div class="table-responsive">
				<h3 style="color:black; padding-left:40%"> NOTIFICATIONS</h3>
					<table class="table table-striped table-bordered" style="color:black">
						<thead>
							<tr>
							
							<th>Notifications</th>
								</tr>
						</thead>
						<tbody>
							
							<c:forEach var="note" items="${NOTE}">
								<tr>
								<td><c:out value="${note.uname}" /> has uploaded the post</td>
								
							 <td> <a href=/seePost?pid=${note.pid}>See Post</a></td> 
							</tr>
							</c:forEach>
						</tbody>
					</table>
		
		</div>
			</c:when>
	
</c:choose> 
		
	</body>
</html>


