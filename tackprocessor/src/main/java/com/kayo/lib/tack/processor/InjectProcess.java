package com.kayo.lib.tack.processor;

import com.google.auto.service.AutoService;
import com.kayo.lib.tack.annos.Inject;
import com.kayo.lib.tack.annos.Reject;
import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;

import java.util.Set;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * KayoSun
 * 2019-01-29
 * 16:50
 * ----------
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes("com.kayo.lib.tack.annos.Inject")
public class InjectProcess extends BaseProcess {

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
        Set<? extends Element> annotated = roundEnvironment.getElementsAnnotatedWith(Inject.class);
        for (Element element : annotated) {
            ((JCTree) mTrees.getTree(element)).accept(new InjectVisitor(mMaker,mNames,messager));
        }
        return false;
    }
    class InjectVisitor extends BaseVisitor {

        public InjectVisitor(TreeMaker mMaker, Names mNames, Messager mMessager) {
            super(mMaker, mNames, mMessager);
        }

        @Override
        public void visitMethodDef(JCTree.JCMethodDecl jcMethodDecl) {
            super.visitMethodDef(jcMethodDecl);
        }

        @Override
        public void visitBlock(JCTree.JCBlock jcBlock) {
            super.visitBlock(jcBlock);
            List<JCTree.JCStatement> stats = jcBlock.getStatements();
            List<JCTree.JCStatement> out = List.nil();

            JCTree.JCExpressionStatement printLiteral = mMaker.Exec(mMaker.Apply(
//                    List.of(memberAccess("java.lang.String")),//参数类型
                    List.of(memberAccess("java.lang.Object")),
                    memberAccess("com.kayo.lib.tack.api.Tack.bind"),
//                    List.of(mMaker.Literal("xiao test zhen"))//参数集合
                    List.of(mMaker.Ident(getNameFromString("this")))
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
