package com.piisw.jpa.services;

import com.piisw.jpa.entities.Comment;
import com.piisw.jpa.entities.Follower;
import com.piisw.jpa.repositories.FollowerEventView;
import com.piisw.jpa.repositories.FollowerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowerService {
  private final FollowerRepository followerRepository;

  public FollowerService(FollowerRepository followerRepository) {
    this.followerRepository = followerRepository;
  }

  public List<FollowerEventView> getEventsByFollowerUserId(String userId) {
    List<Follower> followers = followerRepository.findByUserId(userId);
    List<FollowerEventView> result = new ArrayList<>();

    for (Follower follower : followers) {
      for (Comment comment : follower.getComments()) {
        result.add(new FollowerEventView(
          comment.getEvent().getDescription(),
          comment.getEvent().getTime(),
          comment.getEvent().isAnalysisRequired(),
          comment.getContent(),
          follower.getSubscriptionDate()
        ));
      }
    }

    return result;
  }
}
