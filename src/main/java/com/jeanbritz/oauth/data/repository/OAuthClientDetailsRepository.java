package com.jeanbritz.oauth.data.repository;

import com.jeanbritz.oauth.data.model.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthClientDetailsRepository extends CrudRepository<OAuthClientDetails, String> {

}
