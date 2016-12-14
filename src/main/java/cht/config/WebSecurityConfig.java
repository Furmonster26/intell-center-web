/*
 * 版權宣告: FDC all rights reserved.
 */
package cht.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 程式資訊摘要：
 * <P>
 * 類別名稱　　：WebSecurityConfig.java
 * <P>
 * 程式內容說明：
 * <P>
 * 程式修改記錄：
 * <P>
 * XXXX-XX-XX：
 * <P>
 *
 * @author su
 * @version 1.0
 * @since 1.0
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/home").permitAll().anyRequest().authenticated().and().
                formLogin().loginPage("/login").permitAll().and().logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("u").password("p").roles("USER");
    }
}
