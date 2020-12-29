package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;


public class FlightsTest {
    private ArrayList<Flight> flights
            = new ArrayList<>(FlightBuilder.createFlights());
    private ArrayList<Flight> flightsAfterNow
            = new ArrayList<>(new FlightBeforeNowRule().apply(flights));
    private ArrayList<Flight> flightsWithCorrectTime
            = new ArrayList<>(new ArrivalBeforeDepartureRule().apply(flights));
    private ArrayList<Flight> flightsLessThanTwoHoursOnGround
            = new ArrayList<>(new MoreThanTwoHoursGroundRule().apply(flights));


    @Test
    public void flights_AFTER_NOW() {
        for (Flight flight : flightsAfterNow) {
            for (Segment segment : flight.getSegments()) {
                if (segment.getDepartureDate().isBefore(LocalDateTime.now())) {
                    Assert.fail("вылет должен быть до текущего момента времени");
                }
            }
        }
    }
    @Test
    public void flights_DEPATURE_BEFORE_ARRIVAL() {
        for (Flight flight : flightsWithCorrectTime) {
            for (Segment segment : flight.getSegments())
            {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    Assert.fail("Прибытие не может быть раньше вылета");
                }
                if (segment.getDepartureDate().isEqual(segment.getArrivalDate())) {
                    Assert.fail("Прибытие не может быть одновременнов с вылетом");
                }
            }
        }
    }
    @Test
    public void flights_MORE_THAN_2_HOURS() {
        for (Flight flight : flightsLessThanTwoHoursOnGround) {
            ArrayList<Segment> segments = new ArrayList<>(flight.getSegments());
            long timeOnGround = 0L;
            for (int i = 0; i < segments.size() - 1; i++) {
                Duration duration = Duration.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate());
                timeOnGround += duration.toHours();
            }
            if (timeOnGround > 2) {
                Assert.fail("Рейс не должен ожидать больше 2 часов");
            }
        }
    }

}
