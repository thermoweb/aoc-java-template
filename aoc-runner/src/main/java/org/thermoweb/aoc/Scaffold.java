package org.thermoweb.aoc;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

import javax.lang.model.element.Modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public class Scaffold implements AocRunner {

    private static final Logger logger = LoggerFactory.getLogger(Scaffold.class);
    private final int day;

    private Scaffold(int day) {
        this.day = day;
    }

    public static ScaffoldExecutorBuilder builder() {
        return new ScaffoldExecutorBuilder();
    }
    @Override
    public void execute() throws RunnerException {
        String dayNumber = String.valueOf(day);

        Path currentDir = Paths.get("");
        Path classOutput = currentDir.toAbsolutePath().resolve(Path.of("aoc-solutions/src/main/java"));
        Path testOutput = currentDir.toAbsolutePath().resolve(Path.of("aoc-solutions/src/test/java"));

        TypeSpec dayClass = TypeSpec
                .classBuilder("Day" + dayNumber)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(Day.class)
                .addAnnotation(AnnotationSpec.builder(DaySolver.class).addMember("value", dayNumber).build())
                .addMethod(MethodSpec
                        .methodBuilder("partOne")
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .addParameter(ParameterSpec.builder(String.class, "input").build())
                        .returns(ParameterizedTypeName.get(ClassName.get(Optional.class), ClassName.get(BigInteger.class)))
                        .addStatement("return Optional.empty()")
                        .build())
                .addMethod(MethodSpec
                        .methodBuilder("partTwo")
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .addParameter(ParameterSpec.builder(String.class, "input").build())
                        .returns(ParameterizedTypeName.get(ClassName.get(Optional.class), ClassName.get(BigInteger.class)))
                        .addStatement("return Optional.empty()")
                        .build())
                .build();
        TypeSpec testClass = TypeSpec
                .classBuilder("Day" + dayNumber + "Test")
                .addField(FieldSpec.builder(TypeName.get(Day.class), "day")
                        .initializer("new Day" + dayNumber + "()")
                        .addModifiers(Modifier.FINAL)
                        .addModifiers(Modifier.PRIVATE)
                        .build())
                .addMethod(MethodSpec
                        .methodBuilder("test_part_one")
                        .addAnnotation(Test.class)
                        .addException(Exception.class)
                        .addStatement("assertEquals($T.empty(), day.partOne($T.getExample(" + dayNumber + ")))", Optional.class, TypeName.get(DayRunner.class))
                        .build())
                .addMethod(MethodSpec
                        .methodBuilder("test_part_two")
                        .addAnnotation(Test.class)
                        .addException(Exception.class)
                        .addStatement("assertEquals($T.empty(), day.partTwo($T.getExample(" + dayNumber + ")))", Optional.class, TypeName.get(DayRunner.class))
                        .build())
                .build();

        JavaFile javaFile = JavaFile
                .builder("org.thermoweb.aoc.days", dayClass)
                .indent("    ")
                .build();
        JavaFile testFile = JavaFile
                .builder("org.thermoweb.aoc.days", testClass)
                .indent("    ")
                .addStaticImport(Assertions.class, "assertEquals")
                .build();
        try {
            Files.createDirectories(Paths.get("aoc-solutions/src/main/resources/examples"));
            String exampleFile = "aoc-solutions/src/main/resources/examples/example_" + (day > 9 ? day : "0" + day) + ".txt";
            Files.write(Path.of(exampleFile), "".getBytes(), StandardOpenOption.CREATE);
            logger.atInfo().log("file '{}' created", exampleFile);
            javaFile.writeTo(classOutput);
            logger.atInfo().log("day class '{}' created in '{}'", dayClass.name, classOutput);
            testFile.writeTo(testOutput);
            logger.atInfo().log("test class '{}' created in '{}'", testClass.name, testOutput);
        } catch (IOException e) {
            throw new RunnerException(e);
        }
    }

    public static class ScaffoldExecutorBuilder {
        private Integer day;

        public Scaffold build() {
            return new Scaffold(day);
        }

        public void buildAndExecute() throws RunnerException {
            build().execute();
        }

        public ScaffoldExecutorBuilder withDay(int day) {
            this.day = day;
            return this;
        }
    }
}
