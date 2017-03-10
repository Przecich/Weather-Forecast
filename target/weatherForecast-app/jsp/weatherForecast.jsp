 <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
    <head>
    <meta contentType="text/html; charset="UTF-8"/>
       <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css"/>      
       <script src="..//bootstrap/js/bootstrap.min.js"></script>   
       <style>
		        .jumbotron { 
   			      /* background-color: 	#00BFFF; Orange */
              background-image:url("sky-22.jpg");
    		      color: #ffffff;
    		      padding: 100px 25px;
              font-family: Montserrat, sans-serif;
				    }
            .container-fluid {
              padding: 100px 50px;
            }
		        .bg-grey {
    		      background-color: #f6f6f6;
		        }

		        .container-fluid {
    		      padding: 60px 50px;
		        }
            .logo-small {
              /*color: #f4511e;*/
              font-size: 50px;
            }
            .logo {
              font-size: 200px;
            }
            .weather-info{
              font-size: 20px;
            }
            .city-info{
              font-size: 20px;
            }
            body {
              font: 400 15px Lato, sans-serif;
              line-height: 1.8;
             
            }
          
            @media screen and (max-width: 768px) {
            .col-sm-4 {
            text-align: center;
            margin: 25px 0;
            }
            
          

		    </style>    
    </head>

    <body>          
       
      




        <div class="jumbotron text-center bg-grey">
  			<h1>Weather Forecast</h1> 
  			<p>Check the forecast for your city</p> 
  			 <form class="form-inline" action="/weather" method="get" id="seachEmployeeForm" role="form">
         <input type="hidden" id="searchAction" name="searchAction" value="searchByCity">
    			<div class="input-group">
      				<input type="text" name="cityName" id="cityName" class="form-control" size="50" placeholder="City"   required>
      				<div class="input-group-btn">
        		    <button type="submit" class="btn btn-danger">Check</button>
      			  </div>
    			</div>
  			 </form>
		    </div>


     

        <c:choose>
     <c:when test="${not empty weatherData[0].name }"> 
    <div class="container-fluid">
        <div class="row">
          <div class="col-sm-5 col-xs-6 col-md-3 city-info">
            <!-- <span class="glyphicon glyphicon-signal logo"></span> -->
            
            <div class="media">
              <div class="media-left ">
                <img src="${weatherData[0].weather[0].icon}"  alt="Cinque Terre" width="120" height="120">
              </div>
              <div class="media-body">
                <h1>${weatherData[0].main.celsiusTemp}<small>°C</small></h1>
                <h1>${weatherData[0].name}</h1>
                <h3>${weatherData[0].date}</h3> 
                <p>${weatherData[0].weather[0].description}</p>
              </div>
           </div>
          </div>
         
          <div class="col-sm-2 col-xs-3 col-md-2 weather-info">
          <!--   <div class="panel panel-default">
              <div class="panel-header"></div>
              <div class="panel-body"> 
                <p><strong>Humidity</strong> ${weatherData[0].main.humidity}</p>
                <p><strong>Wind</strong> ${weatherData[0].wind.speed} m/s</p>
                <p><strong>Clouds</strong> ${weatherData[0].clouds.all}%</p>
              
              </div>
            </div>  -->
        <table class="table table-striped table-bordered table-condensed">
    <thead>
     <!--  <tr>
        <th>Firstname</th>
        <th>Lastname</th>
      </tr> -->
    </thead>
    <tbody>
      <tr>
        <td>Humidity</td>
        <td>${weatherData[0].main.humidity} </td>
       
      </tr>
      <tr>
        <td>Wind</td>
        <td>${weatherData[0].wind.speed} m/s</td>
      
      </tr>
       <tr>
        <td>Clouds</td>
        <td>${weatherData[0].clouds.all}% </td>
       
      </tr>

       <tr>
        <td>Preassure</td>
        <td>${weatherData[0].main.pressure} hPa</td>
      
      </tr>
      <tr>
        <td>Sunrise</td>
        <td>${weatherData[0].sys.sunRise} </td>
      
      </tr>
      <tr>
        <td>Sunset</td>
        <td>${weatherData[0].sys.sunSet} </td>
      
      </tr>
   
     
    </tbody>
  </table>
          

          </div>

          <div class="col-sm-5 col-xs-3 col-md-7"></div>
           
          
        </div>
    </div>
     

    

  <!--   <div class="container-fluid bg-grey">
        <h2>Our Values</h2>
        <h4><strong>MISSION:</strong> Our mission lorem ipsum..</h4> 
        <p><strong>VISION:</strong> Our vision Lorem ipsum..
    </div>
 -->
   

    <div class="container-fluid">
  <div class="text-center">
    <h2>Forecast</h2>
    <h4>Weather for following days</h4>
  </div>
  <div class="row">
    <div class="col-sm-3">
      <div class="panel panel-default text-center">
        <div class="panel-heading">
          <h1>${weatherData[1].dayOfWeek}</h1>
          ${weatherData[1].dateWeek}
        </div>
        <div class="panel-body">
         
                <img src="${weatherData[1].weather[0].icon}"  alt="Cinque Terre" width="120" height="120">
            
                <p>${weatherData[1].main.celsiusTemp} °C</p>
            
                <p>${weatherData[1].weather[0].description}</p>
               
        </div>
       
      </div> 
    </div> 
    <div class="col-sm-3">
      <div class="panel panel-default text-center">
        <div class="panel-heading">
          <h1>${weatherData[2].dayOfWeek}</h1>
          ${weatherData[2].dateWeek}
        </div>
        <div class="panel-body">
         
                <img src="${weatherData[2].weather[0].icon}"  alt="Cinque Terre" width="120" height="120">
            
                <p>${weatherData[2].main.celsiusTemp} °C</p>
            
                <p>${weatherData[2].weather[0].description}</p>
        </div>
      
      </div> 
    </div> 
   <div class="col-sm-3">
      <div class="panel panel-default text-center">
        <div class="panel-heading">
          <h1>${weatherData[3].dayOfWeek}</h1>
          ${weatherData[3].dateWeek}
        </div>
        <div class="panel-body">
           
          
                <img src="${weatherData[3].weather[0].icon}"  alt="Cinque Terre" width="120" height="120">
            
                <p>${weatherData[3].main.celsiusTemp} °C</p>
             
                <p>${weatherData[3].weather[0].description}</p>
               
             
          
        </div>
       <!--  <div class="panel-footer">
          <h3>$49</h3>
          <h4>per month</h4>
          <button class="btn btn-lg">Sign Up</button>
        </div> -->
      </div> 
    </div> 

    <div class="col-sm-3">
      <div class="panel panel-default text-center">
        <div class="panel-heading">
          <h1>${weatherData[4].dayOfWeek}</h1>
          ${weatherData[4].dateWeek}
        </div>
        <div class="panel-body">
           
          
                <img src="${weatherData[4].weather[0].icon}"  alt="Cinque Terre" width="120" height="120">
            
                <p>${weatherData[4].main.celsiusTemp} °C</p>
             
                <p>${weatherData[4].weather[0].description}</p>
               
             
          
        </div>
       <!--  <div class="panel-footer">
          <h3>$49</h3>
          <h4>per month</h4>
          <button class="btn btn-lg">Sign Up</button>
        </div> -->
      </div> 
    </div> 


  </div>
</div>







    <div id="googleMap" style="height:400px;width:100%;"></div>

<!-- Add Google Maps -->
<script src="http://maps.googleapis.com/maps/api/js"></script>
<script>
var myCenter = new google.maps.LatLng(${weatherData[0].coord.lat}, ${weatherData[0].coord.lon});

function initialize() {
var mapProp = {
center:myCenter,
zoom:12,
scrollwheel:false,
draggable:false,
mapTypeId:google.maps.MapTypeId.ROADMAP
};

var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);

var marker = new google.maps.Marker({
position:myCenter,
});

marker.setMap(map);
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>
    </c:when> 
     <c:otherwise>



     <div class="container-fluid text-center">
  <h2>Forcast</h2>
  <h4>What we offer</h4>
  <br>
  <div class="row">
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-cloud logo-small"></span>
      <h4>Current Weather</h4>
      <p>Detailed data on current weather</p>
    </div>
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-certificate logo-small"></span>
      <h4>4 days forecast</h4>
      <p>Basic 4 day forecast information</p>
    </div>
    <div class="col-sm-4">
      <span class="glyphicon glyphicon-map-marker logo-small"></span>
      <h4>Maps</h4>
      <p>Location of given city</p>
    </div>
    </div>
    <br><br>
    </div>
    </c:otherwise>
    </c:choose>



    
		


		

    </body>
</html>