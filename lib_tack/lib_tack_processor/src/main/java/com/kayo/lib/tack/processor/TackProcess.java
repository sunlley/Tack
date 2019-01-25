package com.kayo.lib.tack.processor;

import com.google.auto.service.AutoService;
import com.kayo.lib.tack.annos.Paste;
import com.kayo.lib.tack.annos.PasteB;
import com.kayo.lib.tack.annos.PasteD;
import com.kayo.lib.tack.annos.PasteF;
import com.kayo.lib.tack.annos.PasteI;
import com.kayo.lib.tack.annos.PasteL;
import com.kayo.lib.tack.annos.PasteS;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Kayo
 * 2018/12/22
 * 00:25
 * Paste 编译器
 */
@AutoService(Processor.class)
public class TackProcess extends BaseProcess {

    @Override
    protected Set<String> getAnnotationTypes() {
        Set<String> annotations = new HashSet<>();
        annotations.add(Paste.class.getCanonicalName());
        annotations.add(PasteS.class.getCanonicalName());
        annotations.add(PasteF.class.getCanonicalName());
        annotations.add(PasteD.class.getCanonicalName());
        annotations.add(PasteI.class.getCanonicalName());
        annotations.add(PasteL.class.getCanonicalName());
        annotations.add(PasteB.class.getCanonicalName());
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        utils.getMessager().printMessage(Diagnostic.Kind.OTHER, "Process Tack Anno ~");

        //1.对数据进行分类  类信息/注解变量信息
        Map<Element, List<Element>> map = new LinkedHashMap<>();
        //*** 解析PasteS
        parserAnno(roundEnvironment, map, PasteS.class);
        //*** 解析PasteF
        parserAnno(roundEnvironment, map, PasteF.class);
        //*** 解析PasteD
        parserAnno(roundEnvironment, map, PasteD.class);
        //*** 解析PasteI
        parserAnno(roundEnvironment, map, PasteI.class);
        //*** 解析PasteL
        parserAnno(roundEnvironment, map, PasteL.class);
        //*** 解析PasteB
        parserAnno(roundEnvironment, map, PasteB.class);
        //*** 解析Paste
        parserAnno(roundEnvironment, map, Paste.class);
        if (map.isEmpty()) {
            return false;
        }

        //2.解析并生成代码
        ClassName activity = ClassName.bestGuess("android.app.Activity");
        ClassName fragment = ClassName.bestGuess("android.support.v4.app.Fragment");
        ClassName intent = ClassName.bestGuess("android.content.Intent");
        ClassName bundle = ClassName.bestGuess("android.os.Bundle");
        ClassName inject = ClassName.bestGuess("com.kayo.lib.tack.api.Inject");
        ClassName bundleBinder = ClassName.bestGuess("com.kayo.lib.tack.api.binders.BundleBinder");

        for (Map.Entry<Element, List<Element>> elementListEntry : map.entrySet()) {
            //类信息
            TypeElement classEl = (TypeElement) elementListEntry.getKey();
            String packageName = getPackageName(classEl);

            int classType = 0;//1标识 activity  2标识 fragment
            if (utils.checkSuperClass(classEl, "android.app.Activity")) {
                classType = 1;
            } else if (utils.checkSuperClass(classEl, "android.support.v4.app.Fragment")) {
                classType = 2;
            }
            if (classType == 0) {
                continue;
            }
            ClassName targetClassName = ClassName.bestGuess(classEl.getQualifiedName().toString());

            //生成变量
            FieldSpec.Builder field = FieldSpec.builder(targetClassName, "mTarget", Modifier.PRIVATE);

            //生成构造
            MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(targetClassName, "target")
                    .addStatement("this.mTarget = target")
                    .addCode("if(target == null){ throw new IllegalArgumentException($S);}\n", "The target can not null ~ ");
            if (classType == 1) {
                constructor.addStatement("$T intent = target.getIntent()", intent)
                        .addStatement("$T binder = new $T(intent)", bundleBinder, bundleBinder)
                        .addStatement("inject(binder)");
            } else {
                constructor.addStatement("$T bundle = target.getArguments()", bundle)
                        .addStatement("$T binder = new $T(bundle)", bundleBinder, bundleBinder)
                        .addCode("if(binder == null){ return;}\n")
                        .addStatement("inject(binder)");
            }
            //生成赋值方法
            MethodSpec.Builder bindArgs = MethodSpec.methodBuilder("inject")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .addParameter(bundleBinder, "binder")
                    .addParameter(boolean.class, "nullable")
                    .addStatement("if(binder==null){return;}");
            MethodSpec.Builder bindArgs2 = MethodSpec.methodBuilder("inject")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .addParameter(bundle, "bundle")
                    .addParameter(boolean.class, "nullable")
                    .addStatement("if(bundle==null){return;}")
                    .addStatement("$T binder = new $T(bundle)", bundleBinder, bundleBinder)
                    .addStatement("this.inject(binder,nullable)");
            MethodSpec.Builder bindArgs3 = MethodSpec.methodBuilder("inject")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .addParameter(bundleBinder, "binder")
                    .addStatement("if(binder==null){return;}")
                    .addStatement("this.inject(binder,true)");
            MethodSpec.Builder bindArgs4 = MethodSpec.methodBuilder("inject")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .addParameter(bundle, "bundle")
                    .addStatement("if(bundle==null){return;}")
                    .addStatement("this.inject(bundle,true)");
            List<Element> elements = elementListEntry.getValue();
            List<Element> normal = new ArrayList<>();
            List<Element> special = new ArrayList<>();
            //分类
            for (Element argEl : elements) {
                Paste paste = argEl.getAnnotation(Paste.class);
                if (paste != null){
                    special.add(argEl);
                }else {
                    normal.add(argEl);
                }
            }
            //1类
            for (Element argEl : special) {
                createBindArgs(bindArgs, argEl,true);
            }
            //2类
            bindArgs.addCode("if(nullable){\n");
            for (Element argEl : normal) {
                createBindArgs(bindArgs, argEl,true);
            }
            bindArgs.addCode("}else{\n");
            for (Element argEl : normal) {
                createBindArgs(bindArgs, argEl,false);
            }
            bindArgs.addCode("}\n");

            //生成类
            TypeSpec typeSpec = TypeSpec.classBuilder(classEl.getSimpleName().toString() + "$$Tack")
                    .addSuperinterface(inject)
                    .addModifiers(Modifier.PUBLIC)
                    .addField(field.build())
                    .addMethod(constructor.build())
                    .addMethod(bindArgs.build())
                    .addMethod(bindArgs2.build())
                    .addMethod(bindArgs3.build())
                    .addMethod(bindArgs4.build())
                    .build();
            //生成java文件
            try {
                JavaFile build = JavaFile.builder(packageName, typeSpec).build();
                build.writeTo(utils.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return !map.isEmpty();
    }

    private void createBindArgs(MethodSpec.Builder bindArgs, Element argEl,boolean nullable) {
        //参数类型
        String targetType = argEl.asType().toString();
        //参数名
        String argName = argEl.getSimpleName().toString();
        String argKey = argName;
        //注解实例
        //****解析 Paste
        Paste paste = argEl.getAnnotation(Paste.class);
        if (paste != null) {
            if (paste.value().trim().length() > 0) {
                argKey = paste.value().trim();
            }
            String tempName = argName + "Obj";
            createArgCodeI(bindArgs, targetType, argName, argKey, tempName,nullable);
            return;
        }
        //****解析 PasteS
        PasteS pasteS = argEl.getAnnotation(PasteS.class);
        if (pasteS != null) {
            if (pasteS.value().trim().length() > 0) {
                argKey = pasteS.value().trim();
            }
            bindArgs.addCode("    mTarget.$N = binder.getString($S,$S);\n", argName, argKey, pasteS.defaultVar());
            return;
        }
        //****解析 PasteF
        PasteF pasteF = argEl.getAnnotation(PasteF.class);
        if (pasteF != null) {
            if (pasteF.value().trim().length() > 0) {
                argKey = pasteF.value().trim();
            }
            String tempName = argName + "Obj";
            String defaultVar = pasteF.defaultVar() + "f";
            createArgCodeII(bindArgs, "getFloat", argName, argKey, defaultVar,nullable);
            return;
        }
        //****解析 PasteD
        PasteD pasteD = argEl.getAnnotation(PasteD.class);
        if (pasteD != null) {
            if (pasteD.value().trim().length() > 0) {
                argKey = pasteD.value().trim();
            }
            String tempName = argName + "Obj";
            String defaultVar = pasteD.defaultVar() + "";
            createArgCodeII(bindArgs, "getDouble", argName, argKey, defaultVar,nullable);
            return;
        }

        //****解析 PasteI
        PasteI pasteI = argEl.getAnnotation(PasteI.class);
        if (pasteI != null) {
            if (pasteI.value().trim().length() > 0) {
                argKey = pasteI.value().trim();
            }
            String tempName = argName + "Obj";
            String defaultVar = pasteI.defaultVar() + "";
            createArgCodeII(bindArgs, "getInt", argName, argKey, defaultVar,nullable);
            return;
        }
        //****解析 PasteL
        PasteL pasteL = argEl.getAnnotation(PasteL.class);
        if (pasteL != null) {
            if (pasteL.value().trim().length() > 0) {
                argKey = pasteL.value().trim();
            }
            String tempName = argName + "Obj";
            String defaultVar = pasteL.defaultVar() + "l";
            createArgCodeII(bindArgs, "getLong", argName, argKey, defaultVar,nullable);
            return;
        }
        //****解析 PasteB
        PasteB pasteB = argEl.getAnnotation(PasteB.class);
        if (pasteB != null) {
            if (pasteB.value().trim().length() > 0) {
                argKey = pasteB.value().trim();
            }
            String tempName = argName + "Obj";
            String defaultVar = "(byte)" + pasteB.defaultVar();
            createArgCodeII(bindArgs, "getByte", argName, argKey, defaultVar,nullable);
        }
    }

    private void createArgCodeI(MethodSpec.Builder bindArgs, String targetType, String argName, String argKey, String tempName,boolean nullable) {
        bindArgs.addStatement("    Object $N = binder.get($S)", tempName, argKey);
        bindArgs.addCode("    if($N != null){\n        mTarget.$N = ($N)$N;\n    }\n", tempName, argName, targetType, tempName);
    }

    private void createArgCodeII(MethodSpec.Builder bindArgs, String method, String argName, String argKey, String defaultVar,boolean nullable) {
        if (nullable){
            if (defaultVar == null || defaultVar.trim().length() == 0) {
                bindArgs.addCode("    mTarget.$N = binder." + method + "($S);\n", argName, argKey);
            } else {
                bindArgs.addCode("    mTarget.$N = binder."+method+"($S,"+defaultVar+");\n", argName, argKey);
            }
        }else {
            if (defaultVar == null || defaultVar.trim().length() == 0) {
                bindArgs.addCode("    Object $N = binder.get($S);\n" +
                        "    if($N != null){\n" +
                        "        mTarget.$N = binder."+method+"($S);}\n", argName, argKey ,argName,argName,argKey);
            } else {
                bindArgs.addCode("    Object $N = binder.get($S);\n" +
                        "    if($N != null){\n" +
                        "        mTarget.$N = binder."+method+"($S,"+defaultVar+");}\n", argName, argKey ,argName,argName,argKey);

            }
        }


    }

    private void parserAnno(RoundEnvironment roundEnvironment, Map<Element, List<Element>> map, Class<? extends Annotation> clz) {
        Set<? extends Element> elPastes = roundEnvironment.getElementsAnnotatedWith(clz);
        if (elPastes != null) {
            for (Element element : elPastes) {
                //类信息
                Element enclosingElement = element.getEnclosingElement();
                List<Element> holdEls = map.computeIfAbsent(enclosingElement, k -> new ArrayList<>());
                holdEls.add(element);
            }
        }
    }

}
