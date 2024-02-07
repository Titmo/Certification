package service;

import entity.Flight;

import java.util.List;

public interface Filter {
    List<Flight> build();

    Filter filterDepartureBeforeNow();

    Filter filterArrivalBeforeDeparture();
    Filter filterSumTimeOnGroundMoreThanTwoHours();
}
