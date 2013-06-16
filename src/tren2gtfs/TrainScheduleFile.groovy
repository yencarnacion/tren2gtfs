package tren2gtfs

resourcesFolder = "../resources"
trainScheduleFileName =  "trainschedule.csv"

TrainSchedule ts = new TrainSchedule("${resourcesFolder}/${trainScheduleFileName}")
ts.readScheduleFromTrainArrivals()


