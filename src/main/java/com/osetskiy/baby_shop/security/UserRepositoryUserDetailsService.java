/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.osetskiy.baby_shop.security;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.osetskiy.baby_shop.REPO.AccountRepository;
import com.osetskiy.baby_shop.model.User;


@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private final AccountRepository userRepository;

    @Autowired
    public UserRepositoryUserDetailsService(AccountRepository userRepository) {
        this.userRepository = userRepository;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByName(username);        
        if(user == null) {
            throw new UsernameNotFoundException("Could not find user " + username);
        }
        user.getRoles().size();
        System.out.println(user.getRoles());
        return new CustomUserDetails(user);
    }

    private final static class CustomUserDetails extends User implements UserDetails {

        private CustomUserDetails(User user) {
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
        	String[] arrayRoles = getRolesAsStrings();
            return AuthorityUtils.createAuthorityList(arrayRoles);
        }

        @Override
        public String getUsername() {
            return getName();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        private static final long serialVersionUID = 5639683223516504866L;
    }
}
