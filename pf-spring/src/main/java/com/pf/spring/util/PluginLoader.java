package com.pf.spring.util;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Author: Ru He
 * Date: Created in 2019/2/6.
 * Description:
 */
@Component
public class PluginLoader implements ApplicationRunner{

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadClass();
    }

    public static void loadClass() throws Exception{
        /*URL url = PluginLoader.class.getClassLoader().getResource("");
        System.out.println(url.getPath());
        URL u = new URL(url.getFile() + "pf-plugin-1.0.jar");
        URLClassLoader classLoader = new URLClassLoader(new URL[]{url},
                PluginLoader.class.getClass().getClassLoader());
        //classLoader.loadClass();
        //File f = new File(u.getFile());
        System.out.println(u.getPath());
        JarFile jar = ((JarURLConnection)u.openConnection()).getJarFile();
        //System.out.println(f.isDirectory());
        findClassByJar(jar);*/
        //Enumeration<URL> e = PluginLoader.class.getClassLoader().getResources("pf-plugin-1.0.jar");
        /*URL u = PluginLoader.class.getClassLoader().getResource("pf-plugin-1.0.jar");
        JarFile jar = new JarFile(u.getFile());
        findClassByJar(jar);*/
        /*while (e.hasMoreElements()){
            System.out.println(e.nextElement().getPath());
            //(JarURLConnection)(e.nextElement().openConnection());
            //JarFile jar = ((JarURLConnection)(e.nextElement().openConnection())).getJarFile();
            //findClassByJar(jar);
        }*/
        Class clazz = PluginLoader.class.getClassLoader().loadClass("com.pf.spring.TestClass");
        AutowireCapableBeanFactory factory = SpringUtil.getApplicationConext().getAutowireCapableBeanFactory();
        Object o = factory.createBean(clazz);
        //factory.
        //System.out.println(factory.get);

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);

                    Class clazz = PluginLoader.class.getClassLoader().loadClass("com.pf.spring.TestClass");
                    Object o = SpringUtil.getApplicationConext().getAutowireCapableBeanFactory().createBean(clazz);
                    System.out.println(o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/

    }

    public static void findClassByJar(JarFile jar) throws Exception{
        Enumeration<JarEntry> e = jar.entries();
        while (e.hasMoreElements()){
            JarEntry entry = e.nextElement();
            System.out.println(entry.getName());
        }
    }

    public static void main(String[] args) throws Exception{
        loadClass();
    }
}
