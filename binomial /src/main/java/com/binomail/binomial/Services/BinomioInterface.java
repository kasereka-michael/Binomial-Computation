package com.binomail.binomial.Services;

import com.binomail.binomial.models.BinomiolItems;

import java.math.BigDecimal;

public interface BinomioInterface {

    double getFirstPartOfTheFormular(BinomiolItems items);

    double getSecondPartOfTheFormular(BinomiolItems items);

    double getThirdPartOfTheFormular(BinomiolItems items);

}
