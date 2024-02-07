package service;

import entity.Flight;
import service.Filter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterImpl implements Filter {
    private List<Flight> flights;

    public FilterImpl(List<Flight> flights) {
        this.flights = new ArrayList<>(flights);
    }

    @Override
    public List<Flight> build() {
        return flights;
    }

    @Override
    public Filter filterDepartureBeforeNow() {
        flights.removeIf(flight ->
                flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()))
        );
        return this;
    }

    @Override
    public Filter filterArrivalBeforeDeparture() {
        flights.removeIf(flight ->
                flight.getSegments().stream()
                        .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()))
        );
        return this;
    }
}