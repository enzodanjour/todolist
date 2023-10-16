package app.mlab.todolist.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserRepository repository;

    @PostMapping("/")
    public ResponseEntity<Object> createUserName(@RequestBody UserModel userModel) {
        var user = this.repository.findByUsername(userModel.getUsername());
        if(user != null) {
            System.out.print("Usuario Existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        var passwrdHashed = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwrdHashed);
        var userCreated = this.repository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @GetMapping("/")
    public List<UserModel> getAllUserName() {
        var userCreated = this.repository.findAll();
        return userCreated;
    }
    @GetMapping("/username")
    public UserModel findByUsername(@RequestParam String username){
        var user = this.repository.findByUsername(username);
        
        return user;

    }

}
