Feature: Apply Cancel on Bookings

  Scenario: Verify that customer can cancel booking when rule matches and successful message is displayed to customer
    Given Navigate to "NZ" Fly365 "stage" site
    #Given Delete All Rules
    And Search for trip using API
      | departures |  | arrivals |  | depDatesAfter |  | Adults |  | Infants |  | Child |  | CabinClass |
      | CAI        |  | JED      |  | 10            |  | 1      |  | 0       |  | 0     |  | economy    |

    And Choose trip number "2" and create cart
    And Add passengers with this data
      | Birthdates        | 1991-03-03                  |
      | PassengerTypes    | ADT                         |
      | Titles            | Mr                          |
      | FirstNames        | Hassan                      |
      | LastNames         | Sayed                       |
      | PassportNumber    | 1234567                     |
      | PassportExpiry    | 2024-03-03                  |
      | PassportCountry   | Eg                          |
      | FrequentFlyer     | 321321                      |
      | Seats             | , ,                         |
      | Meals             | WINDOW                      |
      | SpecialAssist     | VVVVV                       |
      | CustomerTitle     | Mr                          |
      | CustomerFirstName | Khaled                      |
      | CustomerLastName  | Aziz                        |
      | PhoneNumber       | 20100000001                 |
      | Email             | john.smith.fly365@gmail.com |
      | SpecialRequest    | Test Request                |

    And Checkout and get booking details
      |cardHolderName   | |cardExpiryDate  | |cardNumber         | |cvv    |
      |John Smith       | |0522            | |5123450000000008   | |123    |
    And Create "Active" "Refundable" Rule from API for "stage" matched with booking
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    Then Request Is sent Successfully
    And Delete Created Rule From Database

  Scenario: Verify that status of canceled booking is to be refunded to the customer
    Given Navigate to "NZ" Fly365 "stage" site
    #Given Delete All Rules
    And Book a "round trip" trip from API for "stage" and get "order"
    And Create "Active" "Refundable" Rule from API for "stage" matched with booking
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    Then Booking Status Will Be To Be Refunded
    And Delete Created Rule From Database

  Scenario: Verify that customer can't cancel refundable flight canceled before
    Given Navigate to "NZ" Fly365 "stage" site
    #Given Delete All Rules
    And Book a "one way" trip from API for "stage" and get "order"
    And Create "Active" "Refundable" Rule from API for "stage" matched with booking
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    Then Request Is sent Successfully
    And Click on Manage My Booking
    Then Cancel My Booking is not Clickable
    And Delete Created Rule From Database

  Scenario: Verify that canceled booking status in hub is to be refunded
    Given Navigate to "NZ" Fly365 "stage" site
    #Given Delete All Rules
    And Book a "round trip" trip from API for "stage" and get "order"
    And Create "Active" "Refundable" Rule from API for "stage" matched with booking
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    When Open hub login page
    And  login into hub with super admin
    And  Open menu
    And  Open  "BackOffice"
    And Open Orders
    And Search for Order Number from Advanced Search
    Then Order Will Have To be Refunded status
    And Delete Created Rule From Database

  Scenario: Verify that canceled booking status for order details in hub is to be refunded
    Given Navigate to "NZ" Fly365 "stage" site
    #Given Delete All Rules
    And Book a "round trip" trip from API for "stage" and get "order"
    And Create "Active" "Refundable" Rule from API for "stage" matched with booking
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Get Fly Reference
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    When Open hub login page
    And  login into hub with super admin
    And  Open menu
    And  Open  "BackOffice"
    And Open Orders
    And Search for booking returned in "booking pnr response" Quick Search
    Then Order Details Will Have To be Refunded status
    And Delete Created Rule From Database

  Scenario: Verify that customer can cancel one way trip when rule matches
    Given Navigate to "NZ" Fly365 "stage" site
    #Given Delete All Rules
    And Book a "one way" trip from API for "stage" and get "order"
    And Create "Active" "Refundable" Rule from API for "stage" matched with booking
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    Then Booking Status Will Be To Be Refunded
    And Delete Created Rule From Database

  Scenario: Verify that customer can cancel round trip when rule matches
    Given Navigate to "NZ" Fly365 "stage" site
    #Given Delete All Rules
    And Book a "round trip" trip from API for "stage" and get "order"
    And Create "Active" "Refundable" Rule from API for "stage" matched with booking
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    Then Booking Status Will Be To Be Refunded
    And Delete Created Rule From Database

  Scenario: Verify that customer can cancel multi city trip when rule matches
    Given Navigate to "NZ" Fly365 "stage" site
    #Given Delete All Rules
    And Book a "multi city" trip from API for "stage" and get "order"
    And Create "Active" "Refundable" Rule from API for "stage" matched with booking
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    Then Booking Status Will Be To Be Refunded
    And Delete Created Rule From Database


  Scenario: Verify that rule is not applied if it isn't active
    Given Navigate to "NZ" Fly365 "stage" site
    #Given Delete All Rules
    And Book a "round trip" trip from API for "stage" and get "order"
    And Create "Inactive" "Refundable" Rule from API for "stage" matched with booking
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Click Cancel Booking
    And Enter request comment
    Then Booking Status Will still confirmed
    And Delete Created Rule From Database

  Scenario: Test booking cycle through api
    And Search for trip using API
      | departures |  | arrivals |  | depDatesAfter |  | Adults |  | Infants |  | Child |  | CabinClass |
      | CAI,DXB    |  | DXB,CAI  |  | 10,15         |  | 1      |  | 1       |  | 1     |  | economy    |

    And Choose trip number "2" and create cart
    #And Add passengers
      #|Birthdates           | |PassengerTypes  | |Titles   | |FirstNames      | |LastNames      | |PassportNumber      | |PassportExpiry           | |PassportCountry| |FrequentFlyer   | |Seats         | |Meals             |         |SpecialAssist| |CustomerTitle| |CustomerFirstName| |CustomerLastName| |PhoneNumber| |Email|
      #|1991-03-03,2015-09-08| |ADT,CNN         | |Mr,Mr    | |Hassan,Hamed    | |Sayed,Ragab    | |1234567,45456       | |2024-03-03,2022-03-03    | |Eg,AE          | |  ,45365        | |VOML,VOML     | |WINDOW,WINDOW     |         |WCHC,WCHC        | |Mr           | |Khaled           | |Aziz            | |20100000001| |khaled.abdelaziz@fly365.com|

    And Add passengers with this data
      | Birthdates        | 1991-03-03,2019-03-03,2014-04-03 |
      | PassengerTypes    | ADT,INF,CNN                      |
      | Titles            | Mr,Mr,Ms                         |
      | FirstNames        | Hassan,Tarek,Salma               |
      | LastNames         | Sayed,Aziz,Ramy                  |
      | PassportNumber    | 1234567,4324234,3123213          |
      | PassportExpiry    | 2024-03-03,2021-01-01,2023-09-08 |
      | PassportCountry   | Eg,AE,AE                         |
      | FrequentFlyer     | 321321,321321,321321             |
      | Seats             | VOML,VOML,VOML                   |
      | Meals             | WINDOW,WINDOW,WINDOW             |
      | SpecialAssist     | , , ,                            |
      | CustomerTitle     | Mr                               |
      | CustomerFirstName | Khaled                           |
      | CustomerLastName  | Aziz                             |
      | PhoneNumber       | 20100000001                      |
      | Email             | john.smith.fly365@gmail.com      |
      | SpecialRequest    | Test Request                     |

    And Checkout and get booking details
      |cardHolderName   | |cardExpiryDate  | |cardNumber         | |cvv    |
      |John Smith       | |0522            | |5123450000000008   | |123    |

