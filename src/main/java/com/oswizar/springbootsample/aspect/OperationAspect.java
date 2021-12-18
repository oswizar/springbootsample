package com.oswizar.springbootsample.aspect;

import com.oswizar.springbootsample.annotation.OperationLogDetail;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class OperationAspect {

    /**
     * 此处的切点是注解的方式，也可以用包名的方式达到相同的效果
     * '@Pointcut("execution(* com.wwj.springboot.service.impl.*.*(..))")'
     */
    @Pointcut("@annotation(com.oswizar.springbootsample.annotation.OperationLogDetail)")
    public void operationLog(){}


    /**
     * 环绕增强，相当于MethodInterceptor
     */
    @Around("operationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = null;
        long time = System.currentTimeMillis();
        try {
            res =  joinPoint.proceed();
            time = System.currentTimeMillis() - time;
            return res;
        } finally {
            try {
                //方法执行完成后增加日志
                addOperationLog(joinPoint,res,time);
            }catch (Exception e){
                System.out.println("LogAspect 操作失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void addOperationLog(JoinPoint joinPoint, Object res, long time){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Map<String, Object> operationLog = new HashMap<>();
        OperationLogDetail annotation = signature.getMethod().getAnnotation(OperationLogDetail.class);
        if(annotation != null){
            operationLog.put("level", annotation.level());
            operationLog.put("des", getDetail(((MethodSignature)joinPoint.getSignature()).getParameterNames(),
                    joinPoint.getArgs(),annotation));
            operationLog.put("operationType", annotation.operationType().getValue());
            operationLog.put("operationUnit", annotation.operationUnit().getValue());
        }
        //TODO 这里保存日志
        System.out.println("记录日志:" + operationLog);
//        operationLogService.insert(operationLog);
    }

    /**
     * 对当前登录用户和占位符处理
     * @param argNames 方法参数名称数组
     * @param args 方法参数数组
     * @param annotation 注解信息
     * @return 返回处理后的描述
     */
    private String getDetail(String[] argNames, Object[] args, OperationLogDetail annotation){

        Map<Object, Object> map = new HashMap<>();
        for(int i = 0;i < argNames.length;i++){
            System.out.println(argNames[i]);
            map.put(argNames[i],args[i]);
        }

        String detail = annotation.detail();
//        try {
//            for (Map.Entry<Object, Object> entry : map.entrySet()) {
//                Map<String, Object> v = (Map<String, Object>) entry.getValue();
//                detail = (String) v.get("taskId");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        return detail;
    }

    @Before("operationLog()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        System.out.println("进入方法前执行.....");

    }

    /**
     * 处理完请求，返回内容
     * @param res
     */
    @AfterReturning(returning = "res", pointcut = "operationLog()")
    public void doAfterReturning(Object res) {
        System.out.println("方法的返回值 : " + res);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("operationLog()")
    public void throwss(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("operationLog()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }

}
