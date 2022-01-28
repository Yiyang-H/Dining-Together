# Dining-Together(Version 1.0)
## Brief introduction
Dining together is a pactical app designed for yong people in big cities, especially for those trying to adapt to new enviroment.
They are likely to be lonely and homesick when they come to a new place in their first time.
Our app is an ideal platform for them not only to share food but to spread their ideas and happiness.
Through this platform, we are dedicated to helping more and more people like us become used to their new life and we believe they will like such
a multicultural and inclusive place.
## Technical instruction
### Framework and tools used
- The frontend of our app is built with react
- The backend is built with Java Spring
- The database is used with SQL, and is deployed with AWS RDS
- The Server is deployed using heroku (CI/CD)
### How to open our project
1. Our app can be opened by https://dining-together.herokuapp.com
   *Note: the upload image feature can not be used because of paid addons in heroku*
2. In local host
    - install MySQL workbench and create a schema called 'dining_together'
    - Open backend folder
        - configure backend app in IntelliJ 
        - Run diningTogetherApplication
    - open frontend folder
        - nodejs installed
        - install packages using 'npm install' 
        - run app using 'npm run dev'
## How we achieved it
### Basic workflow
Here are our steps to design and build our app:
1. Discuss and build a conceptualized idea of our app
2. Formalize it with business process models
![](https://github.com/Lulu54368/Dining-Together/blob/main/plan/BPMN.jpg%20(2).svg)
3. Draw Use case diagram
![](https://github.com/Yiyang-H/Dining-Together/blob/main/plan/Use%20case%20diagram.png)
4. Design Fully dressed use case
https://github.com/Lulu54368/Dining-Together/blob/main/plan/Use%20case.pdf
5. Design frontend UI with figma
6. At the same time, we design our database with the ER mdoels
![](https://github.com/Yiyang-H/Dining-Together/blob/main/plan/ER%20model.png)
7. Build a phyical design in MYSQL workbench
8. Put everything into implementation
### Technical details
#### Distribution
- Frontend development and deployment:
    Ziqi & Yiyang
- Backend development and deployment:
    Shumin & Yuntao(Lulu)
#### Frontend detailed information
- We used Material UI to make some components more interactive.
- We designed the UI of the website using Figma before we start coding.
![image](https://user-images.githubusercontent.com/67890406/151504713-49e3b979-adca-459e-b657-7cc01e6eaf24.png)
- In order to keep track the user's login status, we store JWT token in Cookie, and it can be decoded to provide necessary information.


#### Backend detailed information
- The architect we used is Model-Service-Controller
- We involve DTO in our design in order to reduce coupling and have more control of input and output for the api.
- We document our API using swagger 3.0(open api).
- The signIn and registeration system is used with Spring Security and JWT
## Our Future prospective
Firstly we are going to make it more comprenhensive and sophiscated by adding some more feature,
including but not limited to:
- registering using email validation
- Viewing and modifying user profile
- notifying both sides through sending email registered
- Design and develop a better way to post pictures

We are very cared about users' experience
- We will promote it through various communites and clubs, from which we can get more and more feedback
- We will evaluate the technical feasibility and update our app according to it.

We are also open to new developers to join
- As long as you are passionate about learning new technologies and willing to contribute to our project, we are more than welcome.

