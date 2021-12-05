package dk.bringlarsen.exploration.java;

import io.github.classgraph.*;

import java.util.List;

public class JavaExploration {

    public static void main(String[] args) {
        try (ScanResult scanResult =
                     new ClassGraph()
                             .enableAllInfo()
                             .acceptPackages(JavaExploration.class.getPackageName())
                             .scan()) {
            for (ClassInfo annotatedClass : scanResult.getClassesWithAnnotation(JDK.class)) {
                System.out.println(annotatedClass.getName() + " is annotated with " + annotatedClass.getAnnotationInfo());
            }
            for (ClassInfo classWithAnnotatedMethod : scanResult.getClassesWithMethodAnnotation(JDK.class)) {
                System.out.println(classWithAnnotatedMethod.getName() + " has methods annotated with :");
                classWithAnnotatedMethod.getMethodInfo().stream()
                        .forEach(methodInfo -> System.out.println(methodInfo.getAnnotationInfo(JDK.class)));
            }
        }
    }
}
