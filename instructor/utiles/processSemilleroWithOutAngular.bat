@ECHO OFF

rem En esta zona debe ir el path a la carpeta de semillero:
set pathSemillero=C:\Projects\semilleroHBT2019

rem En esta zona debe ir el path a la carpeta de wildfly
set pathWildfly=C:\wildfly-15.0.1.Final


set pathDeployments=%pathWildfly%\standalone\deployments
set pathSemilleroHbt=%pathSemillero%\ambiente\semillero-hbt
set commandMvnWithoutAngular=mvn clean install -DskipAngular
set pathTarget=%pathSemillero%\ambiente\semillero-hbt\semillero-padre\semillero-ear\target
set pathStandalone=%pathWildfly%\bin\standalone.bat

start /wait cmd.exe /c "cd %pathSemilleroHbt% && %commandMvnWithoutAngular%"

IF EXIST %pathDeployments%\semillero-ear-1.0-SNAPSHOT.ear.deployed. (del %pathDeployments%\semillero-ear-1.0-SNAPSHOT.ear.deployed)

IF EXIST %pathDeployments%\semillero-ear-1.0-SNAPSHOT.ear. (del %pathDeployments%\semillero-ear-1.0-SNAPSHOT.ear. && copy %pathTarget%\semillero-ear-1.0-SNAPSHOT.ear %pathDeployments% ) ELSE copy %pathTarget%\semillero-ear-1.0-SNAPSHOT.ear %pathDeployments% 

SLEEP 2

start %pathStandalone%

ECHO Process finished, remember that Standalone is running, close it before launch this .bat again
