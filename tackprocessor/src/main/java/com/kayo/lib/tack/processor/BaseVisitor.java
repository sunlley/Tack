package com.kayo.lib.tack.processor;

import com.sun.source.util.Trees;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.Messager;

/**
 * KayoSun
 * 2019-02-03
 * 17:17
 * ----------
 */
public class BaseVisitor extends TreeTranslator {



    TreeMaker mMaker;
    Names mNames;
    Messager mMessager;

    public BaseVisitor(TreeMaker mMaker, Names mNames, Messager mMessager) {
        this.mMaker = mMaker;
        this.mNames = mNames;
        this.mMessager = mMessager;
    }

    //根据字符串获取Name
    protected Name getNameFromString(String s) { return mNames.fromString(s); }

    //创建变量语句
    protected JCTree.JCVariableDecl makeVarDef(JCTree.JCModifiers modifiers,
                                             String name,
                                             JCTree.JCExpression vartype,
                                             JCTree.JCExpression init) {
        return mMaker.VarDef(
                modifiers,
                getNameFromString(name), //名字
                vartype, //类型
                init //初始化语句
        );
    }
    //创建 域/方法 的多级访问, 方法的标识只能是最后一个,例如： java.lang.System.out.println
    protected JCTree.JCExpression memberAccess(String components) {
        String[] componentArray = components.split("\\.");
        JCTree.JCExpression expr = mMaker.Ident(getNameFromString(componentArray[0]));
        for (int i = 1; i < componentArray.length; i++) {
            expr = mMaker.Select(expr, getNameFromString(componentArray[i]));
        }
        return expr;
    }

    //1、声明变量并赋值 生成语句为：String xiao = "methodName";
//    JCTree.JCVariableDecl var = makeVarDef(mMaker.Modifiers(0),
//            "xiao",
//            memberAccess("java.lang.String"),
//            mMaker.Literal("methodName"));

    //2、给变量赋值 生成的赋值语句为：xiao = "assignment test";
    protected JCTree.JCExpressionStatement makeAssignment(JCTree.JCExpression lhs, JCTree.JCExpression rhs) {
        return mMaker.Exec(
                mMaker.Assign(
                        lhs,
                        rhs
                )
        );
    }
//    JCTree.JCExpressionStatement expressionStatement = makeAssignment(mMaker.Ident(getNameFromString("xiao")), mMaker.Literal("assignment test"));


}
