package com.kayo.lib.tack.processor;

import com.squareup.javapoet.ClassName;

import java.util.List;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

public class Utils {

    private Filer filer;
    private Messager messager;
    private Elements elements;
    private Types types;

    private Utils(ProcessingEnvironment processingEnvironment){
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        elements = processingEnvironment.getElementUtils();
        types = processingEnvironment.getTypeUtils();
    }

    public static Utils from(ProcessingEnvironment processingEnvironment){
        return new Utils(processingEnvironment);
    }

    public ExecutableElement getOverrideMethod(ClassName creator, String methodName) {
        TypeElement element = elements.getTypeElement(creator.toString());
        List<? extends Element> elements = element.getEnclosedElements();
        for (Element ele : elements) {
            if (ele.getKind() != ElementKind.METHOD) continue;
            if (methodName.equals(ele.getSimpleName().toString())) {
                return (ExecutableElement) ele;
            }
        }
        throw new RuntimeException("method createRouteRules of interface RouteCreator not found");
    }

    public boolean checkSuperClass (TypeElement type,String superClassName) {
        if (type == null) {
            return false;
        }

        do {
            //首先判断接口
            List<? extends TypeMirror> interfaces = type.getInterfaces();
            if (interfaces != null) {
                for (TypeMirror anInterface : interfaces) {
                    TypeElement typeElement = (TypeElement) types.asElement(anInterface);
                    if (typeElement.getQualifiedName().toString().equals(superClassName)) {
                        return true;
                    }
                }
            }
            //判断类
            type = (TypeElement) types.asElement(type.getSuperclass());
            if (type.getQualifiedName().toString().equals(superClassName)) {
                return true;
            }

            if ("java.lang.Object".equals(type.getQualifiedName().toString())) {
                return false;
            }
        } while (true);
    }

    public void printMessage(String msg){
        messager.printMessage(Diagnostic.Kind.NOTE, msg);
    }

    public Filer getFiler() {
        return filer;
    }

    public Messager getMessager() {
        return messager;
    }

    public Elements getElements() {
        return elements;
    }

    public Types getTypes() {
        return types;
    }

}
