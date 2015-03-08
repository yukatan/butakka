package test.mock;

/**
 * Created by Jesus Barquín on 8/03/15.
 */
public class MockBean {

    public void testMethod() {
        throw  new RuntimeException("Not a mocked bean");
    }

    public void testMethod(String text) {
        throw  new RuntimeException("Not a mocked bean");
    }
}
