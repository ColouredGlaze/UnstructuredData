package com.lmt.data.unstructured.repository;

import com.lmt.data.unstructured.base.BaseJdbcFind;
import com.lmt.data.unstructured.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author MT-Lin
 * @date 2018/1/3 0:16
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    /**
     * @apiNote 根据用户名查找
     * @param userName 要查找的用户的用户名
     * @return UserInfo
     */
    UserInfo findByUserName(String userName);
}
