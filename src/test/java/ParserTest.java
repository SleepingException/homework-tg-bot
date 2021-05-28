import org.junit.Test;
import ru.StanokBot.DTO.Subject;
import ru.StanokBot.Service.Parser;

public class ParserTest {
    @Test
    public void test1()
    {
        Parser parser = new Parser();
        Subject s = parser.parse("#ИТЦП лекция, 14:10\n" +
                "https://us04web.zoom.us/j/79580190684?pwd=TDM2L0NTQmpjV0VVN21UNVA4RHUwQT09\n" +
                "код: 6RsUZM" +
                "дз: абоба");
        System.out.println(s);
    }
}
