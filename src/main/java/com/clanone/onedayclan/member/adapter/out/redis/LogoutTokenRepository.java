package com.clanone.onedayclan.member.adapter.out.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogoutTokenRepository extends CrudRepository<LogoutToken, String> {
}
