package br.com.danieleleao.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
//Lib Lombok usada
import lombok.Data;

//getters and setters, poderia usar também @getter/@setter 
@Data
@Entity(name="tb_users")
public class userModel {
    
    @Id //Para indicar que é uma chave primária
    @GeneratedValue(generator = "UUID")
    private UUID id;

    //Unique - Restrição para não deixar salvar um usuário com mesmo nome
    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    //Quando o dado for gerado de forma automática o BD já terá a informação do "CreationTimestamp"
    @CreationTimestamp
    private LocalDateTime createdAt;
}
