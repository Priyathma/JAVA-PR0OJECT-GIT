<%@ page import="javax.servlet.http.HttpSession" %>

<%
    // Get the session
    

    // Check if the user is logged in
    if (session != null && session.getAttribute("email") != null) {
        // Retrieve user information from the session
        String firstname = (String) session.getAttribute("firstname");
        String lastname = (String) session.getAttribute("lastname");
        String email = (String) session.getAttribute("email");
        String dob = (String) session.getAttribute("dob");
        String mobileno = (String) session.getAttribute("mobileno");
%>
<!DOCTYPE html>

<html>
    <head>
        <title>My Profile | Green Supermarket</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        
            <style>
            
            * {
              box-sizing: border-box;
            }

            
            /* Style the body */
            body {
              font-family: Arial, Helvetica, sans-serif;
              background-color: #f1f1f1;
              margin: 0;
            }

            
            /* Header/logo Title */
            .header {
              padding: 80px;
              text-align: center;
              background: #009900;
              color: white;
            }

            
            /* Increase the font size of the heading */
            .header h1 {
              font-size: 80px;
            }

            
            /* Style the top navigation bar */
            .navbar {
              overflow: hidden;
              background-color: #006600;
            }

            
            /* Style the navigation bar links */
            .navbar a {
              float: left;
              display: block;
              color: white;
              text-align: center;
              padding: 25px 20px;
              text-decoration: none;
            }

            
            /* Right-aligned link */
            .navbar a.right {
              float: right;
            }

            
            /* Change color on hover */
            .navbar a:hover {
              background-color: white;
              color: black;
            }

            
            /* Column container */
            .row {  
              display: -ms-flexbox; /* IE10 */
              display: flex;
              -ms-flex-wrap: wrap; /* IE10 */
              flex-wrap: wrap;
            }

            
            /* Create two unequal columns that sits next to each other */
            /* Sidebar/left column */
            .side {
              -ms-flex: 30%; /* IE10 */
              flex: 30%;
              background-color: #f1f1f1;
              padding: 20px;
            }

            
            /* Main column */
            .main {   
              -ms-flex: 70%; /* IE10 */
              flex: 70%;
              background-color: white;
              padding: 20px;
            }

            
            /* Fake image, just for this example */
            .fakeimg {
              background-color: #aaa;
              width: 100%;
              padding: 20px;
            }

            
            /* Footer */
            .footer {
              padding: 20px;
              text-align: center;
              background: black;
            }

            .footer h2,a {
              color: white;
            }
            
            /* Responsive layout - when the screen is less than 700px wide, make the two columns stack on top of each other instead of next to each other */
            @media screen and (max-width: 700px) {
              .row {   
                flex-direction: column;
              }
            }

            
            /* Responsive layout - when the screen is less than 400px wide, make the navigation links stack on top of each other instead of next to each other */
            @media screen and (max-width: 400px) {
              .navbar a {
                float: none;
                width: 100%;
              }
            }
            
            
            
            
            button {   
            background-color: #009900;   
            width: 100%;  
            color: white;   
            padding: 15px;   
            margin: 10px 0px;   
            border: none;   
            cursor: pointer;   
          }   
          
          
            form { 
            width: 70%; 
            border: 1px solid #f1f1f1;
            /*color: white;*/
          }   
          
          
            input[type=text], input[type=password] {   
            width: 100%;   
            margin: 8px 0;  
            padding: 12px 20px;   
            display: inline-block;   
            border: 2px solid green;   
            box-sizing: border-box;   
          }  
          
          
            button:hover {   
            opacity: 0.7;   
          }   
          
          
            .cancelbtn {   
            width: auto;   
            padding: 10px 18px;  
            margin: 10px 5px;  
          }   
        
     
            .container {   
            padding: 35px;   
            font-color: black;
            background-color: white;  
          }
          
            .container a,table {
            color: black;
          }
          
        </style>

        
    </head>
    <body>
        
        <!-- header code -->
        <div class="header">
            <h1>GREEN SUPERMARKET</h1>
            <p>JAVA PROJECT G23</p>
        </div>
        
        <!-- navbar code -->
        <div class="navbar">
            <a href="home.html">HOME</a>
            <a href="#">CATEGORY</a>
            <a href="history.html">HISTORY</a>
            <a href="aboutus.html">ABOUT US</a>
            <a href="settings.html" class="right"><img src="settings.png" width="20" height="20"</a>
            <a href="index.html" class="right">LOGIN</a>
            <a href="register.html" class="right">REGISTER</a>
        </div>
        
        <center> <h2> MY PROFILE </h2> 
        
        
        <form>  
            <form>  
                <div class="container">   
                    <table>
                       <tr>
                       <center>  <th><h2>Hello, <%= firstname %>!</h2></th></center>
                       </tr>
                       <tr>
                           <td>Full Name -</td> <td><%= firstname %> <%= lastname %></td>
                       </tr>
                       <tr>
                           <td>Email Address -</td> <td><%= email %></td>
                       </tr>
                       <tr>
                           <td>Mobile Number -</td> <td><%= mobileno %></td>
                       </tr>
                       <tr>
                           <td>Birthday -</td> <td><%= dob %></td>
                       </tr>
                    </table>
                </div>
            </form>

        </center>

         
        
        <!--footer code -->
        <div class="footer">
        <h2>FOOTER</h2>
        <a href="contactus.html">CONTACT US</a>
        </div>
        
        
        
    </body>
</html>
<%
    } else {
        // If the user is not logged in, redirect to the login page
        response.sendRedirect("myprofile.jsp");
    }
%>
