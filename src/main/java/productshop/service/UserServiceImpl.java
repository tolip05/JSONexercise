package productshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productshop.domein.entities.User;
import productshop.domein.entities.dtos.UserSeedDto;
import productshop.repositoris.UserRepository;
import productshop.util.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {
        for (UserSeedDto userSeedDto : userSeedDtos) {
            if (!this.validatorUtil.isValid(userSeedDto)){
                this.validatorUtil.violations(userSeedDto)
                        .forEach(violation -> System.out.println(violation.getMessage()) );
               continue;
            }
            User user = this.modelMapper.map(userSeedDto,User.class);
            if (this.userRepository.count() > 2){
                user.setFriends(this.getFriends());
            }

            this.userRepository.saveAndFlush(user);
        }
    }

    @Override
    public User getRandomUserFromBase() {
        Random random = new Random();
        return this.userRepository
                .getOne(random.nextInt((int) this.userRepository.count() -1) + 1);
    }

    @Override
    public List<User> getFriends() {
        List<User>users = new ArrayList<>();
        Random random = new Random();
        int countOfArray = random.nextInt((int) this.userRepository.count() - 1) + 1;

        for (int i = 0; i < countOfArray; i++) {
            users.add(this.getRandomUserFromBase());
        }
        return users;
    }


}
