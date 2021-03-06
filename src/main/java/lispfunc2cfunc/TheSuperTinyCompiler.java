package lispfunc2cfunc;

import com.alibaba.fastjson.JSON;
import lispfunc2cfunc.codegen.CodeGenerator;
import lispfunc2cfunc.lexer.Token;
import lispfunc2cfunc.lexer.Tokenizer;
import lispfunc2cfunc.parser.node.ASTProgramNode;
import lispfunc2cfunc.parser.Parser;
import lispfunc2cfunc.transformer.Transformer;

import java.util.List;

/**
 * @author ouyangjixin
 * @date 2018-02-12 11:25
 */
public class TheSuperTinyCompiler {

    private static Tokenizer tokenizer = new Tokenizer();
    private static Parser parser = new Parser();
    private static Transformer transformer = new Transformer();
    private static CodeGenerator codeGenerator = new CodeGenerator();


    public static void main(String[] args) {

        System.out.println("++++++++++++++TheSuperTinyCompiler In Java.++++++++++++++++++");
        System.out.println();

        String lispfunc1 = " (add 2 2) ";//add some whitespace
        String lispfunc2 = "(subtract 4 2) ";//add some whitespace
        String lispfunc3 = "(add (add 2 (subtract 4 2)) 9)";//add some whitespace

        compiler(lispfunc1);
        compiler(lispfunc2);
        compiler(lispfunc3);

        /**
         ++++++++++++++TheSuperTinyCompiler In Java.++++++++++++++++++

         lisp func:  (add 2 2)
         C func: add(2,2);

         lisp func: (subtract 4 2)
         C func: subtract(4,2);

         lisp func: (add (add 2 (subtract 4 2)) 9)
         C func: add(add(2,subtract(4,2)),9);


         Process finished with exit code 0
         */
    }

    /**
     * transform Lisp func to C func.
     *
     * @param lispfuncString
     */
    private static void compiler(String lispfuncString) {
        try {
            List<Token> tokens = tokenizer.tokens(lispfuncString);
            System.out.println("lisp func: " + lispfuncString);
            ASTProgramNode astProgramNode = (ASTProgramNode) parser.parser(tokens);
            //System.out.println(astProgramNode == null ? "" : "oldASTRoot: " + JSON.toJSONString(astProgramNode));
            ASTProgramNode newASTRoot = (ASTProgramNode) transformer.transform(astProgramNode);
            //System.out.println("newAST Tree: " + JSON.toJSONString(newASTRoot));
            System.out.println("C func: " + codeGenerator.codeGen(newASTRoot));
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}