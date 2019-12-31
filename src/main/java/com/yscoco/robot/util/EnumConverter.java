package com.yscoco.robot.util;



import com.yscoco.robot.common.Language;

import java.lang.reflect.Method;

public class EnumConverter {
	
    @SuppressWarnings("unchecked")
	public static <T> T StingToEnum(String enumStr, Class<T> c) {
        try {
            Method method = c.getMethod("valueOf", String.class);
            return (T)method.invoke(null, enumStr);
        }
        catch (Exception e) {
        	
        	if(!Language.class.getSimpleName().equals(c.getSimpleName())){
        		e.printStackTrace();
        	}
            return null;
        }
    }
}
