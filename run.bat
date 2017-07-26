REM code from rbprogrammer at [stackoverflow.com/questions/2106723/how-to-minimize-the-command-prompt-when-running-a-batch-script]
if not "%minimized%"=="" goto :minimized
set minimized=true
start /min cmd /C "%~dpnx0"
goto :EOF
:minimized
c:\Users\nicho\AppData\Local\Programs\Python\Python36-32\python gui.py