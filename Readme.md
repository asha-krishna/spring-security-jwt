**Spring-Security-JWT Token**

1. First get JWT token by making POST call

![image](https://user-images.githubusercontent.com/73759012/147288451-8c8e0260-4627-4e1b-81dd-57ef54b4b879.png)


2. This token gets expired after 1 hour

3. If we make any HTTP request without passing token, then it gives "403 Forbidden".
 
 ![image](https://user-images.githubusercontent.com/73759012/147288511-09d74e31-22fc-4d9b-a5af-5b3605a923e0.png)

4. Send Bearer token in the Authorization header

![image](https://user-images.githubusercontent.com/73759012/147288616-b3784446-d2d4-4a47-9498-db8768eaeb1e.png)
