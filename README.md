OmxRemote
=========

Android application to control omxplayer on a raspberrypi over the network via a java server and screen.

Setup
-----
* Fill in your raspberry pi's ip and a port number in ./Android/src/org/dyndns/stonehammer/pontus/OmxRemote/Client.java and in ./server.java
* omx_control.sh and server.java goes on the raspberry pi. (Don't forget to compile server.java)
* Start omxplayer with screen -S omxplayer -d -m omxplayer /path/to/video
* Run java server
* Run the app
