package guru.springframework.converter;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    public static Long ID_VALUE = 1L;
    public static String NOTES = "Notes";

    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void convert() {
        Notes notes = new Notes();
        notes.setNotes(NOTES);
        notes.setId(ID_VALUE);

        NotesCommand notesCommand = converter.convert(notes);
        assertEquals(notesCommand.getId(), ID_VALUE);
        assertEquals(notesCommand.getRecipeNotes(), NOTES);
    }
}