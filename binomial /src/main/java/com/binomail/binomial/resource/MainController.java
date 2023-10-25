package com.binomail.binomial.resource;

import com.binomail.binomial.Services.BinomiolService;
import com.binomail.binomial.models.BinomiolItems;
import com.binomail.binomial.models.HttpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final BinomiolService binomiolService;
    @PostMapping("/conputebinomial")
    public ResponseEntity<HttpResponse> ComputeBinomial(@RequestBody BinomiolItems binomiolItems) throws InterruptedException {

        BinomiolItems binomiolItemss = computeBinomialValues(binomiolItems);

        System.out.println("Mean: " + binomiolItemss.getMean());
        System.out.println("Variance: " + binomiolItemss.getVariance());
        System.out.println("Standard Deviation: " + binomiolItemss.getNumberOfSuccess());

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timesStamp(now().toString())
                        .message("binomial computed successfully")
                        .status(OK)
                        .data(Map.of("binomial",binomiolItemss))
                        .statusCode(OK.value())
                        .build());
    }




    private BinomiolItems computeBinomialValues(BinomiolItems binomiolItems) {

        double firstPart = binomiolService.getFirstPartOfTheFormular(binomiolItems);
        double secondPart = binomiolService.getSecondPartOfTheFormular(binomiolItems);
        double thirdPart = binomiolService.getThirdPartOfTheFormular(binomiolItems);
        double probOfFailure = 1 - binomiolItems.getProbabilityOfSuccessOnEachTrial();

        double Answer = secondPart * thirdPart;

        double mean = binomiolItems.getNumberOfTrial() * binomiolItems.getProbabilityOfSuccessOnEachTrial();

        double variance = binomiolItems.getNumberOfTrial() * binomiolItems.getProbabilityOfSuccessOnEachTrial() * probOfFailure;

        double standardDeviation = Math.sqrt(variance);

        String binomiolDistribution = String.format("%.5f",Answer*firstPart);
        String meanString = String.format("%.2f", mean);
        String varianceString = String.format("%.3f", variance);
        String standardDeviationString = String.format("%.4f", standardDeviation);

        BinomiolItems binomiolItem = new BinomiolItems();

        binomiolItem.setBinomiolDistribution(binomiolDistribution);
        binomiolItem.setMean(meanString);
        binomiolItem.setVariance(varianceString);
        binomiolItem.setStandardDeviation(standardDeviationString);


        return binomiolItem;
    }


}

