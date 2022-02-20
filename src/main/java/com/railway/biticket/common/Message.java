package com.railway.biticket.common;

public interface Message {
    String NAME_CONFLICT_MSG = "name is already in use";
    String NOT_FOUND_TRAIN = "Requested train is not found";
    String NOT_FOUND_COACH = "Requested coach is not found";
    String NOT_FOUND_SEAT = "Requested seat is not found";

    String CREATED = "CREATE";
}
