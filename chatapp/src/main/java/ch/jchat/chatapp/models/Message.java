package ch.jchat.chatapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageID;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "chatID", nullable = false)
    private Chat chat;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @NotBlank(message = "Message text cannot be empty")
    private String messageText;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendingTime;
}