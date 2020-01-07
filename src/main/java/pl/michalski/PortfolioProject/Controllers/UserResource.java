package pl.michalski.PortfolioProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.michalski.PortfolioProject.Entities.User;
import pl.michalski.PortfolioProject.Exceptions.UserNotFoundException;
import pl.michalski.PortfolioProject.Repositories.UserRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> showAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/findone/{id}")
    public User showUserbyId(@PathVariable long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
                if(!user.isPresent()) throw new UserNotFoundException("id -" + id);
        return user.get();
    }

    @GetMapping("/users/delete/{id}")
    public void removeUser(@PathVariable long id){
        userRepository.deleteById(id);
    }
    @PostMapping( "/users/add")
//    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
