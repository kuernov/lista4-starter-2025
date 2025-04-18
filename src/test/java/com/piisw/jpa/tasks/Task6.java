package com.piisw.jpa.tasks;


import com.piisw.jpa.entities.Comment;
import com.piisw.jpa.entities.Event;
import com.piisw.jpa.entities.Follower;
import com.piisw.jpa.entities.SqlEvent;
import com.piisw.jpa.repositories.FollowerEventView;
import com.piisw.jpa.repositories.FollowerRepository;
import com.piisw.jpa.services.FollowerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class Task6 {

  @Autowired
  private FollowerRepository followerRepository;

  private FollowerService followerService;

  @Test
  void shouldReturnFollowerByUserId() {
    List<Follower> result = followerRepository.findByUserId("Adam");

    assertThat(result).hasSize(1);
    assertThat(result.get(0).getUserId()).isEqualTo("Adam");
  }

  @Test
  void followerShouldContainComments() {
    Follower follower = followerRepository.findByUserId("Adam").get(0);

    List<Comment> comments = follower.getComments();
    assertThat(comments).isNotEmpty();
    assertThat(comments.get(0).getContent()).isEqualTo("Komentarz do zdarzenia 21");
  }

  @Test
  void shouldReturnEmptyListForUnknownUserId() {
    List<Follower> result = followerRepository.findByUserId("nonexistent");
    assertThat(result).isEmpty();
  }

  @Test
  void shouldReturnEventsForFollowerUserId() {
    // Inicjalizujemy ręcznie serwis (ponieważ nie ma @SpringBootTest)
    this.followerService = new FollowerService(followerRepository);

    List<FollowerEventView> result = followerService.getEventsByFollowerUserId("Adam");

    assertThat(result).isNotEmpty(); // powinno być 1 jeśli masz 1 komentarz

    FollowerEventView view = result.get(0);
    assertThat(view.eventDescription()).isNotBlank();
    assertThat(view.commentContent()).isEqualTo("Komentarz do zdarzenia 21");
    assertThat(view.subscriptionDate()).isNotNull();
    assertThat(view.eventTime()).isNotNull();
  }

  @Test
  void shouldReturnMultipleEventsForFollowerUserId() {
    this.followerService = new FollowerService(followerRepository);

    List<FollowerEventView> result = followerService.getEventsByFollowerUserId("Jan");

    assertThat(result).hasSize(2);

    for (FollowerEventView view : result) {
      assertThat(view.eventDescription()).isNotBlank();
      assertThat(view.commentContent()).isIn("Komentarz do zdarzenia 23", "Komentarz do zdarzenia 24");
      assertThat(view.subscriptionDate()).isNotNull();
      assertThat(view.eventTime()).isNotNull();
    }
  }

}
