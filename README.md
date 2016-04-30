# RSSFeedAggregatorAPI

API for Epitech project "RSS Feed Aggregator"
This project is running on Tomcat8 server, with mongo as database

How to install and run API with docker :

1. Install docker (see docker documentation to get install tutorial without )

2. Create mongo container with following command : [docker run --name MONGO_CONTAINER_NAME -v PATH_FOLDER_DB_SAVE:/data/db -d -p 27017:27017 mongo]

3. Create tomcat container with following command : [docker run --name TOMCAT_CONTAINER_NAME --link MONGO_CONTAINER_NAME:rss_mongo -d -p EXTERNAL_PORT:8080 tutum/tomcat]

4. Download API .war file from repository

5. Get tomcat admin id and password with following command : [docker logs TOMCAT_CONTAINER_NAME]

6. Go on url : [http://localhost:EXTERNAL_PORT] , login with previous id/password and install API .war

7. Go on url : [http://localhost:EXTERNAL_PORT/rssaggregator] ; if you see "Jersey RESTful API is running" message, your API is successfully installed and running

8. Go on url : [http://localhost:EXTERNAL_PORT/rssaggregator/api/users] ; if you see a simple "[]" message, your tomcat container is successfully linked with your mongo container


Notes :
* PATH_FOLDER_DB_SAVE	: is the path to the folder that will contain mongo datas (avoid loosing all your database if you delete your container)
* MONGO_CONTAINER_NAME	: is the name you want to give to your mongo container, to avoid using container ID in commands
* TOMCAT_CONTAINER_NAME	: is the name you want to give to your tomcat container, to avoid using container ID in commands
* EXTERNAL_PORT		: is the port you will use to access tomcat in urls

I recommend using "tutum/tomcat" docker image (latest is Tomcat8 while writing this README) instead of official "tomcat" docker image because "tutum/tomcat" image launches with a generated admin account (id + password) while official "tomcat" image yet doesn't have an easy way to setup accounts


__________________________________

API Available requests :

* POST :: localhost:8080/rssaggregator/api/user/register
* POST :: localhost:8080/rssaggregator/api/user/login
* PUT :: localhost:8080/rssaggregator/api/user
* GET :: localhost:8080/rssaggregator/api/feeds
