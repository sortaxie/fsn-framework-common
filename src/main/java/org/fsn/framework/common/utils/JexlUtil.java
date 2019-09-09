package org.fsn.framework.common.utils;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlScript;

public class JexlUtil {
    private static final JexlEngine engine = new JexlBuilder().cache(512).strict(true).silent(false).create();


    /**
     * 计算表达式结果
     * @param context 参数变量
     * @param expression
     * @return
     */
    public static double evaluate(JexlContext context,String expression){
        JexlScript script = engine.createScript(expression);//使用引擎创建表达式对象
        Object o = script.execute(context);//使用表达式对象开始计算
        return Double.parseDouble(o.toString());
    }
}
