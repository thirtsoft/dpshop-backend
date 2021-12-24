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

                .antMatchers("/**/articles/searchArticleByPrice/{price}").permitAll()
                .antMatchers("/**/articles/all").permitAll()
                .antMatchers("/**/articles/searchArticleByselectedIsTrue").permitAll()
                .antMatchers("/**/articles/searchTop12ArticleOrderByCreatedDateDesc").permitAll()
                .antMatchers("/**/articles/searchArticleByKeyword/*").permitAll()
                .antMatchers("/**/articles/articlesByScategories/{scatId}").permitAll()
                .antMatchers("/**/articles/searchArticleByScategoryByPageables/***").permitAll()
                .antMatchers("/**/articles/searchArticleBySamePriceByPageables/***").permitAll()
                .antMatchers("/**/articles/searchArticleByPrice/{price}").permitAll()
                .antMatchers("/**/articles/searchbyReference/{reference}").permitAll()
                .antMatchers("/**/articles/countNumberOfArticleInSubCat/{subCatId}").permitAll()
                .antMatchers("/**/articles/photoArticle/{idArticle}").permitAll()
                .antMatchers("/**/articles/photoArticleInContext/{idArticle}").permitAll()
                .antMatchers("/**/articles/uploadArticlePhoto/{id}").permitAll()
                .antMatchers("/**/articles/uploadArticlePhotoInFolder/{id}").permitAll()

                .antMatchers("/**/categories/all").permitAll()
                .antMatchers("/**/scategories/all").permitAll()

                .antMatchers("/**/fournisseurs/all").permitAll()

                .antMatchers("/**/commandes/create").permitAll()
                .antMatchers("/**/commandes/all").permitAll()
                .antMatchers("/**/commandes/countNumberOfCommande").permitAll()
                .antMatchers("/**/commandes/sumTotalOfCommandeByDay").permitAll()
                .antMatchers("/**/commandes/sumTotalOfCommandeByMonth").permitAll()
                .antMatchers("/**/commandes/sumTotalOfCommandeByYear").permitAll()
                .antMatchers("/**/commandes/numberOfCommandeByMonth").permitAll()

                .antMatchers("/**/commandes/countNumberOfOrdersInMonth").permitAll()
                .antMatchers("/**/commandes/countNumberOfOrdersByPendingStatus").permitAll()
                //  .antMatchers("/**/commandes/searchCommandeByUtilisateurByPageables/***").permitAll()

                //  .antMatchers("/**/commandes/searchCommandeByCustomerByPageables/***").permitAll()
                .antMatchers("/**/commandes/sumTotaleOfCommandeByMonthByList").permitAll()
                .antMatchers("/**/commandes/sumTotaleOfCommandeByYearList").permitAll()

                .antMatchers("/**/commandes/searchAllComandesOrderByIdDesc").permitAll()
                .antMatchers("/**/commandes/findListOrderByStatuePending").permitAll()
                .antMatchers("/**/commandes/findListOrderByStatuePayed").permitAll()

                .antMatchers("/**/commandes/searchCommandeByUserIdOrderByIdDesc/{id}").permitAll()
                .antMatchers("/**/commandes/searchCommandeByBillingAddressIdDesc/{id}").permitAll()
                .antMatchers("/**/commandes/searchCommandeByShippingAddressIdDesc/{id}").permitAll()

                .antMatchers("/**/commandes/searchCommandesByUtilisateurIdByPageables/***").permitAll()

                .antMatchers("/**/commandes/updateStatusOfCommande/{id}/*").permitAll()

                .antMatchers("/**/checkout/placeToOrder").permitAll()

                .antMatchers("/**/checkout/placeToOrderWithUser/**").permitAll()

                .antMatchers("/**/checkout/purchase").permitAll()

                .antMatchers("/**/countries/all").permitAll()
                .antMatchers("/**/fournisseurs/all").permitAll()
                .antMatchers("/**/fournisseurs/countNumberOfFournisseurs").permitAll()

                .antMatchers("/**/lignecommandes/all").permitAll()
                .antMatchers("/**/lignecommandes/searchAllLigneCommandeOrderByIdDesc").permitAll()
                .antMatchers("/**/lignecommandes/findListArticleGroupByIdDesc").permitAll()
                .antMatchers("/**/lignecommandes/searchAllLigneCommandesByCommandeId/{comId}").permitAll()
                .antMatchers("/**/lignecommandes/searchTopLigneCommandesOrderByIdDesc").permitAll()
                .antMatchers("/**/lignecommandes/findListArticleGroupByIdDesc").permitAll()
                .antMatchers("/**/scategories/all").permitAll()
                .antMatchers("/**/states/all").permitAll()
                .antMatchers("/**/states/searchStateByCountryCode/**").permitAll()
                .antMatchers("/**/utilisateurs/all").permitAll()
                .antMatchers("/**/utilisateurs/findById/{idUtilisateur}").permitAll()
                .antMatchers("/**/utilisateurs/update/{idUser}").permitAll()
                .antMatchers("/**/utilisateurs/avatar/{id}").permitAll()
                .antMatchers("/**/utilisateurs/uploadUserPhoto/{id}").permitAll()
                .antMatchers("/**/utilisateurs/updateCustomerProfileByUsername").permitAll()

                .antMatchers("/**/clients/all").permitAll()
                .antMatchers("/**/clients/countNumberOfClient").permitAll()
                .antMatchers("/**/notifications/all").permitAll()
                .antMatchers("/**/notifications/countNumberOfNotification").permitAll()
                .antMatchers("/**/notifications/createRatingToArticle/**").permitAll()
                .antMatchers("/**/notifications/searchTop3RatingOrderByCreatedDateDesc").permitAll()

                .antMatchers("/**/notifications/countNumberOfNotificationByProductId/{idProd}").permitAll()
                .antMatchers("/**/notifications/searchTop4RatingOrderByCreatedDateDescByProductId/{idProd}").permitAll()

                .antMatchers("/**/addresslivraisons/all").permitAll()
                .antMatchers("/**/addresslivraisons/searchAllAddressLivraisonsOrderByIdDesc").permitAll()

                .antMatchers("/**/addresseclients/searchAllAddressClientsOrderByIdDesc").permitAll()

                .antMatchers("/**/newsletters/create").permitAll()
                .antMatchers("/**/newsletters/findById/{idNewsletter}").permitAll()
                .antMatchers("/**/newsletters/countNumberOfNewsletters").permitAll()
                .antMatchers("/**/newsletters/searchAllNewslettersOrderByIdDesc").permitAll()
                .antMatchers("/**/newsletters/delete/{idNewsletter}").permitAll()

                .antMatchers("/**/blogs/searchAllBlogOrderByIdDesc").permitAll()
                .antMatchers("/**/blogs/findById/{idBlog}").permitAll()
                .antMatchers("/**/blogs/searchTop5BlogOrderByCreatedDateDesc").permitAll()
                .antMatchers("/**/blogs/photoBlog/{idBlog}").permitAll()
                .antMatchers("/**/blogs/photoBlogInFolder/{idBlog}").permitAll()
                .antMatchers("/**/blogs/uploadBlogPhoto/{id}").permitAll()
                .antMatchers("/**/blogs/uploadBlogPhotoInContext/{id}").permitAll()

                .antMatchers("/**/emails/all").permitAll()
                .antMatchers("/**/emails/findById/{idEmail}").permitAll()
                .antMatchers("/**/emails/searchAllEmailssOrderByIdDesc").permitAll()

                .antMatchers("/**/emails/sendEmail").permitAll()
                .antMatchers("/**/emails/sendToFournisseur").permitAll()
                .antMatchers("/**/emails/sendToNewsletter").permitAll()
                .antMatchers("/**/emails/sendMailToAllCustomers").permitAll()
                .antMatchers("/**/emails/sendMailToManager").permitAll()
                .antMatchers("/**/emails/findById/{idEmail}").permitAll()
                .antMatchers("/**/emails/searchAllEmailsOrderByIdDesc").permitAll()
                .antMatchers("/**/emails/countNumberOfEmail").permitAll()
                .antMatchers("/**/emails/delete/{idEmail}").permitAll()
                .antMatchers("/**/historiqueLogins/searchAllHistoriqueLoginsOrderByIdDesc").permitAll()

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
                        //    .allowedOrigins("http://localhost:4200")
                        .allowedOrigins("http://localhost:8080/MyPharma")
                        //      .allowedMethods("*")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .maxAge(3600L)
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization")
                        .allowCredentials(true);


            }
        };
    }

}
