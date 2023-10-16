package app.mlab.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
//Definição de tipo

public interface IUserRepository extends JpaRepository<UserModel,UUID>{

    UserModel findByUsername(String username);

    
}
