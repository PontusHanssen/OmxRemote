#!/bin/sh
if [ $#  -gt "1" ]
then
	killall screen
	FILE="${@:2}"
	screen -S omxplayer -d -m omxplayer -o hdmi "$FILE"
	echo $FILE > lol 
else
screen -S omxplayer -p 0 -X eval "stuff $1"
fi
