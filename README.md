# Wroong
Wroong is a web application designed to manage a list of Donald Trump tweets. It lets you add, delete, edit, sort and search tweets in your database.

# Deployment
The easiest way to deploy Wroong is to place youself in the "docker-compose image" folder and execute `docker-compose up --build` in a terminal. This can take a while because it's loading almost 30,000 tweets into memory.

You can then use Wroong on your local machine by accessing http://localhost:9090/wroong/home from your favorite browser.

# Usage
Use the navbar on the left to navigate between menus.
## Tweets
Lists every tweet added to the database. The search, sort, paging and delete functions are self-explanatory. Click "Edit" to change a tweet's text.
## Configuration - Add a tweet
Enter the tweet's text content to add a tweet to the database. The "Created on" date will be set to the instant you press "Submit".
## Configuration - Populate/clear database
Here you can populate the database with a specified number of tweets. They will be randomly picked from every tweet by Donald Trump (up until 14.10.2017). The "Created on" field will be set to the actual date Trump published each tweet.

Finally, you can entirely clear the database if you are FAKE NEWS.

## Authors
Antoine Friant and Lawrence Stalder for HEIG-VD (AMT 2017 https://github.com/SoftEng-HEIGVD/Teaching-HEIGVD-AMT-2017-Main)

# Credits :
Template from https://startbootstrap.com/template-overviews/sb-admin-2/

Data scraped with https://github.com/taspinar/twitterscraper
