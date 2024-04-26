package ch.jchat.chatapp.models;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ch.jchat.chatapp.enums.EAvatar;
import ch.jchat.chatapp.enums.EPlatformRoles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @NotBlank
    @NotNull
    @Column(length = 20)
    private String username;

    @NotBlank
    @NotNull
    @Column(length = 255)
    private String password;

    @NotNull
    @Column(length = 33)
    private String salt;

    @NotNull
    @Email
    @Column(length = 255)
    private String email;

    @Email
    @Column(length = 255)
    private String backUpEmail;

    @Column(length = 20)
    private String phone;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @NotNull
    private boolean banned;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "RoleOnPlatform")
    private EPlatformRoles role;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EAvatar avatar;
    
    

    @Override
    public String toString() {
        return "User [username=" + username + ", passwordHash=" + password + ", salt=" + salt + ", email=" + email
                + ", backUpEmail=" + backUpEmail + ", phone=" + phone + ", creationDate=" + creationDate + ", banned="
                + banned + ", role=" + role + ", avatar=" + avatar + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userID == null) ? 0 : userID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (userID == null) {
            if (other.userID != null)
                return false;
        } else if (!userID.equals(other.userID))
            return false;
        return true;
    }

    //Security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}