package com.tpcstld.twozerogame;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by apple on 2017/5/25.
 */

        import android.util.Log;

        import org.aspectj.lang.JoinPoint;
        import org.aspectj.lang.annotation.After;
        import org.aspectj.lang.annotation.Around;
        import org.aspectj.lang.annotation.Aspect;
        import org.aspectj.lang.annotation.Before;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.PrintStream;
        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.HashMap;


@Aspect
public class AspectJ {
    private  static boolean flags=false;
    long start;
    long end;
    long start1;
    HashMap<String, Long> counts;
    ArrayList<String> log;
    long end1;
    @Before("execution(* android.app.Activity.onCreate(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable{
        if(log==null)
            log = new ArrayList<String>();
        if(counts==null)
            counts= new HashMap<String , Long>(); //记录调用次数
    }

    @Before("execution(* android.app.Activity.onDestroy(..))")
    public void onActivityMethodAfter(JoinPoint joinPoint) throws Throwable{
        if(flags!=false)
        {
            return ;
        }
        flags=true;
        clatime ct = new clatime();
        ct.getTime(log);
        clacounts cc= new clacounts();
        cc.getCounts(counts);
        log = new ArrayList<String>();
        counts=new HashMap<String , Long>();//记录调用次数
    }
    @Before("within(MainGame)")
    public void onggMethodBefore(JoinPoint joinPoint) throws Throwable{
        String name =  joinPoint.getSignature().getName();

        start = System.currentTimeMillis();

        start1=System.nanoTime();

        log.add(name+":start:"+start1);
        boolean flag=counts.containsKey(name);  //判断函数是否调用过
        if(flag==false)//不存在，置为1
        {
            counts.put(name,(long)1);
        }
        else//存在，加一
        {
            counts.put(name,counts.get(name)+1);
        }

    }
    @After("within(MainGame)")
    public void onggMethodAfter(JoinPoint joinPoint) throws Throwable{
        String name =  joinPoint.getSignature().getName();

        end = System.currentTimeMillis();

        end1=System.nanoTime();

        log.add(name+":end:"+end1);
    }
}
