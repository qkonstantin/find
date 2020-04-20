package FindTest;

import FindLauncher.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FindTest {
    private PrintStream stream;
    private ArgumentCaptor<String> captor;

    public void setStream() {
        stream = mock(PrintStream.class);
        captor = ArgumentCaptor.forClass(String.class);
        System.setOut(stream);
    }

    @Test
    public void findTest() {
        String some = "";
        boolean first = true;

        setStream();
        FindLauncher.main(new String[]{"file.txt"});
        verify(stream).println(captor.capture());
        assertEquals("Файл не найден", captor.getValue());

        setStream();
        FindLauncher.main(new String[]{"-r", "file.txt"});
        verify(stream, times(3)).println(captor.capture());
        some = "";
        for (String cap : captor.getAllValues()) {
            if (first) {
                first = false;
                some = cap;
            } else some += "\n" + cap;
        }
        assertEquals(some,
                "Найдено: C:\\Users\\Marshmello\\IdeaProjects\\find\\.\\resources\\1\\11\\file.txt\n" +
                        "Найдено: C:\\Users\\Marshmello\\IdeaProjects\\find\\.\\resources\\2\\file.txt\n" +
                        "Найдено: C:\\Users\\Marshmello\\IdeaProjects\\find\\.\\resources\\file.txt");

        first = true;
        setStream();
        FindLauncher.main(new String[]{"-d", "C:\\Users\\Marshmello\\IdeaProjects\\find\\resources", "file.txt"});
        verify(stream, times(2)).println(captor.capture());
        some = "";
        for (String cap : captor.getAllValues()) {
            if (first) {
                first = false;
                some = cap;
            } else some += "\n" + cap;
        }
        assertEquals(some,"Найдено: C:\\Users\\Marshmello\\IdeaProjects\\find\\resources\\file.txt\n" +
                "Файл(ы) найден(ы)");

        first = true;
        setStream();
        FindLauncher.main(new String[]{"-r", "-d", "C:\\Users\\Marshmello\\IdeaProjects\\find\\resources", "file.txt"});
        verify(stream, times(3)).println(captor.capture());
        some = "";
        for (String cap : captor.getAllValues()) {
            if (first) {
                first = false;
                some = cap;
            } else some += "\n" + cap;
        }
        assertEquals(some,"Найдено: C:\\Users\\Marshmello\\IdeaProjects\\find\\resources\\1\\11\\file.txt\n" +
                "Найдено: C:\\Users\\Marshmello\\IdeaProjects\\find\\resources\\2\\file.txt\n" +
                "Найдено: C:\\Users\\Marshmello\\IdeaProjects\\find\\resources\\file.txt");

    }
}
