package com.example.StudentskaSluzba.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomLoginSuccessHandler successHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select User, Pass, Enable from users where User = ?")
                .authoritiesByUsernameQuery("select User, Role from users where User =?")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin","/admin_homepage","/admin_professors/*","/admin_students/*","/admin_subjects/*").hasRole("ADMIN")
                .antMatchers("/professor","/professor_homepage","/professor_applications/*","/professor_subs/*").hasRole("PROFESSOR")
                .antMatchers("/student","/student_homepage","/student_subjects/*","/student_reg/*","/student_mark/*").hasRole("STUDENT")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .successHandler(successHandler)
                .and()
                .logout().permitAll()
        ;
    }

}
