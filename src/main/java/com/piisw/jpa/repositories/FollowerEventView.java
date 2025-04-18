package com.piisw.jpa.repositories;

import com.piisw.jpa.entities.Event;
import com.piisw.jpa.entities.Follower;

import java.time.LocalDateTime;

public record FollowerEventView(
  String eventDescription,
  LocalDateTime eventTime,
  boolean analysisRequired,
  String commentContent,
  LocalDateTime subscriptionDate
) { }
