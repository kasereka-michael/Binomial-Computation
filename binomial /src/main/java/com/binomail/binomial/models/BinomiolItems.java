package com.binomail.binomial.models;

import lombok.Data;

@Data
public class BinomiolItems {

    private double probabilityOfSuccessOnEachTrial;
    private double probabilityOfFailure;
    private double numberOfSuccess;
    private int numberOfTrial;
    private String binomiolDistribution;
    private String mean;
    private String variance;
    private String standardDeviation;

}
