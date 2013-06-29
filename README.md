tren2gtfs, General Transit Feed Specification File for the Tren Urbano
=====

tren2gtfs is copyrighted free software by Yamir Encarnación &lt;yencarnacion@webninjapr.com&gt;.
You can redistribute it and/or modify it under the terms of the 2-clause BSDL (see the
file BSDL.txt).

What it does
=====

tren2gtfs generates a [General Transit Feed Specification File](https://developers.google.com/transit/gtfs/reference)
from a file provided by Víctor Ramirez &lt;victor.ramirez@gmail.com&gt;  which contains in csv format the
following information:


1. Arrival Station
2. Direction
3. An ID for a Weekly Schedule
4. The Actual Train Arrival time at the station.

At the moment, the file contains the arrival times for the [Tren Urbano of Puerto Rico](http://en.wikipedia.org/wiki/Tren_Urbano).
The program only generates the GTFS file for the Tren Urbano of Puerto Rico at the moment.  After the program is run you can find
the TREN2GTFS.zip file in src/main/resources/output/TREN2GTFS.zip

How to run
=====
To get this project working, perform the minimum steps in Maven:

>$ mvn groovy:compile

>$ mvn groovy:execute

Validation
=====

You can validate the file in src/main/resources/output/TREN2GTFS.zip with https://code.google.com/p/googletransitdatafeed/wiki/FeedValidator

You can explore the feed on a map using https://code.google.com/p/googletransitdatafeed/wiki/ScheduleViewer

Route Map
=====

You can download a map of the Tren Urbano route area with the following command on Linux/OSX if you have curl:
>curl http://open.mapquestapi.com/xapi/api/0.6/map?bbox=-66.156,18.389,-66.05,18.44 &gt; trenUrbano.osm

Tren Urbano Schedule
=====

The file src/main/resources/input/trainschedule.csv was provided by Víctor Ramirez &lt;victor.ramirez@gmail.com&gt;.  Many thanks
to him for the file and for the other Transit related to Puerto Rico work he is doing.
