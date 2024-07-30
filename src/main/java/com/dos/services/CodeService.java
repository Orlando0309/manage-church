/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.services;

import com.dos.contrat.IDAO;
import com.dos.factory.ServiceFactory;
import com.dos.model.Code;
import org.springframework.stereotype.Service;

/**
 *
 * @author andri
 */
@Service
public class CodeService extends ServiceFactory<Code,Long> implements IDAO<Code,Long> {
    
}
