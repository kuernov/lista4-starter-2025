package com.piisw.jpa.tasks;

import com.piisw.jpa.entities.Server;
import com.piisw.jpa.repositories.ServerRepository;
import com.piisw.jpa.services.ServerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
class Task5 {

    @Mock
    private ServerRepository serverRepository;

    @InjectMocks
    private ServerService serverService;

    @Test
    void shouldReturnMockServer() throws Exception {
        // given
        String serverName = "dummyName";
        String mockServerName = "Alex";
        String mockServerIp = "noIp";
        Server dummyServer = new Server(mockServerName, mockServerIp);
        whenSearchingForNameReturn(serverName, dummyServer);

        // when
        Optional<Server> result = serverService.findByName(serverName);

        // then
        assertThat(result.isPresent(), Matchers.is(true));
        assertThat(result.get().getName(), Matchers.is(mockServerName));
        assertThat(result.get().getIp(), Matchers.is(mockServerIp));
    }

    private void whenSearchingForNameReturn(String serverName, Server dummyServer) {
        when(serverRepository.findByName(serverName)).thenReturn(Optional.of(dummyServer));
    }

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public ServerService serverService(ServerRepository serverRepository) {
            return new ServerService(serverRepository);
        }
    }

}
