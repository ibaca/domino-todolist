# domino-todolist
Todo-list using Domino implementing UI with three different ways:

- elemento + Polymer components
- UIBinder + GWTMaterialDesign
- Desktop JavaFx (Requires a jdk with javafx support)

## How to build :

1. clone the repository

2. run `mvn clean install` to compile with UiBinders and Material design or run `mvn clean install -Dui=polymer` to compile with elemento and polymer web components

## How to run :
- Running as web application :

  - For super dev mode 
  
    - In one terminal run `mvn gwt:codeserver -pl *-frontend -am`
    
    - In another terminal `cd todolist-backend`
    - execute `mvn exec:java`
    - the server port will be printed in the logs access the application on `http://localhost:[port]`

  - For compiled mode 
    - `cd todolist-backend`
    - execute `mvn exec:java -Dmode=compiled`
    - the server port will be printed in the logs access the application on `http://localhost:[port]`

> Note : The UI you see in the browser depends on what command you used to build the application.

- Running the desktop version
  - in a terminal `cd todolist-desktop-frontend`
  - execute `mvn exec:java  -Dexec.args="localhost [port]"`


Thats it, have fun..
  
