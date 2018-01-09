package com.springdemo.hkd.dao.beansOperInterface.base;


import org.springframework.data.jpa.repository.JpaRepository;

import com.springdemo.hkd.dao.bean.base.SaOpauthorize;

public interface AuthorizeJPAOper extends JpaRepository<SaOpauthorize, Long>{
}
