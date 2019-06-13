package com.my.vipzal.services;

import com.my.vipzal.table.Trainer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("trainerService")
public class TrainerService {

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public void save(Trainer trainer){
        Session ses = sessionFactory.getCurrentSession();
        ses.save(trainer);
    }

    public Trainer get(long l){
        Session ses = sessionFactory.getCurrentSession();
        return ses.get(Trainer.class, l);
    }

    public List<Trainer> getAll(){
        Session ses = sessionFactory.getCurrentSession();
        String sql = "FROM Trainer";
        Query<Trainer> q = ses.createQuery(sql);
        return q.list();
    }

    public Trainer get(String pass, String email){
        Session ses = sessionFactory.getCurrentSession();
        String sql = "FROM Trainer WHERE PASSWORD = :pass AND EMAIL = :email";
        Query q = ses.createQuery(sql);
        q.setParameter("pass", pass);
        q.setParameter("email", email);
        List list = q.list();
        if(list == null || list.size() <= 0)
            return null;
        return (Trainer)list.get(0);
    }

    public void update(Trainer trainer){
        Session ses = sessionFactory.getCurrentSession();
        ses.update(trainer);
    }
}
