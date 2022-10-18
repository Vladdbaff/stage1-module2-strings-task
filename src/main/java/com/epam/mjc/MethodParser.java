package com.epam.mjc;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        MethodSignature methodSignature;
        String [] array = signatureString.split("\\(");
        String[] methodSignatureBeforeBrackets = array[0].split(" ");
        String[] afterBrackets = array[1].replace(")", "").replace(", ", ",").split(",");
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        if (afterBrackets.length > 1) {
            for (String argument: afterBrackets) {
                String[] parameter = argument.split(" ");
                arguments.add(new MethodSignature.Argument(parameter[0], parameter[1]));
            }
        }
        if (methodSignatureBeforeBrackets.length == 3) {
            methodSignature = new MethodSignature(methodSignatureBeforeBrackets[2], arguments);
            methodSignature.setAccessModifier(methodSignatureBeforeBrackets[0]);
            methodSignature.setReturnType(methodSignatureBeforeBrackets[1]);
        } else {
            methodSignature = new MethodSignature(methodSignatureBeforeBrackets[1], arguments);
            methodSignature.setReturnType(methodSignatureBeforeBrackets[0]);
        }
        return methodSignature;
    }
}
