package com.my.vipzal.services;

import com.my.vipzal.table.TrainerTokenCookies;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("trainerTokenCookies")
public class TrainerTokenCookiesService {

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public void save(TrainerTokenCookies token){
        Session ses = sessionFactory.getCurrentSession();
        ses.save(token);
    }

    public TrainerTokenCookies get(String token){
        Session ses = sessionFactory.getCurrentSession();
        String sql = "FROM TrainerTokenCookies WHERE TOKEN = :token";
        Query q = ses.createQuery(sql);
        q.setParameter("token", token);
        List list = q.list();
        if(list == null || list.size() <= 0)
            return null;
        return (TrainerTokenCookies)list.get(0);
    }

    public void delete(TrainerTokenCookies token){
        Session ses = sessionFactory.getCurrentSession();
        ses.delete(token);
    }
}
