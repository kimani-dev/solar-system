package sim.solar.planet;

import java.lang.reflect.*;

/*
 * Classname: Exhibit
 * 
 * Version information: v1
 *
 * Date:4-27-2022
 *
 * Description: Currently using reflection to create whatever class name is supplied
 * list of exceptions not handled in this code: 
 *        ClassNotFoundException, 
 *         NoSuchMethodException, 
 *         InstantiationException,  
 *          IllegalAccessException, 
 *         InvocationTargetException
 *
 * Author: BMM
 * 
 * Copyright notice
 */
public class Exhibit
{

   public NurseryInterface GetNursery (String className) throws
         ClassNotFoundException, 
         NoSuchMethodException, 
         InstantiationException,  
         IllegalAccessException, 
         InvocationTargetException
   {
         className = "sim.solar.planet."+className; 
         System.out.println(className) ; 
         Class<?> cls = Class.forName(className);
         Class<?>[] parameters = {};
         Constructor<?> constructor = cls.getConstructor(parameters);
         return (NurseryInterface) constructor.newInstance(new Object[]{}); 
   }
}



