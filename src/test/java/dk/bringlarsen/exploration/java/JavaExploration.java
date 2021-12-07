package dk.bringlarsen.exploration.java;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

public class JavaExploration {

    public static void main(String[] args) {
        try (ScanResult scanResult =
                     new ClassGraph()
                             .enableAllInfo()
                             .acceptPackages(JavaExploration.class.getPackageName())
                             .scan()) {
            for (ClassInfo annotatedClass : scanResult.getClassesWithAnnotation(JDK.class)) {
                System.out.println(annotatedClass.getSimpleName() + " is annotated with " + annotatedClass.getAnnotationInfo().getAsStringsWithSimpleNames());
            }
            for (ClassInfo classWithAnnotatedMethod : scanResult.getClassesWithMethodAnnotation(JDK.class)) {
                System.out.println(classWithAnnotatedMethod.getSimpleName() + " has methods annotated with : ");
                classWithAnnotatedMethod.getMethodInfo().stream()
                        .map(methodInfo -> methodInfo.getAnnotationInfo(JDK.class))
                        .filter(annotationInfo -> annotationInfo != null)
                        .forEach(annotationInfo -> System.out.print(annotationInfo.toStringWithSimpleNames().indent(2)));
            }
        }
    }
}
