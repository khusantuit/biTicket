package com.railway.biticket.common;

public interface Message {
    String NAME_CONFLICT_MSG = "Name is already in use";
    String NOT_FOUND_TRAIN = "Requested train is not found";
    String NOT_FOUND_COACH = "Requested coach is not found";
    String NOT_FOUND_SEAT = "Requested seat is not found";
    String NOT_FOUND_TRIP = "Requested trip is not found";
    String NOT_FOUND_ADDRESS = "Requested address is not found";

    String LESS_THAN_TWO_STATIONS = "Less than two stations installed";
    String NOT_FOUND_STATION = "Requested station is not found";
    String SUCCESS = "SUCCESS";

    String CREATED = "CREATE";
}
