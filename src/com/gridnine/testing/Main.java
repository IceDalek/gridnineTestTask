package com.gridnine.testing;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        ArrayList<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        ArrayList<Flight> flightsAfterNow = new ArrayList<>(new FlightBeforeNowRule().apply(flights));
        ArrayList<Flight> flightsWithCorrectTime = new ArrayList<>(new ArrivalBeforeDepartureRule().apply(flights));
        ArrayList<Flight> flightsLessThanTwoHoursOnGround = new ArrayList<>(new MoreThanTwoHoursGroundRule().apply(flights));

        System.out.println("Вывод полетов с вылетом после текущего момента времени:\n\n");
        for (Flight flight : flightsAfterNow) {
            System.out.println(flight);
        }

        System.out.println("\nВывод полетов с датой прилета позже даты вылета:\n\n");

        for (Flight flight : flightsWithCorrectTime) {
            System.out.println(flight);
        }

        System.out.println("\nВывод полетов общее время на земле которых не превышает два часа:\n\n");
        for (Flight flight : flightsLessThanTwoHoursOnGround) {
            System.out.println(flight);
        }

    }
}
