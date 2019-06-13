package com.my.vipzal.services;

import com.my.vipzal.table.Disease;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("diseasesService")
public class DiseasesService {

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public void save(Disease disease){
        Session ses = sessionFactory.getCurrentSession();
        ses.save(disease);
    }

    public Disease get(long id){
        Session ses = sessionFactory.getCurrentSession();
        return ses.get(Disease.class, id);
    }

    public List<Disease> getAllForUser(long idUser){
        Session ses = sessionFactory.getCurrentSession();
        String sql = "FROM Disease WHERE ID_USER = :id";
        Query q = ses.createQuery(sql);
        q.setParameter("id", idUser);
        List list = q.list();
        if(list == null || list.size() <= 0)
            return new ArrayList();
        return (List<Disease>) list;
    }

    public void delete(Disease dis){
        Session ses = sessionFactory.getCurrentSession();
        ses.delete(dis);
    }
}
