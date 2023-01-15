package chap05;

import org.junit.jupiter.api.*;

@DisplayName("@DisplayName Test")
public class LifecycleTest {
    public LifecycleTest() {
        System.out.println("new LifecycleTest");
    }

    @BeforeEach
    void setUp(){
        System.out.println("setUp");
    }

    @DisplayName("Print A ")
    @Test
    void a(){
        System.out.println("A");
    }
    @Disabled
    @DisplayName("Print B")
    @Test
    void b(){
        System.out.println("B");
    }
    @AfterEach
    void tearDown(){
        System.out.println("tearDown");
    }



}
