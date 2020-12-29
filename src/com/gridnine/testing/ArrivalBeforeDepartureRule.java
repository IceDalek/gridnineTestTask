package com.gridnine.testing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrivalBeforeDepartureRule implements Rule {
    /**
     * Удаляет из списка перелеты, прибыте которых раньше чем вылет.
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
            for (Segment segment : flight.getSegments()) {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    it.remove();
                    break;
                }
            }
        }
        return flightsToFilter;
    }

}
