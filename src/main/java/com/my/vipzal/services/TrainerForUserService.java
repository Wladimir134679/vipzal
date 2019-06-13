package com.my.vipzal.services;

import com.my.vipzal.table.TrainerForUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("trainerForUser")
public class TrainerForUserService {

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public void save(TrainerForUserService tfu){
        Session ses = sessionFactory.getCurrentSession();
        ses.save(tfu);
    }

    public TrainerForUser getForUser(long idUser){
        Session ses = sessionFactory.getCurrentSession();
        String sql = "FROM TrainerForUser WHERE ID_USER = :id";
        Query q = ses.createQuery(sql);
        q.setParameter("id", idUser);
        List l = q.list();
        if(l == null || l.size() <= 0)
            return null;
        return (TrainerForUser)l.get(0);
    }

    public List<TrainerForUser> getForTriner(long idTrainer){
        Session ses = sessionFactory.getCurrentSession();
        String sql = "FROM TrainerForUser WHERE ID_TRAINER = :id";
        Query q = ses.createQuery(sql);
        q.setParameter("id", idTrainer);
        List l = q.list();
        if(l == null || l.size() <= 0)
            return null;
        return l;
    }

    public void update(TrainerForUserService tfu){
        Session ses = sessionFactory.getCurrentSession();
        ses.update(tfu);
    }

    public void delete(TrainerForUserService tfu){
        Session ses = sessionFactory.getCurrentSession();
        ses.delete(tfu);
    }
}
