package app.mlab.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "TB_TASKS")
public class TaskModel {
    /*
     * Id
     * Usuario
     * Desc
     * titulo
     * data de inicio
     * date de termino
     * prioridade
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(length = 50)
    private String title;

    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    @CreationTimestamp
    private LocalDateTime createdAt;

    private UUID idUser;
    
    public void setTitle(String title) throws Exception{
        if(title.length() >50){
            throw new Exception("O campo deve conter no máximo 50 caracteres");
        }
        this.title =title;
    }

}
