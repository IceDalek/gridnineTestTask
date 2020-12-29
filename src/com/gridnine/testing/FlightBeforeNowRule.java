package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlightBeforeNowRule implements Rule {


    /**
     * Удаляет из списка перелеты, вылет которых раньше текущей даты.
     * Метод не изменяет список, переданный в качестве аргумента.
     *
     * @param flights Список из которого удалять
     *
     * @return Копию списка с отфильтрованными значениями.
     *
     */
    @Override
    public List<Flight> apply(List<Flight> flights) {

        ArrayList<Flight> flightsToFilter = new ArrayList<>(flights);
        LocalDateTime localDateTime = LocalDateTime.now();
        Iterator<Flight> it = flightsToFilter.iterator();
        while (it.hasNext()) {
            Flight flight = it.next();
            Segment departure = flight.getSegments().get(0);
            if (!localDateTime.isBefore(departure.getDepartureDate())) {
                it.remove();
            }
        }
        return flightsToFilter;
    }

}
