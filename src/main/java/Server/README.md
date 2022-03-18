RobotWorld:

This project consist of two programs, the server and the client. The server program is responsible for managing a world with its obstacles, robots and anything else we program into the world. The client program is responsible for launching a robot into the world and controlling the robot in the world by sending messages to the server program and receiving messages back in response.

Technologies:

Java
Maven


Developer setup:

Ensure all relevant technologies are installed.
In your terminal run: git clone https://github.com/wtc-cohort-2020/0111-robot-worlds.git
Open the project in your prefered IDE
Run the server and the client from your IDE


Test Plan

The focus of our tests

Our tests focus on the domain code of the project.
* Tests to assert that our world generates obstacles and pits
  as expected
* Tests to assert that the methods in our Obstacle
  class and pit class were as intended
* We assert that our response functions are
  returning the correct strings

To run the tests with Maven, run:

mvn test

in the root of the repository

Expectations:
* We expect that our world generates a set amount of pits and obstacles
* We expect our obstacles to behave in a certain way
* We expect our pits to behave in a certain way
* We expect to be returned with pre-defined response strings

