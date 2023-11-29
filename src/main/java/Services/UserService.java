package Services;

import Models.User.User;
import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired private UserRepository userRepository;

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public User getUserByEmail(String email) {
    User exampleUser = new User();
    exampleUser.setEmail(email);
    Example<User> example = Example.of(exampleUser);
    User user = userRepository.findOne(example).orElse(null);
    return user;
  }
}
