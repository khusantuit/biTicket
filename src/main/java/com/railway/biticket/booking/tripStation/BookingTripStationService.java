package com.railway.biticket.booking.tripStation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingTripStationService {
    private final BookingTripStationRepository bookingTripStationRepository;
}
