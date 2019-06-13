package com.my.vipzal.services;

import com.my.vipzal.table.User;
import com.my.vipzal.table.UserTokenCookies;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("tokenCookies")
public class TokenCookies {


    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public void save(UserTokenCookies user){
        Session ses = sessionFactory.getCurrentSession();
        ses.save(user);
    }

    public UserTokenCookies get(long id){
        Session ses = sessionFactory.getCurrentSession();
        UserTokenCookies user = ses.get(UserTokenCookies.class, id);
        return user;
    }

    public UserTokenCookies get(String token){
        Session ses = sessionFactory.getCurrentSession();
        Query q = ses.createQuery("FROM UserTokenCookies WHERE TOKEN = :parToken");
        q.setParameter("parToken", token);
        List list = q.list();
        if(list != null && list.size() > 0)
            return (UserTokenCookies)q.list().get(0);
        return null;
    }

    public List<UserTokenCookies> getAll(){
        Session ses = sessionFactory.getCurrentSession();
        Query q = ses.createSQLQuery("FROM USER_TOKEN_COOKIES");
        return q.list();
    }
}
