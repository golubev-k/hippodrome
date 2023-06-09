import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    public void nullArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void nullExceptionMessage() {
        String exceptionMessage = "Horses cannot be null.";
        try {
            Hippodrome hippodrome = new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void blankListException() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @Test
    public void blankListExceptionMessage() {
        String exceptionMessage = "Horses cannot be empty.";
        List<Horse> horses = new ArrayList<>();
        try {
            Hippodrome hippodrome = new Hippodrome(horses);
        } catch (IllegalArgumentException e) {
            assertEquals(exceptionMessage, e.getMessage());
        }
    }

    @Test
    public void correctSequence() {
        Hippodrome hippodrome;
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(String.valueOf(i), 1, 2));
        }
        hippodrome = new Hippodrome(horses);

        for (int i = 0; i < 30; i++) {
            assertEquals(horses.get(i), hippodrome.getHorses().get(i));
        }
    }

    @Test
    public void move() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }
    @Test
    public void getWinner(){
        List<Horse> horses = new ArrayList<>();

        horses.add(new Horse("Horse1", 2,3));
        horses.add(new Horse("Horse2", 1,2));
        horses.add(new Horse("Horse3", 4,6));
        horses.add(new Horse("Horse4", 5,4));
        horses.add(new Horse("Horse5", 3,1));

        Hippodrome hippodrome = new Hippodrome(horses);
        assertSame(hippodrome.getHorses().get(2), hippodrome.getWinner());
    }
}