package io.homo_efficio.lmsb.socialmultiplication.multiplication.service;

/**
 * @author homo.efficio@gmail.com
 * created on 2019-10-06
 */
public interface RandomGeneratorService {

    /**
     *
     * @return a randomly generated factor. It's always a number between 11 and 99.
     */
    int generateRandomFactor();
}
