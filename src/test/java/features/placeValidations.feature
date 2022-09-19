Feature: Validating Place API's
@AddPlace @Regression
Scenario Outline: Verify if Place is being added successfully
    Given Add Place Payload "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

Examples:
    |name   |language|address           |
    |AAhouse|English |World cross center| 
#    |BBhouse|Spanish |Sea cross center  | 
@deletePlace @Regression
Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
      