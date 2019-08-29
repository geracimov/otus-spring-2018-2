package ru.geracimov.otus.spring.hw20springsecurityauth.config.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SP_SEC_AUTHORITIES")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NAME")
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

}
