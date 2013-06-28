/*
 *
 * Copyright (c) 2013, Yamir Encarnación <yencarnacion@webninjapr.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package tren2gtfs
/**
 * Created by yamir on 6/6/13.
 */

import java.util.zip.ZipOutputStream
import java.util.zip.ZipEntry
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Period
import org.joda.time.format.PeriodFormatterBuilder
import org.joda.time.format.PeriodFormatter

 String resourcesFolder = project.properties['ressourceDirectoryLocation']

 agency_timezone = 'America/Puerto_Rico'

 startTime = new DateTime(DateTimeZone.forID(agency_timezone))

 println "Using resource folder: ${resourcesFolder}"

trainScheduleFileName =  "trainschedule.csv"

 zipFileName = "TREN2GTFS.zip"
 agency_id = 'TREN'
 agency_name = 'Tren Urbano'
 agency_url = 'http://www.dtop.gov.pr'

 agency_lang = null
 agency_phone = null
 agency_fare_url = null


class StopCollection {
    private static def stops = []

    static def getTheStops(){
        if(stops.size()==0){
            populateStops()
        }
        return stops
    }

    static def getStopFromId(stationId) {
        stops.find({stop -> if(stop.stopId == "${stationId}")  stop})
    }

    private static def populateStops(){
        def sagrado_corazon = new Stop(
                codeName: "sagrado_corazon",
                stopId: "16",
                stopCode: "",
                stopName: "Sagrado Coraz\u00F3n",
                stopDesc: "",
                stopLon:"-66.05968",
                stopLat: "18.437642",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def hato_rey = new Stop(
                codeName: "hato_rey",
                stopId: "15",
                stopCode: "",
                stopName: "Hato Rey",
                stopDesc: "",
                stopLon:"-66.059985",
                stopLat: "18.430277",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def roosevelt = new Stop(
                codeName: "roosevelt",
                stopId: "14",
                stopCode: "",
                stopName: "Roosevelt",
                stopDesc: "",
                stopLon:"-66.058779",
                stopLat: "18.424528",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def domenech = new Stop(
                codeName: "domenech",
                stopId: "13",
                stopCode: "",
                stopName: "D\u00F3menech",
                stopDesc: "",
                stopLon:"-66.056338",
                stopLat: "18.416346",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def pinero = new Stop(
                codeName: "pinero",
                stopId: "12",
                stopCode: "",
                stopName: "Pi\u00F1ero",
                stopDesc: "",
                stopLon:"-66.055225",
                stopLat: "18.411693",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def universidad = new Stop(
                codeName: "universidad",
                stopId: "11",
                stopCode: "",
                stopName: "Universidad",
                stopDesc: "",
                stopLon:"-66.05193",
                stopLat: "18.406024",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def rio_piedras = new Stop (
                codeName: "rio_piedras",
                stopId: "10",
                stopCode: "",
                stopName: "R\u00EDo Piedras",
                stopDesc: "",
                stopLon:"-66.052413",
                stopLat: "18.400251",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )
        def cupey = new Stop (
                codeName: "cupey",
                stopId: "9",
                stopCode: "",
                stopName: "Cupey",
                stopDesc: "",
                stopLon:"-66.063209",
                stopLat: "18.392124",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def centro_medico = new Stop (
                codeName: "centro_medico",
                stopId: "8",
                stopCode: "",
                stopName: "Centro M\u00E9dico",
                stopDesc: "",
                stopLon:"-66.075734",
                stopLat: "18.393167",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def san_francisco = new Stop (
                codeName: "san_francisco",
                stopId: "7",
                stopCode: "",
                stopName: "San Francisco",
                stopDesc: "",
                stopLon:"-66.084036",
                stopLat: "18.392307",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def las_lomas = new Stop (
                codeName: "las_lomas",
                stopId: "6",
                stopCode: "",
                stopName: "Las Lomas",
                stopDesc: "",
                stopLon:"-66.095176",
                stopLat: "18.392398",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def martinez_nadal = new Stop (
                codeName: "martinez_nadal",
                stopId: "5",
                stopCode: "",
                stopName: "Mart\u00EDnez Nadal",
                stopDesc: "",
                stopLon:"-66.102911",
                stopLat: "18.392811",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def torrimar = new Stop (
                codeName: "torrimar",
                stopId: "4",
                stopCode: "",
                stopName: "Torrimar",
                stopDesc: "",
                stopLon:"-66.120175",
                stopLat: "18.393597",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )


        def jardines = new Stop (
                codeName: "jardines",
                stopId: "3",
                stopCode: "",
                stopName: "Jardines",
                stopDesc: "",
                stopLon:"-66.128919",
                stopLat: "18.395734",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def deportivo = new Stop (
                codeName: "deportivo",
                stopId: "2",
                stopCode: "",
                stopName: "Deportivo",
                stopDesc: "",
                stopLon:"-66.151249",
                stopLat: "18.395922",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def bayamon = new Stop (
                codeName: "bayamon",
                stopId: "1",
                stopCode: "",
                stopName: "Bayam\u00F3n",
                stopDesc: "",
                stopLon:"-66.153815",
                stopLat: "18.40095",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )


        stops << sagrado_corazon
        stops << hato_rey
        stops << roosevelt
        stops << domenech
        stops << pinero
        stops << universidad
        stops << rio_piedras
        stops << cupey
        stops << centro_medico
        stops << san_francisco
        stops << las_lomas
        stops << martinez_nadal
        stops << torrimar
        stops << jardines
        stops << deportivo
        stops << bayamon

    }

}

routeTrenUrbano = new Route (
    routeId: "RUTA_TREN",
    agencyId: agency_id,
    routeShortName: "",
    routeLongName: "Ruta del Tren Urbano",
    routeDesc: "Ruta del Tren Urbano de Puerto Rico",
    routeType: "1",
    routeUrl: "http://www.dtop.gov.pr",
    routecolor: "",
    routeTextColor: ""
)

debug = true

calendar = []

low_season_workday = new CalendarItem(
         serviceId: "LOW_SEASON_WORKDAY",
         monday: 1,
         tuesday: 1,
         wednesday: 1,
         thursday: 1,
         friday: 1,
         saturday: 0,
         sunday: 0,
         startDate: '20130601',
         endDate: '20130731'
)

calendar << low_season_workday

restday = new CalendarItem(
        serviceId: "RESTDAY",
        monday: 0,
        tuesday: 0,
        wednesday: 0,
        thursday: 0,
        friday: 0,
        saturday: 1,
        sunday: 1,
        startDate: '20130101',
        endDate: '20131231'
)

calendar << restday

workday = new CalendarItem(
        serviceId: "WORKDAY",
        monday: 1,
        tuesday: 1,
        wednesday: 1,
        thursday: 1,
        friday: 1,
        saturday: 0,
        sunday: 0,
        startDate: '20130801',
        endDate: '20131231'
)

calendar << workday

/******/

TrainGlobals.globals['debug'] = debug
timezone = agency_timezone

routes = []
routes << routeTrenUrbano

 String agencyFileName = "agency.txt"
 String stopsFileName = "stops.txt"
 String routesFileName = "routes.txt"
 String tripsFileName = "trips.txt"
 String stopTimesFileName = "stop_times.txt"
 String calendarFileName = "calendar.txt"

 tren2gtfsFiles = [
         agencyFileName,
         stopsFileName,
         routesFileName,
         tripsFileName,
         stopTimesFileName,
         calendarFileName
      ]

agency = new Agency(agencyId: agency_id,
                    agencyName: agency_name,
                    agencyUrl: agency_url,
                    agencyTimezone: agency_timezone,
                    agencyLang: agency_lang,
                    agencyPhone: agency_phone,
                    agencyFareUrl: agency_fare_url)
createAgencyTxt(resourcesFolder, agencyFileName, agency)
stopCollection = new StopCollection()
createStopsTxt(resourcesFolder, stopsFileName, stopCollection.getTheStops())
createRoutesTxt(resourcesFolder, routesFileName, routes)

def retval = new TrainSchedule("${resourcesFolder}/input/${trainScheduleFileName}", routes, stopCollection, timezone).getStopTimesAndTripsFromTrainArrivals()

println "Creating ${stopTimesFileName}"
createStopTimesTxt("${resourcesFolder}/input/${stopTimesFileName}", retval.stopTimes)

def trips = new Trips("${resourcesFolder}/output/${tripsFileName}", retval.trips)

println "Creating ${tripsFileName}"
trips.createTripsTxt()

createCalendarTxt("${resourcesFolder}/output/${calendarFileName}", calendar)
createZipFile(resourcesFolder, zipFileName)

def createAgencyTxt(String resourcesFolder, String agencyFileName,  def agency){
    def agencyTxt = new File("${resourcesFolder}/output/${agencyFileName}")
    agencyTxt.newWriter()
    agencyTxt << ("agency_id,agency_name,agency_url,agency_timezone,agency_lang,agency_phone,agency_fare_url") << "\r\n"
    agencyTxt << (agency.agencyId?agency.agencyId+",":",")
    assert(agency.agencyName)
    agencyTxt << (agency.agencyName?agency.agencyName+",":",")
    assert(agency.agencyUrl)
    agencyTxt << (agency.agencyUrl?agency.agencyUrl+",":",")
    assert(agency.agencyTimezone)
    agencyTxt << (agency.agencyTimezone?agency.agencyTimezone+",":",")
    agencyTxt << (agency.agencyLang?agency.agencyLanga+",":",")
    agencyTxt << (agency.agencyPhone?agency.agencyPhone+",":",")
    agencyTxt << (agency.agencyFareUrl?agency.agencyFareUrl:"") << "\r\n"

}

def createStopsTxt(String resourcesFolder,  String stopsFileName, def stops){
    def stopsTxt = new File("${resourcesFolder}/output/${stopsFileName}")
    stopsTxt.newWriter()
    stopsTxt << ("stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,location_type,parent_station,stop_timezone,wheelchair_boarding") << "\r\n"
    stops.each { stop ->
        stopsTxt << (stop.stopId?stop.stopId+",":",")
        stopsTxt << (stop.stopCode?stop.stopCode+",":",")
        stopsTxt << (stop.stopName?stop.stopName+",":",")
        stopsTxt << (stop.stopDesc?stop.stopDesc+",":",")
        stopsTxt << (stop.stopLat?stop.stopLat+",":",")
        stopsTxt << (stop.stopLon?stop.stopLon+",":",")
        stopsTxt << (stop.zoneId?stop.zoneId+",":",")
        stopsTxt << (stop.stopUrl?stop.stopUrl+",":",")
        stopsTxt << (stop.locationType?stop.locationType+",":",")
        stopsTxt << (stop.parentStation?stop.parentStation+",":",")
        stopsTxt << (stop.stopTimezone?stop.stopTimezone+",":",")
        stopsTxt << (stop.wheelchairBoarding?stop.wheelchairBoarding:"") << "\r\n"
    }

}

def createRoutesTxt(String resourcesFolder, String routesFileName, def routes){
    def routesTxt = new File ("${resourcesFolder}/output/${routesFileName}")
    routesTxt.newWriter()
    routesTxt << ("route_id,agency_id,route_short_name,route_long_name,route_desc,route_type,route_url,route_color,route_text_color") << "\r\n"
    routes.each { route ->
        routesTxt << (route.routeId?route.routeId+",":",")
        routesTxt << (route.agencyId?route.agencyId+",":",")
        routesTxt << (route.routeShortName?route.routeShortName+",":",")
        routesTxt << (route.routeLongName?route.routeLongName+",":",")
        routesTxt << (route.routeDesc?route.routeDesc+",":",")
        routesTxt << (route.routeType?route.routeType+",":",")
        routesTxt << (route.routeUrl?route.routeUrl+",":",")
        routesTxt << (route.routecolor?route.routecolor+",":",")
        routesTxt << (route.routeTextColor?route.routeTextColor:"") << "\r\n"
    }
}

def createStopTimesTxt(stopTimesFile, stopTimes){
    def stopTimesTxt = new File("${stopTimesFile}")
    stopTimesTxt.newWriter()
    stopTimesTxt << ("trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type,shape_dist_traveled") << "\r\n"
    stopTimes.each { StopTime stopTime ->
        stopTimesTxt << (stopTime.tripId?stopTime.tripId+",":",")
        stopTimesTxt << (stopTime.arrivalTime?stopTime.arrivalTime+",":",")
        stopTimesTxt << (stopTime.departureTime?stopTime.departureTime+",":",")
        stopTimesTxt << (stopTime.stopId?stopTime.stopId+",":",")
        stopTimesTxt << (stopTime.stopSequence?stopTime.stopSequence+",":",")
        stopTimesTxt << (",,,") << '\r\n'
    }
}




def createCalendarTxt(String calendarFile, def calendar){
    def calendarTxt = new File("${calendarFile}")
    calendarTxt.newWriter()
    calendarTxt << ("service_id,monday,tuesday,wednesday,thursday,friday,saturday,sunday,start_date,end_date") << "\r\n"
    calendar.each { CalendarItem calendarItem ->
        calendarTxt << (calendarItem.serviceId?calendarItem.serviceId+",":",")
        calendarTxt << (calendarItem.monday?calendarItem.monday+",":",")
        calendarTxt << (calendarItem.tuesday?calendarItem.tuesday+",":",")
        calendarTxt << (calendarItem.wednesday?calendarItem.wednesday+",":",")
        calendarTxt << (calendarItem.thursday?calendarItem.thursday+",":",")
        calendarTxt << (calendarItem.friday?calendarItem.friday+",":",")
        calendarTxt << (calendarItem.saturday?calendarItem.saturday+",":",")
        calendarTxt << (calendarItem.sunday?calendarItem.sunday+",":",")
        calendarTxt << (calendarItem.startDate?calendarItem.startDate+",":",")
        calendarTxt << (calendarItem.endDate?calendarItem.endDate:"") << "\r\n"
    }

}

def createZipFile(String resourcesFolder, String zipFileName){
    ZipOutputStream zipFile = new ZipOutputStream(new FileOutputStream("${resourcesFolder}/output/${zipFileName}"))
    tren2gtfsFiles.each() { fileName ->
        zipFile.putNextEntry(new ZipEntry(fileName))
        def file = new File("${resourcesFolder}/output/${fileName}")
        file.withInputStream { i ->
            zipFile << i
        }
        zipFile.closeEntry()
    }
    zipFile.close()
}

stopTime = new DateTime(DateTimeZone.forID(agency_timezone))
Period period = new Period(startTime, stopTime);
println "-----------------------------------"
PeriodFormatter formatter = new PeriodFormatterBuilder()
        .appendSeconds().appendSuffix(" seconds\n")
        .appendMinutes().appendSuffix(" minutes\n")
        .appendHours().appendSuffix(" hours\n")
        .appendDays().appendSuffix(" days\n")
        .appendWeeks().appendSuffix(" weeks\n")
        .appendMonths().appendSuffix(" months\n")
        .appendYears().appendSuffix(" years\n")
        .printZeroNever()
        .toFormatter();

String elapsed = formatter.print(period);
println "It took:\n"+elapsed+"To create ${zipFileName}"
println "-----------------------------------"








