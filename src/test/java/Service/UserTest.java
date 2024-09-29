package Service;

import com.eiericksilva.controle_financeiro.dto.UserDTO;
import com.eiericksilva.controle_financeiro.dto.mapper.UserMapper;
import com.eiericksilva.controle_financeiro.entities.User;
import com.eiericksilva.controle_financeiro.repositories.UserRepository;
import com.eiericksilva.controle_financeiro.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldCreateUser_whenValidUserDTOProvided() {
        // Arrange
        UserDTO userDTO = new UserDTO(1L, "joaozinho", "Joao", "Da Roça");

        User user = new User();
        user.setId(1L);
        user.setUsername("joaozinho");
        user.setFirstName("Joao");
        user.setLastName("Da Roça");

        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        // Act
        UserDTO createdUser = userService.createUser((userDTO));

        // Assert
        assertNotNull(createdUser, "The created user should not be null");
        assertEquals("joaozinho", createdUser.username(), "The username should be 'joaozinho' ");
        assertEquals("Joao", createdUser.firstName(), "The firstName should be 'Joao' ");
        assertEquals("Da Roça", createdUser.lastName(), "The lastName should be 'Da Roça' ");
    }

    @Test
    void shouldReturnAllUsers_whenUsersExist() {
        // Arrange
        User user1 = new User(1L, "joazinho", "Joao", "Da Roça");
        User user2 = new User(2L, "mariazinha", "Maria", "Sem Rumo");
        List<User> users = Arrays.asList(user1, user2);

        UserDTO userDTO1 = new UserDTO(1L, "joazinho", "Joao", "Da Roça");
        UserDTO userDTO2 = new UserDTO(2L, "mariazinha", "Maria", "Sem Rumo");
        List<UserDTO> userDTOs = Arrays.asList(userDTO1, userDTO2);

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toDTO(user1)).thenReturn(userDTO1);
        when(userMapper.toDTO(user2)).thenReturn(userDTO2);

        // Act

        List<UserDTO> result = userService.findAllUsers();

        // Assert

        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.size(), "The result should contain 2 users");
        assertEquals(userDTOs, result, "The result should match the expected list of UserDTOs");
    }

    @Test
    void shouldReturnOneUserDTO_whenUserExist() {
        // Arrange
        User user = new User(1L, "joazinho", "Joao", "Da Roça");
        UserDTO userDTO = new UserDTO(1L, "joazinho", "Joao", "Da Roça");
        Optional<User> optionalUser = Optional.of(user);

        when(userRepository.findById(1L)).thenReturn(optionalUser);
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        // Act

        UserDTO userFound = userService.findUserById(1L);

        // Assert

        assertNotNull(userFound, "The result should not be null");
        assertEquals(userDTO, userFound, "The userFound should match the userDTO expected");
    }
}
