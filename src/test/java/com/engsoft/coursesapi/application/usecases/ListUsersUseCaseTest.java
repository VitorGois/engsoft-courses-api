package com.engsoft.coursesapi.application.usecases;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.engsoft.coursesapi.domain.models.User;
import com.engsoft.coursesapi.domain.models.valueobjects.Email;
import com.engsoft.coursesapi.domain.models.valueobjects.TaxId;
import com.engsoft.coursesapi.infraestructure.controllers.dtos.ListUsersReadDto;
import com.engsoft.coursesapi.infraestructure.repositories.UserRepository;


@ExtendWith(MockitoExtension.class)
public class ListUsersUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ListUsersUseCase listUsersUseCase;

    @Test
    public void testExecute_withFilters() {
        // Dados de entrada
        ListUsersReadDto params = new ListUsersReadDto("John", "john@example.com");

        // Dados de saída simulados
        List<User> expectedUsers = Arrays.asList(
            new User(1L, "John Doe", Email.fromString("john@example.com"), TaxId.fromString("123.456.789-00"), null),
            new User(2L, "John Smith", Email.fromString("john@example.com"), TaxId.fromString("123.456.789-00"), null)
        );

        // Configuração do comportamento simulado do UserRepository
        Mockito.when(userRepository.findByFilter("John", "john@example.com")).thenReturn(expectedUsers);

        // Execução do caso de uso
        List<User> actualUsers = listUsersUseCase.execute(params);

        // Verificação do resultado
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testExecute_withoutFilters() {
        // Dados de entrada
        ListUsersReadDto params = new ListUsersReadDto(null, null);

        // Dados de saída simulados
        List<User> expectedUsers = Arrays.asList(
                new User(1L, "John Doe", Email.fromString("john@example.com"), TaxId.fromString("123.456.789-00"), null),
                new User(2L, "Jane Smith", Email.fromString("jane@example.com"), TaxId.fromString("123.456.789-00"), null),
                new User(3L, "Bob Johnson", Email.fromString("bob@example.com"), TaxId.fromString("123.456.789-00"), null)
        );

        // Configuração do comportamento simulado do UserRepository
        Mockito.when(userRepository.findByFilter(null, null)).thenReturn(expectedUsers);

        // Execução do caso de uso
        List<User> actualUsers = listUsersUseCase.execute(params);

        // Verificação do resultado
        assertEquals(expectedUsers, actualUsers);
    }

}
