package m2.ila.fr.istic.ila.vv;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class RunAClass {


    public static void main(String[] args) {

        JUnitCore core = new JUnitCore();
//        Result result = core.run(BinOpTest.class, AnotherInputTest.class);
//        Result result = core.run(BinOpTest.class);
//        System.out.println("FINISHED");
//        System.out.println(String.format("| IGNORED: %d", result.getIgnoreCount()));
//        System.out.println(String.format("| FAILURES: %d", result.getFailureCount()));
//        System.out.println(String.format("| RUN: %d", result.getRunCount()));

    }
    
    public static void provideTest(Class<?> clazz) {

        JUnitCore core = new JUnitCore();
//        Result result = core.run(BinOpTest.class, AnotherInputTest.class);
        Result result = core.run(clazz);
        System.out.println("FINISHED");
        System.out.println(String.format("| IGNORED: %d", result.getIgnoreCount()));
        System.out.println(String.format("| FAILURES: %d", result.getFailureCount()));
        System.out.println(String.format("| RUN: %d", result.getRunCount()));

    }

}