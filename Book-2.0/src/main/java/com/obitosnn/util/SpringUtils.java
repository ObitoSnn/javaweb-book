package com.obitosnn.util;

import com.obitosnn.servlet.BaseServlet;
import org.springframework.context.ApplicationContext;

/**
 * @Author ObitoSnn
 * @Description:
 * @Date 2020/12/22 23:53
 */
public class SpringUtils {
    private static ApplicationContext ctx;

    static {
        ctx = BaseServlet.getCtx();
    }

    /**
     * 从spring容器中获取对象
     * @param beanName bean的名字(配置文件中的id)
     * @return 返回容器中指定对象
     */
    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }

}
