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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/**/auth/signUp").permitAll()
                .antMatchers("/**/auth/registerUser").permitAll()
                .antMatchers("/**/auth/authenticated").permitAll()

                .antMatchers("/**/articles/findById/{idArticle}").permitAll()
                .antMatchers("/**/articles/createWithFile").permitAll()
                .antMatchers("/**/articles/all").permitAll()
                .antMatchers("/**/articles/searchAllArticleOrderByIdDesc").permitAll()
                .antMatchers("/**/articles/search-all-active-articles").permitAll()
                .antMatchers("/**/articles/searchArticleByselectedIsTrue").permitAll()
                .antMatchers("/**/articles/searchArticleByselectedIsTrue").permitAll()
                .antMatchers("/**/articles/searchTop12ArticleOrderByCreatedDateDesc").permitAll()
                .antMatchers("/**/articles/searchArticleByKeyword").permitAll()
                .antMatchers("/**/articles/articlesByScategories/{scatId}").permitAll()
                .antMatchers("/**/articles/searchArticleByScategoryByPageables").permitAll()
                .antMatchers("/**/articles/searchArticleBySamePriceByPageables").permitAll()
                .antMatchers("/**/articles/searchArticleByPageables").permitAll()
                .antMatchers("/**/articles/searchArticleByPrice/{price}").permitAll()
                .antMatchers("/**/articles/searchbyReference/{reference}").permitAll()
                .antMatchers("/**/articles/photoArticle/{idArticle}").permitAll()
                .antMatchers("/**/articles/photoArticleInContext/{idArticle}").permitAll()
                .antMatchers("/**/articles/uploadArticlePhoto/{id}").permitAll()
                .antMatchers("/**/articles/uploadArticlePhotoInFolder/{id}").permitAll()


                .antMatchers("/**/blogs/searchbyTitle/{title}").permitAll()
                .antMatchers("/**/blogs/all").permitAll()
                .antMatchers("/**/blogs/searchTop5BlogOrderByCreatedDateDesc").permitAll()
                .antMatchers("/**/blogs/searchAllBlogOrderByIdDesc").permitAll()
                .antMatchers("/**/blogs/photoBlog/{idBlog}").permitAll()

                .antMatchers("/**/categories/*").permitAll()
                .antMatchers("/**/categories/**").permitAll()
                .antMatchers("/**/categories/all").permitAll()
                .antMatchers("/**/categories/searchAllCategorieOrderByIdDesc").permitAll()
                .antMatchers("/**/categories/search-all-active-categories").permitAll()

                .antMatchers("/**/checkout/placeToOrder").permitAll()
                .antMatchers("/**/checkout/placeToOrderWithUser").permitAll()
                .antMatchers("/**/checkout/purchase").permitAll()

                .antMatchers("/**/clients/*").permitAll()
                .antMatchers("/**/clients/**").permitAll()
                .antMatchers("/**/clients/findById/{idClient}").permitAll()
                .antMatchers("/**/clients/all").permitAll()
                .antMatchers("/**/clients/searchAllClientsOrderByIdDesc").permitAll()
                .antMatchers("/**/clients/search-all-active-clients").permitAll()

                .antMatchers("/**/countries/*").permitAll()
                .antMatchers("/**/countries/**").permitAll()
                .antMatchers("/**/countries/all").permitAll()
                .antMatchers("/**/countries/searchAllCountryOrderByIdDesc").permitAll()
                .antMatchers("/**/countries/search-all-active-countries").permitAll()


                .antMatchers("/**/emails/sendEmail").permitAll()
                .antMatchers("/**/emails/sendToFournisseur").permitAll()
                .antMatchers("/**/emails/sendToNewsletter").permitAll()
                .antMatchers("/**/emails/sendMailToAllCustomers").permitAll()
                .antMatchers("/**/emails/sendMailToManager").permitAll()

                .antMatchers("/**/newsletters/*").permitAll()
                .antMatchers("/**/newsletters/**").permitAll()
                .antMatchers("/**/newsletters/create").permitAll()
                .antMatchers("/**/newsletters/searchAllNewslettersOrderByIdDesc").permitAll()
                .antMatchers("/**/newsletters/search-all-active-newsletters").permitAll()

                .antMatchers("/**/notifications/*").permitAll()
                .antMatchers("/**/notifications/**").permitAll()
                .antMatchers("/**/notifications/all").permitAll()
                .antMatchers("/**/notifications/searchAllNotificationsOrderByIdDesc").permitAll()
                .antMatchers("/**/notifications/search-all-active-notifications").permitAll()
                .antMatchers("/**/notifications/searchTop4RatingOrderByCreatedDateDescByProductId/{idProd}").permitAll()

                .antMatchers("/**/scategories/*").permitAll()
                .antMatchers("/**/scategories/**").permitAll()
                .antMatchers("/**/scategories/all").permitAll()
                .antMatchers("/**/scategories/searchAllSubCategoryOrderByIdDesc").permitAll()
                .antMatchers("/**/scategories/search-all-active-scategories").permitAll()

                .antMatchers("/**/states/*").permitAll()
                .antMatchers("/**/states/**").permitAll()
                .antMatchers("/**/states/all").permitAll()
                .antMatchers("/**/states/searchAllStatesOrderByIdDesc").permitAll()
                .antMatchers("/**/states/search-all-active-states").permitAll()
                .antMatchers("/**/states/searchStateByCountryCode").permitAll()

                .antMatchers("/**/commandes/*").permitAll()
                .antMatchers("/**/commandes/**").permitAll()

                .antMatchers("/**/lignecommandes/*").permitAll()
                .antMatchers("/**/lignecommandes/**").permitAll()

                .antMatchers("/**/utilisateurs/all").permitAll()
                .antMatchers("/**/utilisateurs/searchAllUtilisateurOrderByIdDesc").permitAll()
                .antMatchers("/**/utilisateurs/search-all-active-utilisateurs").permitAll()
                .antMatchers("/**/utilisateurs/updateCustomerProfileByUsername").permitAll()

                .antMatchers("/**/addresslivraisons/*").permitAll()
                .antMatchers("/**/addresslivraisons/**").permitAll()
                .antMatchers("/**/addresslivraisons/all").permitAll()
                .antMatchers("/**/addresslivraisons/searchAllAddressLivraisonsOrderByIdDesc").permitAll()
                .antMatchers("/**/addresslivraisons/search-all-active-addresslivraisons").permitAll()

                .antMatchers("/**/fournisseurs/*").permitAll()
                .antMatchers("/**/fournisseurs/**").permitAll()

                .antMatchers("/**/utilisateurs/avatar/{id}").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                     //   .allowedOrigins("https://soulbusinesse.com")
                        //   .allowedMethods("*")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .maxAge(3600L)
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization")
                        .allowCredentials(true);


            }
        };
    }

}
