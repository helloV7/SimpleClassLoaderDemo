package com.company;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;

public class CustomClassLoader extends ClassLoader{
    //Windows下的 存放路径
    String path = "D:\\code\\java\\classLoader\\tempclass\\";
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = getClassPath(name);
        if(classPath != null){
            byte[] clazz = loadClazz(classPath);
            //根据byte[] 生成 class
            return defineClass(clazz, 0, clazz.length);
        }else{
            System.out.println("class is not found !");
            return null;
        }

    }

    /**
     * 根据文件路径读取byte[]
     * @param classPath
     * @return
     */
    public byte[] loadClazz(String classPath) {
        try {
            FileInputStream in = new FileInputStream(new File(classPath));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b;
            while((b = in.read()) != -1){
                baos.write(b);
            }
            in.close();
            return baos.toByteArray();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String getClassPath(String className){
        //根据className 获取路径

//         jar解压后的文件路径 大致是 com/xx/xx
//        if(className.contains(".")){
//            className = className.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
//        }

        className = className.substring(className.lastIndexOf(".")+1,className.length());
        String classPath = path + className + ".class";
        //以上自定义路径处理

        File classFile = new File(classPath);
        if(classFile.exists()){
            return classPath;
        }
        return null;
    }

}
