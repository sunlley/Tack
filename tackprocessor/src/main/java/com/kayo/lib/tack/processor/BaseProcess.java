package com.kayo.lib.tack.processor;


import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

public abstract class BaseProcess extends AbstractProcessor {

    Utils utils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        utils = Utils.from(processingEnvironment);
        initAction(processingEnvironment);
    }

    protected void initAction(ProcessingEnvironment processingEnvironment){}

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        utils.getMessager().printMessage(Diagnostic.Kind.OTHER, this.getClass().getSimpleName()+" Process Anno ~");
        return handler(annotations, roundEnv);
    }

    abstract boolean handler(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv);

    @Override
    public Set<String> getSupportedOptions() {
        return super.getSupportedOptions();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
