package com.dp.dpshopbackend.security;


import com.dp.dpshopbackend.security.jwt.JwtAuthEntryPoint;
import com.dp.dpshopbackend.security.jwt.JwtAuthTokenFilter;
import com.dp.dpshopbackend.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");

            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/**/auth/signUp").permitAll()
                .antMatchers("/**/auth/registerUser").permitAll()
                .antMatchers("/**/auth/authenticated").permitAll()
                .antMatchers("/**/articles/searchArticleByKeyword").permitAll()
                .antMatchers("/**/articles/searchArticleByPrice/**").permitAll()
                .antMatchers("/**/articles/all").permitAll()
                .antMatchers("/**/articles/**").permitAll()
                .antMatchers("/**/articles/searchArticleByselectedIsTrue").permitAll()
                .antMatchers("/**/articles/searchTop12ArticleOrderByCreatedDateDesc").permitAll()
                .antMatchers("/**/articles/searchArticleByKeyword").permitAll()
                .antMatchers("/**/articles/articlesByScategories").permitAll()
                .antMatchers("/**/articles/searchArticleByScategoryByPageables").permitAll()
                .antMatchers("/**/articles/searchArticleBySamePriceByPageables").permitAll()
                .antMatchers("/**/articles/searchArticleByPrice/**").permitAll()
                .antMatchers("/**/articles/searchbyReference/**").permitAll()
                .antMatchers("/**/articles/photoArticle/**").permitAll()
                .antMatchers("/**/articles/uploadArticlePhoto/**").permitAll()
                .antMatchers("/**/categories/**").permitAll()
                .antMatchers("/**/scategories/**").permitAll()
                .antMatchers("/**/fournisseurs/**").permitAll()
                .antMatchers("/**/commandes/create").permitAll()
                .antMatchers("/**/commandes/all").permitAll()
                .antMatchers("/**/commandes/countNumberOfCommande").permitAll()
                .antMatchers("/**/commandes/sumTotalOfCommandeByDay").permitAll()
                .antMatchers("/**/commandes/sumTotalOfCommandeByMonth").permitAll()
                .antMatchers("/**/commandes/sumTotalOfCommandeByYear").permitAll()
                .antMatchers("/**/commandes/numberOfCommandeByMonth").permitAll()

                .antMatchers("/**/commandes/countNumberOfOrdersInMonth").permitAll()
                .antMatchers("/**/commandes/countNumberOfOrdersByPendingStatus").permitAll()
                .antMatchers("/**/commandes/searchCommandeByUtilisateurByPageables/***").permitAll()

                .antMatchers("/**/commandes/searchCommandeByCustomerByPageables/***").permitAll()
                .antMatchers("/**/commandes/searchCommandeByUser/*").permitAll()

                .antMatchers("/**/commandes/sumTotaleOfCommandeByMonthByList").permitAll()
                .antMatchers("/**/commandes/sumTotaleOfCommandeByYearList").permitAll()

                .antMatchers("/**/commandes/searchAllComandesOrderByIdDesc").permitAll()
                .antMatchers("/**/commandes/findListOrderByStatuePending").permitAll()
                .antMatchers("/**/commandes/findListOrderByStatuePayed").permitAll()

                .antMatchers("/**/commandes/searchCommandeByUserIdOrderByIdDesc/*").permitAll()
                .antMatchers("/**/commandes/searchCommandeByBillingAddressIdDesc/*").permitAll()
                .antMatchers("/**/commandes/searchCommandeByShippingAddressIdDesc/*").permitAll()

                .antMatchers("/**/commandes/updateStatusOfCommande/*").permitAll()


                .antMatchers("/**/checkout/placeToOrder").permitAll()

                .antMatchers("/**/checkout/placeToOrderWithUser/**").permitAll()

                .antMatchers("/**/checkout/purchase").permitAll()

                .antMatchers("/**/countries/all").permitAll()
                .antMatchers("/**/fournisseurs/all").permitAll()
                .antMatchers("/**/fournisseurs/**").permitAll()
                .antMatchers("/**/fournisseurs/countNumberOfFournisseurs").permitAll()

                .antMatchers("/**/lignecommandes/all").permitAll()
                .antMatchers("/**/lignecommandes/searchAllLigneCommandeOrderByIdDesc").permitAll()
                .antMatchers("/**/lignecommandes/findListArticleGroupByIdDesc").permitAll()
                .antMatchers("/**/lignecommandes/searchAllLigneCommandesByCommandeId/*").permitAll()
                .antMatchers("/**/lignecommandes/searchTopLigneCommandesOrderByIdDesc").permitAll()
                .antMatchers("/**/lignecommandes/findListArticleGroupByIdDesc").permitAll()
                .antMatchers("/**/scategories/all").permitAll()
                .antMatchers("/**/states/all").permitAll()
                .antMatchers("/**/states/searchStateByCountryCode/**").permitAll()
                .antMatchers("/**/utilisateurs/all").permitAll()
                .antMatchers("/**/clients/**").permitAll()
                .antMatchers("/**/clients/countNumberOfClient").permitAll()
                .antMatchers("/**/notifications/**").permitAll()
                .antMatchers("/**/notifications/all").permitAll()
                .antMatchers("/**/notifications/countNumberOfNotification").permitAll()
                .antMatchers("/**/notifications/createRatingToArticle/**").permitAll()
                .antMatchers("/**/notifications/searchTop3RatingOrderByCreatedDateDesc").permitAll()

                .antMatchers("/**/addresslivraisons/all").permitAll()

                .antMatchers("/**/emails/sendEmail").permitAll()
                .antMatchers("/**/emails/sendMail").permitAll()

                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
