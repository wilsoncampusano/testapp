Feature: Login 

  Scenario: Succes login 
     Given I open the login page
     When I input my user name and password with "kensuka" and "123456"
     Then I should go to the home page by clicking the login button
     And I should see the message "Welcome !" from the welcome page
     
