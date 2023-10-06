package com.dp.dpshopbackend.security;

import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.security.jwt.JwtAuthEntryPoint;
import com.dp.dpshopbackend.security.jwt.JwtAuthTokenFilter;
import com.dp.dpshopbackend.security.service.UserDetailsServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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

                .antMatchers("/**/categories/*").permitAll()
                .antMatchers("/**/categories/**").permitAll()
                .antMatchers("/**/categories/search-all-active-categories").permitAll()
                .antMatchers("/**/categories/delete-category/{id}").permitAll()

                .antMatchers("/**/scategories/*").permitAll()
                .antMatchers("/**/scategories/**").permitAll()
                .antMatchers("/**/scategories/search-all-active-scategories").permitAll()
                .antMatchers("/**/categories/delete-scategorie/{id}").permitAll()

                .antMatchers("/**/articles/create-with-file").permitAll()
                .antMatchers("/**/articles/create-with-file-in-folder").permitAll()
                .antMatchers("/**/articles/search-by-reference/{reference}").permitAll()
                .antMatchers("/**/articles/findById/{idArticle}").permitAll()
                .antMatchers("/**/articles/update/{id}").permitAll()
                .antMatchers("/**/articles/search-all-active-articles").permitAll()
                .antMatchers("/**/articles/search-articles-by-selected-is-true").permitAll()
                .antMatchers("/**/articles/search-top12-article-order-by-createdDate-desc").permitAll()
                .antMatchers("/**/articles/search-article-by-keyword").permitAll()
                .antMatchers("/**/articles/articles-by-subcategories/{scatId}").permitAll()
                .antMatchers("/**/articles/search-all-by-actif-true-and-subcategory").permitAll()
                .antMatchers("/**/articles/search-article-by-subcategory-by-pageable").permitAll()
                .antMatchers("/**/articles/search-article-by-same-price-by-pageable").permitAll()
                .antMatchers("/**/articles/search-article-by-pageable").permitAll()
                .antMatchers("/**/articles/search-article-by-price/{price}").permitAll()
                .antMatchers("/**/articles/photo-article/{idArticle}").permitAll()
                .antMatchers("/**/articles/photo-article-in-context/{idArticle}").permitAll()
                .antMatchers("/**/articles/upload-photo-article/{id}").permitAll()
                .antMatchers("/**/articles/upload-photo-article-in-folder/{id}").permitAll()
                .antMatchers("/**/articles/count-number-of-article-in-subcategory/{subCatId}").permitAll()
                .antMatchers("/**/articles/search-articles-by-fournisseurs/{fournisseurId}").permitAll()
                .antMatchers("/**/articles/search-articles-by-fournisseur-by-pageable").permitAll()
                .antMatchers("/**/articles/delete-article/{subCatId}").permitAll()

                .antMatchers("/**/blogs/searchbyTitle/{title}").permitAll()
                .antMatchers("/**/blogs/all").permitAll()
                .antMatchers("/**/blogs/searchTop5BlogOrderByCreatedDateDesc").permitAll()
                .antMatchers("/**/blogs/searchAllBlogOrderByIdDesc").permitAll()
                .antMatchers("/**/blogs/photoBlog/{idBlog}").permitAll()



                .antMatchers("/**/checkout/place-to-order").permitAll()
                .antMatchers("/**/checkout/place-to-order-with-user").permitAll()
                .antMatchers("/**/checkout/purchase").permitAll()

                .antMatchers("/**/clients/findById/{idClient}").permitAll()
                .antMatchers("/**/clients/search-all-active-clients").permitAll()
                .antMatchers("/**/clients/count-number-of-client").permitAll()
                .antMatchers("/**/clients/delete-client/{idClient}").permitAll()

                .antMatchers("/**/countries/create").permitAll()
                .antMatchers("/**/countries/update/{idCountry}").permitAll()
                .antMatchers("/**/countries/findById/{idCountry}").permitAll()
                .antMatchers("/**/countries/search-all-active-countries").permitAll()
                .antMatchers("/**/countries/delete-country/{idCountry}").permitAll()


                .antMatchers("/**/emails/sendEmail").permitAll()
                .antMatchers("/**/emails/sendToFournisseur").permitAll()
                .antMatchers("/**/emails/sendToNewsletter").permitAll()
                .antMatchers("/**/emails/sendMailToAllCustomers").permitAll()
                .antMatchers("/**/emails/sendMailToManager").permitAll()

                .antMatchers("/**/newsletters/create").permitAll()
                .antMatchers("/**/newsletters/count-number-of-newsletters").permitAll()
                .antMatchers("/**/newsletters/search-all-active-newsletters").permitAll()
                .antMatchers("/**/newsletters/delete-newsletters/{idNewsletter}").permitAll()

                .antMatchers("/**/notifications/create").permitAll()
                .antMatchers("/**/notifications/create-notification-to-article").permitAll()
                .antMatchers("/**/notifications/create-rating-to-article").permitAll()
                .antMatchers("/**/notifications/search-top3-rating-order-by-createdDateDesc").permitAll()
                .antMatchers("/**/notifications/search-top4-rating-order-by-createdDateDesc-by-productId/{idProd}").permitAll()
                .antMatchers("/**/notifications/search-all-active-notifications").permitAll()
                .antMatchers("/**/notifications/delete-notification/{idNotification}").permitAll()
                .antMatchers("/**/notifications/count-number-of-notification").permitAll()
                .antMatchers("/**/notifications/count-number-of-notification-by-productId/{idProd}").permitAll()


                .antMatchers("/**/states/create").permitAll()
                .antMatchers("/**/states/**").permitAll()
                .antMatchers("/**/states/search-state-by-country-code").permitAll()
                .antMatchers("/**/states/search-all-active-states").permitAll()
                .antMatchers("/**/states/delete-state/{idState}").permitAll()

                .antMatchers("/**/commandes/update-status-of-commande/{id}").permitAll()
                .antMatchers("/**/commandes/findById/{idCommande}").permitAll()
                .antMatchers("/**/commandes/find-listOrder-by-status-pending").permitAll()
                .antMatchers("/**/commandes/find-listOrder-by-status-payed").permitAll()
                .antMatchers("/**/commandes/search-all-active-commandes").permitAll()
                .antMatchers("/**/commandes/search-commande-by-userId-order-by-IdDesc/{id}").permitAll()
                .antMatchers("/**/commandes/search-commande-by-billing-addressIdDesc/{id}").permitAll()
                .antMatchers("/**/commandes/search-commande-by-shipping-addressIdDesc/{id}").permitAll()
                .antMatchers("/**/commandes/search-commandes-by-userId-by-pageable").permitAll()
                .antMatchers("/**/commandes/count-number-of-orders-by-pending-status").permitAll()
                .antMatchers("/**/commandes/number-of-commande-by-day").permitAll()
                .antMatchers("/**/commandes/number-of-commande-by-month").permitAll()
                .antMatchers("/**/commandes/count-number-of-commande").permitAll()
                .antMatchers("/**/commandes/count-number-of-orders-in-month").permitAll()
                .antMatchers("/**/commandes/sum-totale-of-commande-by-month-by-list").permitAll()
                .antMatchers("/**/commandes/sum-totale-of-commande-by-year-list").permitAll()
                .antMatchers("/**/commandes/sum-total-of-commande-by-day").permitAll()
                .antMatchers("/**/commandes/sum-total-of-commande-by-month").permitAll()
                .antMatchers("/**/commandes/sum-total-of-commande-by-year").permitAll()
                .antMatchers("/**/commandes/delete-commande/{idCommande}").permitAll()

                .antMatchers("/**/lignecommandes/findById/{idLignecommande}").permitAll()
                .antMatchers("/**/lignecommandes/search-all-active-lignecommandes").permitAll()
                .antMatchers("/**/lignecommandes/search-all-lignecommandes-by-commandeId/{comId}").permitAll()
                .antMatchers("/**/lignecommandes/search-top-lignecommandes-order-by-IdDesc").permitAll()


                .antMatchers("/**/addresslivraisons/{idAddressLivraison}").permitAll()
                .antMatchers("/**/addresslivraisons/**").permitAll()
                .antMatchers("/**/addresslivraisons/search-all-active-addresslivraisons").permitAll()
                .antMatchers("/**/addresslivraisons/delete-addresslivraisons/{idAddressLivraison}").permitAll()

                .antMatchers("/**/fournisseurs/create").permitAll()
                .antMatchers("/**/fournisseurs/update/{idFournisseur}").permitAll()
                .antMatchers("/**/fournisseurs/findById/{idFournisseur}").permitAll()
                .antMatchers("/**/fournisseurs/search-all-active-fournisseurs").permitAll()
                .antMatchers("/**/fournisseurs/count-number-of-fournisseurs").permitAll()
                .antMatchers("/**/fournisseurs/delete-fournisseur/{idFournisseur}").permitAll()

                .antMatchers("/**/utilisateurs/findById/{idUtilisateur}").permitAll()
                .antMatchers("/**/utilisateurs/search-utilisateur-by-username").permitAll()
                .antMatchers("/**/utilisateurs/upload-photo-to-user/{id}").permitAll()
                .antMatchers("/**/utilisateurs/update/{idUser}").permitAll()
                .antMatchers("/**/utilisateurs/update-username-of-user-by-username").permitAll()
                .antMatchers("/**/utilisateurs/update-username-of-user-byId").permitAll()
                .antMatchers("/**/utilisateurs/update-password-by-username").permitAll()
                .antMatchers("/**/utilisateurs/update-password-by-userId").permitAll()
                .antMatchers("/**/utilisateurs/update-customer-profile-by-username").permitAll()
                .antMatchers("/**/utilisateurs/update-username-of-user-byId").permitAll()
                .antMatchers("/**/utilisateurs/search-all-active-utilisateurs").permitAll()
                .antMatchers("/**/utilisateurs/delete-utilisateur/{idUtilisateur}").permitAll()
                .antMatchers("/**/utilisateurs/avatar/{id}").permitAll()

                .antMatchers("/**/historiqueLogins/search-all-active-historiqueLogins").permitAll()
                .antMatchers("/**/historiqueLogins/delete-historiqueLogin/{idHisotiqueLogin}").permitAll()

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
                        .allowedOrigins("https://soulbusinesse.com")
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
