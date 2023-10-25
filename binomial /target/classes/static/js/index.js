function calculateBinomial() {
    // Get values from the form
    var probabilityOfSuccess = parseFloat(document.getElementById('probabilityOfSuccessOnEachTrial').value);
    var numberOfTrials = parseInt(document.getElementById('numberOfTrial').value);
    var numberOfSuccesses = parseInt(document.getElementById('numberOfSuccess').value);

    // Prepare the request body
    var requestBody = {
        probabilityOfSuccessOnEachTrial: probabilityOfSuccess,
        numberOfTrial: numberOfTrials,
        numberOfSuccess: numberOfSuccesses
    };

    // Make the Fetch request
    fetch('/conputebinomial', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody),
    })
        .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
    })
        .then(data => {
        // Handle successful response
        document.getElementById('binomialDistribution').value = data.data.binomial.binomiolDistribution;
        document.getElementById('mean').value = data.data.binomial.mean;
        document.getElementById('variance').value = data.data.binomial.variance;
        document.getElementById('standardDeviation').value = data.data.binomial.standardDeviation;
    })
        .catch(error => {
        // Handle error cases here
        console.error('Error:', error.message);
        // Display an error message on the page or handle it appropriately
    });
}
