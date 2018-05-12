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
<div>

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
		<div class="table-responsive">
				<h3 style="color:black; padding-left:40%"> Posts</h3>
					<table class="table table-striped table-bordered" style="color:black">
						<thead>
							<tr>
							<th>Photo</th>
							<th>Post Name</th>
								</tr>
						</thead>
						<tbody>
							
							<c:forEach var="man" items="${manav}">
								<tr>
								<td><img src="${man.photoURL}" alt="Post page image" class="rounded-circle" class="center" style="border:solid 1px black; width:20px; height:30px;" /></td>
								 
								<td><c:out value="${man.postDis}" /></td>
							 <td> <a href=/seePost?pid=${man.postID}>See Post</a></td> 
							</tr>
							</c:forEach>
						</tbody>
					</table>
					
							
						
					
				</div>
		</div>
</body>
</html>