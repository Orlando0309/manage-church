/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import terminal.Terminal;

/**
 *
 * @author andri
 */
public class ManagerUtils {
    public static void transmute(Object taloha, Object vaovao) throws Exception {
        HashMap<String, Object> values = Terminal.values(vaovao);
        String[] fields = values.keySet().toArray(new String[values.size()]);
        Method[] met = Terminal.setters2(taloha, fields);
        for (int i = 0; i < values.size(); i++) {
            if (values.get(fields[i]) != null) {
                if (values.get(fields[i]).getClass().isArray()) {
                    Object[] obj = (Object[]) values.get(fields[i]);
                    if (obj.length != 0) {
                        met[i].invoke(taloha, values.get(fields[i]));
                    }
                } else {
                    met[i].invoke(taloha, values.get(fields[i]));
                }
            }
        }
    }
}
