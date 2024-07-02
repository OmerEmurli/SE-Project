@echo off
set PATH_TO_FX=C:\Users\Omer\Desktop\javafx-sdk-17.0.1\lib
sbt -J--module-path=%PATH_TO_FX% -J--add-modules=javafx.controls,javafx.fxml run
pause
