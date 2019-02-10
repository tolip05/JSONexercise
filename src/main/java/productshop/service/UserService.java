package productshop.service;

import productshop.domein.entities.User;
import productshop.domein.entities.dtos.UserSeedDto;

import java.util.List;

public interface UserService {

    void seedUsers(UserSeedDto[] userSeedDtos);

    User getRandomUserFromBase();

    List<User> getFriends();
}
