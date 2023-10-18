package br.com.danieleleao.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<userModel, UUID>{
    //Criar método com findBy para procurar pelo atributo username, fará um select para buscar pela coluna username
   userModel findByUsername(String username);
}
