package com.example.backend.Entities;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Client implements UserDetails{

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //hibernate pick automatically the GenerationType
    private Long id;

    private String name;
    private String lastName;

    @Column(unique = true)
    private String email; 
    
    //@JsonProperty(access =  JsonProperty.Access.WRITE_ONLY) //not shown while fetching users
    private String password;
    private String addresse;
    private String phoneNumber;

   
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"client"})
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //not need to be found while im posting but if i want to post massively i should pick cascade MERGE on reclamation entity
    private List<Reclamation> reclamations;

    @Override
public String toString() {
    return "Client{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", addresse='" + addresse + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", createdAt=" + (createdAt != null ? createdAt.toString() : null) +
            '}';
}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
       return List.of(new SimpleGrantedAuthority("client"));
    //return null;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
 }
