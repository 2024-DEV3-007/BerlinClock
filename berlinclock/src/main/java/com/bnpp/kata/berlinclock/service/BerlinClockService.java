package com.bnpp.kata.berlinclock.service;

import org.springframework.stereotype.Service;

@Service
public class BerlinClockService {
	
    public String convertToBerlinTime(String time) {
        return (Integer.parseInt(time) % 2 == 0) ? "Y" : "O";
    }
}
