package com.piisw.jpa.repositories;

import com.piisw.jpa.entities.Follower;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {

    @EntityGraph(value = "Follower.withCommentAndEvent", type = EntityGraph.EntityGraphType.LOAD)
    List<Follower> findByUserId(String userId);
}
