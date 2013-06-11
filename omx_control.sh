#!/bin/sh
if [ $#  -eq "2" ]
then
	killall screen
	screen -S omxplayer -d -m omxplayer -o hdmi /mnt/temp/$2
	echo "lol $2" > lol
else
echo $2 > lol
screen -S omxplayer -p 0 -X eval "stuff $1"
fi
