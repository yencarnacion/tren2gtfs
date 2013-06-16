package tren2gtfs

resourcesFolder = "../resources"
trainScheduleFileName =  "trainschedule.csv"

trainArrivals = readTrainScheduleFile()
readScheduleFromTrainArrivals(trainArrivals)
def readTrainScheduleFile() {

    def trainScheduleFile = new File ("${resourcesFolder}/${trainScheduleFileName}")
    def trainArrivals = []

    trainScheduleFile.eachLine() { line, lineNo ->
        if (lineNo>1) {
            line = line.replace("\"","")
            def( arrivesTo, direction, daytipe, arrivalTime) = line.split(",")
            TrainArrival trainArrival = new TrainArrival(
                    arrivesTo: ((String) arrivesTo).toLowerCase(),
                    direction: ((String) direction).toLowerCase(),
                    daytype: ((String) daytipe).toLowerCase(),
                    arrivalTime: ((String) arrivalTime).toLowerCase()
            )
            trainArrivals << trainArrival
        }
    }

    return trainArrivals

    println "Read ${trainArrivals.size()} schedules"
    /*for (TrainArrival t: trainArrivals){
        println t.dump();
    }*/

}

/**
 * Reads stop times from http://www.dtop.gov.pr/itinerario.asp
 */
def readScheduleFromTrainArrivals(def trainArrivals){

    def stops = (new Utility()).getStops()
    def directions = ['to_bayamon', 'to_sagrado']

    def stopTimes = []
    int i=1;
    for (String direction: directions) {
        for(int startStationId = 1; startStationId <= stops.size(); startStationId++){
            def startStopStation = getStopFromId(stops, startStationId)
            for(int stopStationId = 1; stopStationId <= stops.size(); stopStationId++){
                /*if(!(((direction == 'to_sagrado')&& (startStationId==1) && (stopStationId==16)) ||
                        ((direction == "to_bayamon") && (startStationId==16) && (stopStationId==1)))
                )
                    continue;*/
                if((direction == 'to_sagrado' && (startStationId < stopStationId) ) ||
                        (direction == 'to_bayamon' && (startStationId > stopStationId))
                ){
                    def stopStopStation = getStopFromId(stops, stopStationId)
                    def dayTypes = ["LOW_SEASON_WORKDAY","RESTDAY","WORKDAY"]
                    dayTypes.each({daytype ->
                        def leavingTimes = getStopTrainScheduleTimes(trainArrivals, startStopStation, direction, daytype)
                        leavingTimes.each({ leavingTime ->
                            def arrivalTime = getStopTrainArrivalTime(trainArrivals, startStationId, direction, leavingTime, stopStationId, daytype)
                            println ">>>>>>>"
                            println "[${i++}]-${daytype}-(${direction}) From ${startStopStation.stopName} To ${stopStopStation.stopName}"
                            println "Leaves: ${leavingTime} and Arrives: ${arrivalTime}"
                            println "<<<<<<<"
                        })
                    })
                }
            }
        }
    }
}

def getStopTrainArrivalTime(trainArrivals, int fromStationId, direction, fromTrainTime, int toStationId, daytype){
    def stops = (new Utility()).getStops()

    if(direction=="to_bayamon"){
        assert(fromStationId>toStationId)
        int nextStation = fromStationId-1
        def nextStationSchedule = getStopTrainScheduleTimes(trainArrivals, getStopFromId(stops, nextStation), direction, daytype)
        def nextStationArrival = nextStationSchedule.find({if(it.isAfter(fromTrainTime)) it})
        while(nextStation>toStationId){
            nextStation = nextStation - 1
            nextStationSchedule = getStopTrainScheduleTimes(trainArrivals, getStopFromId(stops, nextStation), direction, daytype)
            nextStationArrival = nextStationSchedule.find({if(it.isAfter(nextStationArrival)) it})
        }
        return nextStationArrival
    }

    if(direction=="to_sagrado"){
        assert (toStationId>fromStationId)
        int nextStation = fromStationId+1
        def nextStationSchedule = getStopTrainScheduleTimes(trainArrivals, getStopFromId(stops, nextStation), direction, daytype)
        def nextStationArrival = nextStationSchedule.find({if(it.isAfter(fromTrainTime)) it})
        while(nextStation<toStationId){
            nextStation = nextStation + 1
            nextStationSchedule = getStopTrainScheduleTimes(trainArrivals, getStopFromId(stops, nextStation), direction, daytype)
            nextStationArrival = nextStationSchedule.find({if(it.isAfter(nextStationArrival)) it})
        }
        return nextStationArrival
    }

    assert false

}

def getStopFromId(stops, stationId) {
    stops.find({stop -> if(stop.stopId == "${stationId}")  stop})
}

def getStopTrainScheduleTimes(trainArrivals, station, direction, daytype){
    def stationCodeName = station.codeName
    assert(stationCodeName!=null)
    trainArrivals.findAll({trainArrival ->
                if(((String) trainArrival.arrivesTo).toUpperCase() == stationCodeName.toUpperCase() &&
                    trainArrival.direction.toUpperCase() == direction.toUpperCase() &&
                    trainArrival.daytype.toUpperCase() == daytype.toUpperCase() )
                        trainArrival
                        }).collect({new TrainLocalTime(it.arrivalTime)}).sort()
}
