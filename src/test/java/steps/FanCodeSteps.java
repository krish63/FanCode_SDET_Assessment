package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import models.ToDosModel;
import models.users.UsersModel;

import java.util.List;
import java.util.Set;


public class FanCodeSteps extends BaseSteps {

    private static ToDosModel[] toDosModel;
    private static Set<Integer> fanCodeUsers;
    @Given("User has the todo tasks")
    public void userHasToDosTasks() {
        logger.info("Fetching ToDo list");
        toDosModel = fetchToDos().as(ToDosModel[].class);
    }

    @And("User belongs to the city FanCode")
    public void userBelongsToFanCode() {
        logger.info("Fetching Users list");
        UsersModel[] usersModel = typiCodeConnector.getUsers().as(UsersModel[].class);
        logger.info("Filtering 'FanCode' users");
        fanCodeUsers = getFanCodeUsers(usersModel);
    }

    @Then("User Completed task percentage should be greater than {}%")
    public void assertPercentageIsGreaterThanX(int percentage) {
        logger.info("Filtering ToDos for 'FanCode' users");
        List<ToDosModel> toDosTasks = filterToDosForFanCodeUsers(toDosModel, fanCodeUsers);
        toDosTasks.stream().map(ToDosModel::userId).distinct().forEach(user -> {
            int total = (int) toDosTasks.stream().filter(t -> t.userId()==user).count();
            int completed = (int) toDosTasks.stream().filter(t -> t.userId()==user && t.completed()).count();
            double actualPercentage = ((double) completed / total) * 100;
            softAssertions.assertThat(actualPercentage)
                    .withFailMessage("User " + user + " failed with completed " + completed + " out of " + total)
                    .isGreaterThan(percentage);
        });
        softAssertions.assertAll();
    }
}
