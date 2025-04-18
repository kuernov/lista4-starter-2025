package com.piisw.jpa.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SoftDelete
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Server {

    @Id
    @SequenceGenerator(name = "SERVER_ID_GENERATOR", sequenceName = "SERVER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVER_ID_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String ip;

    private LocalDateTime createdDate;

    private LocalDateTime lastUpdateDate;

    @Version
    private Long version;

    @Column(name = "deleted", insertable=false, updatable=false)
    private boolean deleted = false;

    public Server(String name, String ip) {
        super();
        this.name = name;
        this.ip = ip;
    }

    @PrePersist
    protected void onCreate() {
      LocalDateTime now = LocalDateTime.now();
      this.createdDate = now;
      this.lastUpdateDate = now;
    }

    @PreUpdate
    protected void onUpdate() {
      this.lastUpdateDate = LocalDateTime.now();
    }

}
