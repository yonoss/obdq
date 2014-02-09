/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.queeq.obdq.plugins.utils;

/*
 * $Id: ClassSearchUtils.java,v 1.1 2009/03/01 12:01:11 rah003 Exp $
 *
 * Copyright 2009 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassHelper 
{
    private ClassLoader classloader;
    private ArrayList list;
    private String extension=".class";
    private String jarextension=".jar";

    public static List getClassListForType(Class obj) 
    {
        ClassHelper factory = new ClassHelper();
        return factory.getResourceList(obj);
    }


    private List<Class<?>> getResourceList(Class obj) 
    {
        this.list = new ArrayList();
        this.classloader = this.getClass().getClassLoader();
        String classpath = System.getProperty("java.class.path");

        try 
        {
            Method method = this.classloader.getClass().getMethod("getClassPath", (Class<?>) null);
            if (method != null) 
            {
                classpath = (String) method.invoke(this.classloader, (Object) null);
            }
        } 
        catch (Exception e) 
        {
        }
        if (classpath == null) 
        {
            classpath = System.getProperty("java.class.path");
        }
        //System.out.println("class path : "+classpath);
        StringTokenizer tokenizer = new StringTokenizer(classpath, File.pathSeparator);
        String token;
        File dir;
        String name;
        while (tokenizer.hasMoreTokens()) 
        {
            token = tokenizer.nextToken();
            dir = new File(token);
            if (dir.isDirectory()) 
            {
                lookInDirectory("", dir,obj);
            }
            if (dir.isFile()) 
            {
                name = dir.getName().toLowerCase();
                if (name.endsWith(".zip") || name.endsWith(".jar")) 
                {
                    this.lookInArchive(dir,obj);
                }
            }
        }
        return this.list;
    }

    /**
     * @param name Name of to parent directories in java class notation (dot * separator)
     * @param dir Directory to be searched for classes.
     */
    private void lookInDirectory(String name, File dir,Class obj) 
    {
        File[] files = dir.listFiles();
        File file;
        String fileName;
        final int size = files.length;
        for (int i = 0; i < size; i++) 
        {
            file = files[i];
            fileName = file.getName();
            if (file.isFile() && fileName.toLowerCase().endsWith(this.jarextension)) 
            {
                this.lookInArchive(file,obj);
//                try 
//                { 
//                    if (this.extension.equalsIgnoreCase(".class")) 
//                    {
//                        fileName = fileName.substring(0, fileName.length() - 6);
//                        // filter ignored resources
//                        if(fileName.indexOf("$")==-1)
//                        {
//                            Class tmpCsl=Class.forName(name + fileName);
//                            if(obj.isAssignableFrom(tmpCsl)&&!fileName.equals(obj.getName()))
//                                this.list.add(tmpCsl);
//                        }
//                    } 
//                 } catch (ClassNotFoundException e) {
//                    // ignore
//                } catch (NoClassDefFoundError e) {
//                        //ignore too
//                } catch (ExceptionInInitializerError e) {
//                    if (e.getCause() instanceof HeadlessException) {
//                        // running in headless env ... ignore 
//                    } else {
//                        throw e;
//                    }
//                }
            }
            if (file.isDirectory())
            {
                lookInDirectory(name + fileName + ".", file,obj);
            }
        }
    }
    /**
     * Search archive files for required resource.
     * @param archive Jar or zip to be searched for classes or other resources.
     */
    private void lookInArchive(File archive,Class obj) 
    {
        JarFile jarFile = null;
        try 
        {
            jarFile = new JarFile(archive);
        } 
        catch (IOException e) 
        {
            //e.printStackTrace();
            return;
        }
        Enumeration entries = jarFile.entries();
        JarEntry entry;
        String entryName;
        while (entries.hasMoreElements()) 
        {
            entry = (JarEntry) entries.nextElement();
            entryName = entry.getName();
            if (entryName.toLowerCase().endsWith(this.extension)) 
            {
                try 
                {
                        String longName= entryName;
                        entryName = entryName.substring(entryName.lastIndexOf('/')+1, entryName.length() - 6);
                        if(entryName.indexOf("$")==-1)
                        {
                            longName=longName.replaceAll("/", ".");
                            longName=longName.substring(0, longName.length()-6);
                            //Class tmpCsl=ClassLoader.getSystemClassLoader().loadClass(entryName);
                           // Thread t = Thread.currentThread();
                           // ClassLoader cl = t.getContextClassLoader();
                          
                            ClassLoader clt = this.getClass().getClassLoader();
                           //ClassLoader tmp =ClassLoader.getSystemClassLoader();
                            Class tmpCsl =Class.forName(longName,false,clt);

                            if(obj.isAssignableFrom(tmpCsl)&&!entryName.equals(obj.getName()))
                                this.list.add(tmpCsl);
                        }
                }
                catch (Throwable e) 
                {
                 //  e.printStackTrace();
                }
            }
        }
    }
    public static List sortEntryPageList(List toSort) 
    {
        ArrayList sorted=new ArrayList();
        
        for(int i=0;i<toSort.size();i++)
        {
            try
            {
                Class tmpCls=(Class)toSort.get(i);           
                int no= getOrderFromClass(tmpCls);
                    if(sorted.size()==0)
                    {
                        sorted.add(tmpCls);
                    }
                    else
                    {
                        int sSize=sorted.size();
                        for(int j=0;j<sSize;j++)
                        {
                            Class sTmpCls=(Class)sorted.get(j);
                            
                            int ccno= getOrderFromClass(sTmpCls);
                            if(no>-1)
                            {
                                if(ccno>-1)
                                {
                                    if(no<=ccno)
                                    {
                                        sorted.add(j, tmpCls);
                                        break;
                                    }
                                    else if(j==(sSize-1))
                                    {
                                        sorted.add(tmpCls);
                                    }
                                }
                                else
                                {
                                    sorted.add(j, tmpCls);
                                    break;
                                }
                            }
                            else
                            {
                                if(ccno==-1)
                                {
                                    String tmpCPClsName=tmpCls.getName();
                                    String tmpClsName=tmpCPClsName.substring(tmpCPClsName.lastIndexOf(".")+1);
                                    String sTMPCPClsName=sTmpCls.getName();
                                    String sTmpClsName=sTMPCPClsName.substring(sTMPCPClsName.lastIndexOf(".")+1);
                                    
                                    if(tmpClsName.compareTo(sTmpClsName)<=0)
                                    {
                                        sorted.add(j,tmpCls);
                                        break;
                                    }
                                    else if(j==(sSize-1))
                                    {
                                          sorted.add(tmpCls);
                                    }
                                }
                                else if(j==(sSize-1))
                                {   
                                    sorted.add(tmpCls);
                                }
                            }
                        }
                    }
            //    System.out.println(no);
            }
            catch(Exception e)
            {
            }
            
            
        }
        return sorted;
    }
    private static int getOrderFromClass(Class tmpCls) throws Exception
    {
       // Class tmpCls=(Class)toSort.get(i);
        Object tmpObj = tmpCls.newInstance();
        Method method = tmpCls.getMethod("getOrder", null);
        return (Integer)method.invoke(tmpObj, null);
    }
}