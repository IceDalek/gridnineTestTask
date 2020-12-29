package com.gridnine.testing;
import java.util.List;
public interface Rule {
        List<Flight> apply(List<Flight> flights);
}
