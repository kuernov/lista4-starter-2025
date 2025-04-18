package com.piisw.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(
  name = "Follower.withCommentAndEvent",
  attributeNodes = {
    @NamedAttributeNode(value = "comments", subgraph = "commentWithEvent")
  },
  subgraphs = {
    @NamedSubgraph(
      name = "commentWithEvent",
      attributeNodes = {
        @NamedAttributeNode("event")
      }
    )
  }
)
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private LocalDateTime subscriptionDate;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;
}
