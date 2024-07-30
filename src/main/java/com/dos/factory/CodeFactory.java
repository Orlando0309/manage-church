/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dos.factory;

import com.dos.contrat.ICodeFactory;
import com.dos.contrat.IDAO;
import com.dos.model.CodeMouvement;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("mouvementcodefactory")
public class CodeFactory extends ServiceFactory<CodeMouvement,Long> implements ICodeFactory<CodeMouvement>{

    @Override
    public CodeMouvement getCodeInfo(String code) throws Exception {
        CodeMouvement codeRetour;
        try (Session session = factory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CodeMouvement> cq = cb.createQuery(CodeMouvement.class);
            Root<CodeMouvement> rootEntry = cq.from(CodeMouvement.class);
            cq.select(rootEntry);
            
            //condition if the code exists
            cq.where(
                    cb.equal(rootEntry.get("code"), code)
            );
            
            codeRetour = session.createQuery(cq).getSingleResultOrNull();
        }
        if(codeRetour==null)
            throw new Exception("The provided code does not exist");
        return codeRetour;
    }

    @Override
    public List<CodeMouvement> findByCode(String str) throws Exception {
        List<CodeMouvement> searchResult;
        try(Session session= factory.openSession()){
            CriteriaBuilder cb= session.getCriteriaBuilder();
            String sb = str+"%";
            CriteriaQuery<CodeMouvement> cq=cb.createQuery(CodeMouvement.class);
            Root<CodeMouvement> rootEntry=cq.from(CodeMouvement.class);
            cq.select(rootEntry);

            cq.where(
              cb.like(rootEntry.get("code"), sb)
            );
            searchResult=session.createQuery(cq).getResultList();
        }
        return searchResult;
    }

    @Override
    public HashMap<String, CodeMouvement> getCodesInfos(String... code) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
