## Custom JWT authentication filter

This project was created to experiment with login methods using JWT. In the project I created a custom oncePerRequest filter to
validate the jwt token included in requests to protected endpoints.   
For logging in an endpoint is used to accept the user login request (email and password) and from that the user is authenticated against the saved user in the database.   
A JWT is then created manually using the jjwt package.    

This is a simplified project made to play around with spring security and is not a reflection of production ready code. 
