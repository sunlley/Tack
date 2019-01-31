package com.kayo.lib.tack.processor;

import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;

import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * KayoSun
 * 2019-01-29
 * 16:50
 * ----------
 */
//@AutoService(Processor.class)
//@SupportedAnnotationTypes("com.kayo.lib.tack.annos.Reject")
public class ASPProcess extends BaseProcess {

    private Trees mTrees;
    private TreeMaker mMaker;

    @Override
    protected void initAction(ProcessingEnvironment processingEnvironment) {
        JavacProcessingEnvironment processingEnv = (JavacProcessingEnvironment) this.processingEnv;
        mTrees = Trees.instance(processingEnv);
        mMaker = TreeMaker.instance(processingEnv.getContext());
    }

    @Override
    public boolean handler(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Messager messager = utils.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE,"获取到APS");
        Stream<? extends Element> stream = roundEnvironment.getRootElements().stream();
        stream.filter(it -> it.getKind() == ElementKind.CLASS)
                .forEach(it -> ((JCTree) mTrees.getTree(it)).accept(new LogClearTranslator(messager,mMaker)));
        return false;
    }
    class LogClearTranslator extends TreeTranslator {

        String LOG_TAG = "Log.";
        Messager mMessager;
        TreeMaker mMaker;

        public LogClearTranslator(Messager messager,TreeMaker mMaker) {
            this.mMessager = messager;
            this.mMaker = mMaker;
        }

        @Override
        public void visitMethodDef(JCTree.JCMethodDecl jcMethodDecl) {
            super.visitMethodDef(jcMethodDecl);
        }

        @Override
        public void visitBlock(JCTree.JCBlock jcBlock) {
            super.visitBlock(jcBlock);
        }


        private boolean isLogStatements(JCTree.JCStatement jcStatement) {
            return jcStatement.toString().contains(LOG_TAG);
        }
    }

}
