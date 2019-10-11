package io.homo_efficio.lmsb.socialmultiplication.multiplication.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-06
 */
@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService {

    final static int MINIMUM_FACTOR = 11;
    final static int MAXIMUM_FACTOR = 99;

    @Override
    public int generateRandomFactor() {
        return new Random().nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1) + MINIMUM_FACTOR;
    }
}
