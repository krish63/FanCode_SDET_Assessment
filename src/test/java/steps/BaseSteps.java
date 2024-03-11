package steps;


import connectors.TypiCodeConnector;
import io.restassured.response.Response;
import models.ToDosModel;
import models.users.UsersModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BaseSteps {
    protected static final Logger logger = LogManager.getLogger(BaseSteps.class);
    protected static final TypiCodeConnector typiCodeConnector = new TypiCodeConnector();
    protected static final SoftAssertions softAssertions = new SoftAssertions();

    protected Response fetchToDos() {
        return typiCodeConnector.getToDos();
    }
    @NotNull
    protected static List<ToDosModel> filterToDosForFanCodeUsers(ToDosModel[] todOsModel, Set<Integer> fanCodeUsers) {
        return Arrays.stream(todOsModel).filter(tasks -> fanCodeUsers.contains(tasks.userId())).toList();
    }

    @NotNull
    protected static Set<Integer> getFanCodeUsers(UsersModel[] usersModel) {
        return Arrays.stream(usersModel)
                .filter(user -> Double.parseDouble(user.address().geo().lat()) >= -40 && Double.parseDouble(user.address().geo().lat()) <= 5)
                .filter(user -> Double.parseDouble(user.address().geo().lng()) >= 5 && Double.parseDouble(user.address().geo().lng()) <= 100)
                .map(UsersModel::id).collect(Collectors.toSet());
    }
}
