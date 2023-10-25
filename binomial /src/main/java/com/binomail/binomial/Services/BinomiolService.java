package com.binomail.binomial.Services;

import com.binomail.binomial.models.BinomiolItems;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
@Service
public class BinomiolService implements BinomioInterface{

/*
  this is the formula to get probability for binomial distribution
  n!
  _______(p)^k (q)^n-k
  k!(n-k)!

  where (n) stand of the number of trials
  (k) si the interest which means the number of success
  (p) is the probability of the success on a trial
  (q) is the probability of failure which is obtained via the formula 1-p = q

 */




    @Override
    public double getFirstPartOfTheFormular(BinomiolItems binomiolItems) {
        int answerFromFactorial = getFactorialOfNumberOfTails(binomiolItems.getNumberOfTrial());

        int answerOfsub = getFactorialOfAfterSub(binomiolItems);

        double result = (double) answerFromFactorial / answerOfsub;

        return result;
    }

    @Override
    public double getSecondPartOfTheFormular(BinomiolItems binomiolItems) {

        double probabilityOfSuccess = binomiolItems.getProbabilityOfSuccessOnEachTrial();

        double answer = Math.pow(probabilityOfSuccess, binomiolItems.getNumberOfSuccess());
        return answer;
    }

    @Override
    public double getThirdPartOfTheFormular(BinomiolItems binomiolItems) {
          binomiolItems.setProbabilityOfFailure (1-binomiolItems.getProbabilityOfSuccessOnEachTrial());
          double probabilityOfFailure = binomiolItems.getProbabilityOfFailure();
          double resultAfterSubstruction = binomiolItems.getNumberOfTrial() - binomiolItems.getNumberOfSuccess();
          double result_of_power = Math.pow(probabilityOfFailure,resultAfterSubstruction);

    return result_of_power;
    }

    private int getFactorialOfNumberOfTails(int numberOfTails) {
        int answer;
        answer = 0;
        if (numberOfTails == 0 || numberOfTails == 1)
            return 1;
        else {
             answer =  numberOfTails*getFactorialOfNumberOfTails(numberOfTails-1);
        }
        return answer;
    }

    private int getFactorialOfAfterSub(BinomiolItems binomiolItems){
        int numberOfTails = binomiolItems.getNumberOfTrial();
        int numberOfSuccess = (int) binomiolItems.getNumberOfSuccess();
        int number = numberOfTails - numberOfSuccess;
        int answer = 0;

        if (number == 0 || number == 1)
            return 1;
        else{
            answer = number*getFactorialOfNumberOfTails(number-1);
        }
        int nu = numberOfSuccess*getFactorialOfNumberOfTails(numberOfSuccess-1);
        int finalAnswer = answer * nu;
        System.out.println("Number after substracting the K "+ number);
        System.out.println("Number of success "+ numberOfSuccess);
        System.out.println("Number of trials "+ numberOfTails);
        System.out.println("Number of answer "+ answer);
        System.out.println("Number of final answer "+ finalAnswer);

        return finalAnswer;
    }


}

