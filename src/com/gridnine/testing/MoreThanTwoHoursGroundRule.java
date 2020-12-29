package com.gridnine.testing;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MoreThanTwoHoursGroundRule implements Rule {
    /**
     * Удаляет из списка перелеты, которые ожидают более двух часов на земле.
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

    Iterator<Flight> it = flightsToFilter.iterator();
        while (it.hasNext()) {
            Flight flight = it.next();
            long timeOnGround = 0L;
            ArrayList<Segment> segments = new ArrayList<>(flight.getSegments());
            for (int i = 0; i <  segments.size() - 1; i++) {
                Segment leftSegment = segments.get(i);
                Segment rightSegment = segments.get(i + 1);
                Duration duration = Duration.between(leftSegment.getArrivalDate(),
                        rightSegment.getDepartureDate());
                timeOnGround += duration.toHours();
            }
            if (timeOnGround > 2) {
                it.remove();
            }
        }
        return flightsToFilter;
    }
}
