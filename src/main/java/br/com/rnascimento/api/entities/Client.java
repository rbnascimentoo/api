package br.com.rnascimento.api.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Table(name = "client")
@Entity(name = "client")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 255)
    @NotEmpty(message = "The name can not is empty.")
    private String name;

    @Column(nullable = false, length = 15)
    @NotEmpty(message = "The phone can not is empty.")
    private String phone;

    @Column(name = "date_creation", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name = "date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;

    @PrePersist
    public void prePersist() {
        this.dateCreation = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.dateUpdate = new Date();
    }

}
