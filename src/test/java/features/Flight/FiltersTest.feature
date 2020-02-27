Feature: Use Filters to filter search results
  Background: Open Fly365 site
    Given Navigate to "NZ" Fly365 "stage" site


  Scenario: Verify that user can filter by one stop
    And Select One Way trip
    And Add airport to the Origin "Auckland International (AKL), New Zealand"
    And Add airport to the Destination "Dubai (DXB), United Arab Emirates"
    And Select the date of the departure, after "10" day from today
    And Press on Search Now
    And Press on 'Stops' Filter
    Then Select 'One Stop' trips
    And Assert that search results are only 'One Stop'


  Scenario: Verify that user can filter by price
    And Select One Way trip
    And Add airport to the Origin "Auckland International (AKL), New Zealand"
    And Add airport to the Destination "Dubai (DXB), United Arab Emirates"
    And Select the date of the departure, after "10" day from today
    And Press on Search Now
    And  Press on 'Price' Filter
    Then Slide the slider to the right
    And Assert that search result matches the filtered price


  Scenario: Verify that user can filter by Duration
    And Select One Way trip
    And Add airport to the Origin "Auckland International (AKL), New Zealand"
    And Add airport to the Destination "Dubai (DXB), United Arab Emirates"
    And Select the date of the departure, after "10" day from today
    And Press on Search Now
    And Click on 'Duration' Filter
    Then Slide the slider to the left
    And Assert that search result duration matches the filtered duration

