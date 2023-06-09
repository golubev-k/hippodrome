import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;

@ExtendWith(MockitoExtension.class)
class HorseTest {
    @Test
    public void firstArgumentIsNullException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 2));
    }

    @Test
    public void nullExceptionMessage() {
        String exceptionMessage = "Name cannot be null.";
        try {
            Horse horse = new Horse(null, 1, 2);
        } catch (IllegalArgumentException e) {
            assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void firstArgumentIsBlankException(String arg) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(arg, 1, 2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void blankExceptionMessage(String arg) {
        String exceptionMessage = "Name cannot be blank.";
        try {
            Horse horse = new Horse(arg, 1, 2);
        } catch (IllegalArgumentException e) {
            assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void negativeSpeedExceptionMessage() {
        String exceptionMessage = "Speed cannot be negative.";
        try {
            Horse horse = new Horse("Shadowfax", -1, 2);
        } catch (IllegalArgumentException e) {
            assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void negativeDistanceExceptionMessage() {
        String exceptionMessage = "Distance cannot be negative.";
        try {
            Horse horse = new Horse("Shadowfax", 1, -2);
        } catch (IllegalArgumentException e) {
            assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void getName() {
        String name = "Shadowfax";
        Horse horse = new Horse(name, 1, 2);
        assertEquals(name, horse.getName());
    }

    @Test
    public void getSpeed() {
        int speed = 1;
        Horse horse = new Horse("Shadowfax", speed, 2);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        int distance = 2;
        Horse horse = new Horse("Shadowfax", 1, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    public void getDistanceWithoutThirdArgument() {
        Horse horse = new Horse("Shadowfax", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void moveUsesGetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            new Horse("Shadowfax", 1, 2).move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    public void checkMove() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Shadowfax", 2, 4);
            horseMockedStatic.when(() -> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(4.0);
            horse.move();
            assertEquals(12, horse.getDistance());
        }
    }
}
