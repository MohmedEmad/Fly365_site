Feature: Apply Modify on Bookings

  @apply
  Scenario: test modify
    ##Given Navigate to "NZ" Fly365 "stage" site
    And Book a "one way" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Active" "Refundable" Rule from API for "stage"
    And Modify the booking in store "nz" environment "stage"
