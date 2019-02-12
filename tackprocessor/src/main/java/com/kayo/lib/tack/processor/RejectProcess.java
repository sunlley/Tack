package com.kayo.lib.tack.processor;

import com.google.auto.service.AutoService;
import com.kayo.lib.tack.annos.Reject;
import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

import java.util.ArrayList;
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
public class RejectProcess extends BaseProcess {

    private Trees mTrees;
    private TreeMaker mMaker;
    private Names mNames;

    @Override
    protected void initAction(ProcessingEnvironment processingEnvironment) {
        JavacProcessingEnvironment processingEnv = (JavacProcessingEnvironment) this.processingEnv;
        mTrees = Trees.instance(processingEnv);
        mMaker = TreeMaker.instance(processingEnv.getContext());
        mNames = Names.instance(processingEnv.getContext());
    }

    @Override
    public boolean handler(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Messager messager = utils.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE,"获取到APS");
        Set<? extends Element> annotated = roundEnvironment.getElementsAnnotatedWith(Reject.class);
        for (Element element : annotated) {
            ((JCTree) mTrees.getTree(element)).accept(new LogClearTranslator(mMaker,mNames,messager));
        }
        return false;
    }
    class LogClearTranslator extends BaseVisitor {

        public LogClearTranslator(TreeMaker mMaker, Names mNames, Messager mMessager) {
            super(mMaker, mNames, mMessager);
        }

        @Override
        public void visitMethodDef(JCTree.JCMethodDecl jcMethodDecl) {
            super.visitMethodDef(jcMethodDecl);
            mMessager.printMessage(Diagnostic.Kind.NOTE,"visitMethodDef:"+jcMethodDecl.toString());
        }

        @Override
        public void visitBlock(JCTree.JCBlock jcBlock) {
            super.visitBlock(jcBlock);
            List<JCTree.JCStatement> stats = jcBlock.getStatements();
            List<JCTree.JCStatement> out = List.nil();

            JCTree.JCExpressionStatement printLiteral = mMaker.Exec(mMaker.Apply(
//                    List.of(memberAccess("java.lang.String")),//参数类型
                    List.nil(),
                    memberAccess("com.kayo.lib.tack.api.Tack.test"),
//                    List.of(mMaker.Literal("xiao test zhen"))//参数集合
                    List.nil()
                    )
            );
            out = out.append(printLiteral);
            for (JCTree.JCStatement stat : stats) {
                out = out.append(stat);
            }
            jcBlock.stats = out;
            result = jcBlock;
            mMessager.printMessage(Diagnostic.Kind.NOTE,"visitBlock:"+jcBlock.toString());
        }


    }






}
