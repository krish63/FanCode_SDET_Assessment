@test

  Feature: Test ToDos

    @fetchToDos
    Scenario: All the users of City `FanCode` should have more than half of their todos task completed
      Given User has the todo tasks
      And User belongs to the city FanCode
      Then User Completed task percentage should be greater than 50%

    @fetchToDos
    Scenario: Some the users of City `FanCode` should have more than half of their todos task completed
      Given User has the todo tasks
      And User belongs to the city FanCode
      Then User Completed task percentage should be greater than 60%