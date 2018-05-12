<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"  %>
<!DOCTYPE html PUBLIC "-W3C//DTD HTML 4.01 transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</head>


<body>
<div style="margin:10%" class="row">
        
        <div class="col-md-8 order-md-1">
          <h4 class="mb-3">Create Profile</h4>
          <form class="needs-validation"  action="/profile" method="Post" enctype="multipart/form-data">
            
		<div class="container py-5">
		<div class="form-group">
		<label><strong><h3>enter your name </h3></strong></label>
		<input type="text" id="name"  required="" name="name"  minlength="10" value="${Users.name}">
		
                
		</div>
		
		<div class="form-group">
				<label><strong><h3>Select your Image</h3></strong></label>
				<input type="file" class="form-control-file" name="file">
		</div>
		<div>
			<textarea rows="4" cols="50" name="dis">
			</textarea>
			
			</div>
			
			<button type="submit" class="btn btn-primary" > SAVE </button>
		</div>
		 </form>
         </div>   
          </div>
          
      </body>
</html>

















































<!--  <!DOCTYPE html>
 <html>
<head>
<h1 align="center">HEALTH STATUS</h1>
 <style>

		.kuchbhi {
    margin: auto;
    width: 80%;
    /*border: 3px solid #73AD21;*/
    padding: 10px;
		text-align: left;

	}
	label,input{
		margin-bottom: 20px;
	}
	body{ 
    background-image: url("pf2.jpg");
    background-color: #cccccc;
	background-color: grey;}

</style>
</head>
<body>




	<form action="/Calories/add" method="Post">
	<div class="kuchbhi">
		<div class="form-group">
		<label for="formGroupExampleInput">uid</label><br>
	     <input type="text" name="name" value="" placeholder="User ID"  size="45">
		</div>
		<div class="form-group">
		<label for="formGroupExampleInput">Weight Lifting</label><br>
		<input type="text" name="wl" value="" placeholder="Enter calories burn by Weight Lifting "  size="45"><br>
		</div>
		<div class="form-group">
		<label for="formGroupExampleInput">Date</label><br>
		<input type="date" name="date" value="" placeholder=""  size="45"><br>
		</div>
		<div class="form-group">
		<label for="formGroupExampleInput">YOGA</label><br>
		<input type="number" name="yoga" value="" placeholder="Enter calories burn by YOGA"  size="45"><br>
		</div>
		<div class="form-group">
		<label for="formGroupExampleInput">Elliptical</label><br>
		<input type="number" name="ell" value="" placeholder="Enter calories burn by Elliptical"  size="45"><br>
		</div>
		<div class="form-group">
		<label for="formGroupExampleInput">Cycling</label><br>
		<input type="number" name="cyc" value="" placeholder="Enter calories burn by Cycling"  size="45"><br>
		</div>
		<div class="form-group">
		<label for="formGroupExampleInput">Walking</label><br>
		<input type="number" name="wal" value="" placeholder="Enter calories burn by Walking"  size="45"><br>
		</div>
		<div class="form-group">
		<label for="formGroupExampleInput">Jogging</label><br>
		<input type="number" name="jog" value="" placeholder="Enter calories burn by Jogging"  size="45"><br>
		</div>
		<div class="form-group">
		<label for="formGroupExampleInput">Running</label><br>
		<input type="number" name="run" value="" placeholder="Enter calories burn by Running"  size="45"><br>
		</div>
		<div class="form-group">
		<label for="formGroupExampleInput">Swimming</label><br>
		<input type="number" name="swim" value="" placeholder="Enter calories burn by Swimming"  size="45"><br>
		</div>
	
	<input type="submit" value="submit">
	</form>
</body>
</html> 
 

 -->