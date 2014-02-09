@echo off
call "./jre/bin/javaw.exe" -cp "OBDQ.jar;javaFXRuntime/lib/jfxrt.jar;javaFXRuntime/lib/javaws.jar;lib/*;plugins/*" -splash:misc/splash.png com.queeq.obdq.OBDQ
EXIT /B