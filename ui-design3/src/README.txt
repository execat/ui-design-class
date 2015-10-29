/**
 *      Robot Interface (UI Design and Mobile App Development class)
 *      Assignment #3
 *
 *      Anuj More (atm140330@utdallas.edu)
 *
 **/

The folder contains the robotinterface package which contains the following classes:

Interfaces:
1. Controller
        Defines all the methods required to be implemented by the RobotController.
2. Model
        Defines all the methods required to be implemented by the RobotModel.

Classes:
1. RobotInterface
        This class has the main method that needs to be executed.
2. RobotController
        This class is called by actions performed on interface (eg buttons).
3. RobotModel
        This class is the data model for the robot.
4. State
        This class maintains the state of the program and is accessed by interface, controller, model.

Enumerations:
1. ArmState
        Limits ArmState to GRAB and RELEASE.

Design philosophy:
Trying to be MVC but breaking MVC in some areas to keep the code concise or for convenience.
Particularly, State objects can be mutated by any class as they are public. That was done to reduce the length of
State.java since that file could easily be 5x its current length if getters-setters were written.

IMO you should start with Model.java and Controller.java to see what methods are being implemented in the respective
Robot classes. RobotInterface is mostly UI soup except for the first bits which contain the code to refresh that UI.

RobotInterface.form was produced by NetBeans, so I am just going to keep it there. Mostly harmless.
