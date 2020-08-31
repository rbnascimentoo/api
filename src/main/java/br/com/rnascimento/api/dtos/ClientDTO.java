package br.com.rnascimento.api.dtos;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ClientDTO implements Serializable {

    private Long id;
    private String name;
    private String phone;

}
