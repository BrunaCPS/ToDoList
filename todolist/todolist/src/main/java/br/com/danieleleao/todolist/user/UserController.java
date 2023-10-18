package br.com.danieleleao.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    // Chama Interface
    @Autowired // Todo ciclo de vida, pede-se para o Spring gerenciar/instaciar
    private IUserRepository userRepository;

    @PostMapping("/")
    // Recebe dados do usuário
    public ResponseEntity create(@RequestBody userModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            //Status code (400) + Mensagem de erro
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

    
       var passwordHashred = BCrypt.withDefaults().hashToString(12,userModel.getPassword().toCharArray());

       userModel.setPassword(passwordHashred);
        //Quando não existir, retorna 201 (sucesso)
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

}
