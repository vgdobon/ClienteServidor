import com.Checker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckerTest {

    @Test
    public void checkMessage_isok_test(){
        //1.Inicialización del entorno

        String mensaje="{farolaNumero:1,valor:10}";

        //2.Ejecución del código

        String resultado= Checker.check(mensaje);

        //3.Comprobación de los resultados

        String expectedResult="OK";
        Assertions.assertEquals(expectedResult,resultado);
    }

    @Test
    public void checkMessage_nook_test(){
        //1.Inicialización del entorno

        String mensaje="{farolaNumero:11w,valor:10}";

        //2.Ejecución del código

        String resultado= Checker.check(mensaje);

        //3.Comprobación de los resultados

        String expectedResult="KO";
        Assertions.assertEquals(expectedResult,resultado);
    }

    @Test
    public void checkMessage_notok_test(){
        //1.Inicialización del entorno

        String mensaje="{farolaNumero:1334.5,valor:10}";

        //2.Ejecución del código

        String resultado= Checker.check(mensaje);

        //3.Comprobación de los resultados

        String expectedResult="KO";
        Assertions.assertEquals(expectedResult,resultado);
    }
}
