package org.leochen.samples.dao;

import org.leochen.samples.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * User: leochen
 * Date: 11-12-14
 * Time: 下午2:25
 */
public interface UserRepository extends CrudRepository<User,Long>{

    User findUserByUsername(String username);
}
