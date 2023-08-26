package com.dp.dpshopbackend.security;

import com.dp.dpshopbackend.dto.*;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                            .allowedOrigins("http://localhost:4200")
                        // .allowedOrigins("http://localhost:8080/shopmania")
                     //   .allowedOrigins("https://soulbusiness.herokuapp.com")
                        //      .allowedMethods("*")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .maxAge(3600L)
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization")
                        .allowCredentials(true);


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

                .antMatchers("/**/articles/create").permitAll()
                .antMatchers("/**/articles/createWithFile").permitAll()
                .antMatchers("/**/articles/createWithFileInFolder").permitAll()
                .antMatchers("/**/articles/update/{idArticle}").permitAll()
                .antMatchers("/**/articles/findById/{idArticle}").permitAll()
                .antMatchers("/**/articles/all").permitAll()
                .antMatchers("/**/articles/searchAllArticleOrderByIdDesc").permitAll()
                .antMatchers("/**/articles/search-all-active-articles").permitAll()
                .antMatchers("/**/articles/searchArticleByselectedIsTrue").permitAll()
                .antMatchers("/**/articles/searchTop12ArticleOrderByCreatedDateDesc").permitAll()
                .antMatchers("/**/articles/searchArticleByKeyword").permitAll()
                .antMatchers("/**/articles/articlesByScategories/{scatId}").permitAll()
                .antMatchers("/**/articles/searchArticleByScategoryByPageables").permitAll()
                .antMatchers("/**/articles/searchArticleBySamePriceByPageables").permitAll()
                .antMatchers("/**/articles/searchArticleByPageables").permitAll()
                .antMatchers("/**/articles/searchArticleByPrice/{price}").permitAll()
                .antMatchers("/**/articles/searchbyReference/{reference}").permitAll()
                .antMatchers("/**/articles/countNumberOfArticleInSubCat/{subCatId}").permitAll()
                .antMatchers("/**/articles/photoArticle/{idArticle}").permitAll()
                .antMatchers("/**/articles/photoArticleInContext/{idArticle}").permitAll()
                .antMatchers("/**/articles/uploadArticlePhoto/{id}").permitAll()
                .antMatchers("/**/articles/uploadArticlePhotoInFolder/{id}").permitAll()
                .antMatchers("/**/articles/delete/{idArticle}").permitAll()
                .antMatchers("/**/articles/delete-article/{idArticle}").permitAll()

                .antMatchers("/**/blogs/create").permitAll()
                .antMatchers("/**/blogs/createBlogWithFile").permitAll()
                .antMatchers("/**/blogs/createBlogWithFileInFolder").permitAll()
                .antMatchers("/**/blogs/update/{idBlog}").permitAll()
                .antMatchers("/**/blogs/findById/{idBlog}").permitAll()
                .antMatchers("/**/blogs/searchbyTitle/{title}").permitAll()
                .antMatchers("/**/blogs/all").permitAll()
                .antMatchers("/**/blogs/searchTop5BlogOrderByCreatedDateDesc").permitAll()
                .antMatchers("/**/blogs/searchAllBlogOrderByIdDesc").permitAll()
                .antMatchers("/**/blogs/photoBlog/{idBlog}").permitAll()
                .antMatchers("/**/blogs/photoBlogInFolder/{idBlog}").permitAll()
                .antMatchers("/**/blogs/uploadBlogPhoto/{id}").permitAll()
                .antMatchers("/**/blogs/uploadBlogPhotoInContext/{id}}").permitAll()
                .antMatchers("/**/blogs/delete/{idBlog}").permitAll()

                .antMatchers("/**/categories/create").permitAll()
                .antMatchers("/**/categories/update/{idCategory}").permitAll()
                .antMatchers("/**/categories/findById/{idCategory}").permitAll()
                .antMatchers("/**/categories/all").permitAll()
                .antMatchers("/**/categories/searchAllCategorieOrderByIdDesc").permitAll()
                .antMatchers("/**/categories/search-all-active-categories").permitAll()
                .antMatchers("/**/categories/delete/{idCategory}").permitAll()
                .antMatchers("/**/categories/delete-categories/{idCategory}").permitAll()

                .antMatchers("/**/checkout/placeToOrder").permitAll()
                .antMatchers("/**/checkout/placeToOrderWithUser").permitAll()
                .antMatchers("/**/checkout/purchase").permitAll()

                .antMatchers("/**/clients/findById/{idClient}").permitAll()
                .antMatchers("/**/clients/all").permitAll()
                .antMatchers("/**/clients/searchAllClientsOrderByIdDesc").permitAll()
                .antMatchers("/**/clients/search-all-active-clients").permitAll()
                .antMatchers("/**/clients/countNumberOfClient").permitAll()
                .antMatchers("/**/clients/delete/{idClient}").permitAll()
                .antMatchers("/**/clients/delete-client/{idClient}").permitAll()

                .antMatchers("/**/commandes/create").permitAll()
                .antMatchers("/**/commandes/saveWithAddresses").permitAll()
                .antMatchers("/**/commandes/saveWithLoginUtilisateur").permitAll()
                .antMatchers("/**/commandes/updateStatusOfCommande/{id}").permitAll()

                .antMatchers("/**/commandes/countNumberOfCommande").permitAll()
                .antMatchers("/**/commandes/countNumberOfOrdersInMonth").permitAll()
                .antMatchers("/**/commandes/countNumberOfOrdersByPendingStatus").permitAll()

                .antMatchers("/**/commandes/sumTotalOfCommandeByDay").permitAll()
                .antMatchers("/**/commandes/sumTotalOfCommandeByMonth").permitAll()
                .antMatchers("/**/commandes/sumTotalOfCommandeByYear").permitAll()
                .antMatchers("/**/commandes/all").permitAll()
                .antMatchers("/**/commandes/searchAllComandesOrderByIdDesc").permitAll()
                .antMatchers("/**/commandes/search-all-active-commandes").permitAll()
                .antMatchers("/**/commandes/findListOrderByStatuePending").permitAll()
                .antMatchers("/**/commandes/findListOrderByStatuePayed").permitAll()

                .antMatchers("/**/commandes/searchCommandeByUserIdOrderByIdDesc/{id}").permitAll()
                .antMatchers("/**/commandes/searchCommandeByBillingAddressIdDesc/{id}").permitAll()
                .antMatchers("/**/commandes/searchCommandeByShippingAddressIdDesc/{id}").permitAll()
                .antMatchers("/**/commandes/searchCommandesByUtilisateurIdByPageables/***").permitAll()

                .antMatchers("/**/commandes/numberOfCommandeByMonth").permitAll()

                .antMatchers("/**/commandes/sumTotaleOfCommandeByMonthByList").permitAll()
                .antMatchers("/**/commandes/sumTotaleOfCommandeByYearList").permitAll()
                .antMatchers("/**/commandes/delete-commande/{idCommande}").permitAll()

                .antMatchers("/**/countries/create").permitAll()
                .antMatchers("/**/countries/update/{idCountry}").permitAll()
                .antMatchers("/**/countries/all").permitAll()
                .antMatchers("/**/countries/findById/{idCountry}").permitAll()
                .antMatchers("/**/countries/searchAllCountryOrderByIdDesc").permitAll()
                .antMatchers("/**/countries/search-all-active-countries").permitAll()
                .antMatchers("/**/countries/delete/{idCountry}").permitAll()
                .antMatchers("/**/countries/delete-country/{idCountry}").permitAll()

                .antMatchers("/**/emails/sendEmail").permitAll()
                .antMatchers("/**/emails/sendToFournisseur").permitAll()
                .antMatchers("/**/emails/sendToNewsletter").permitAll()
                .antMatchers("/**/emails/sendMailToAllCustomers").permitAll()
                .antMatchers("/**/emails/sendMailToManager").permitAll()
                .antMatchers("/**/emails/findById/{idEmail}").permitAll()
                .antMatchers("/**/emails/all").permitAll()
                .antMatchers("/**/emails/searchAllEmailsOrderByIdDesc").permitAll()
                .antMatchers("/**/emails/search-all-active-emails").permitAll()
                .antMatchers("/**/emails/countNumberOfEmail").permitAll()
                .antMatchers("/**/emails/delete/{idEmail}").permitAll()
                .antMatchers("/**/emails/delete-email/{idEmail}").permitAll()

                .antMatchers("/**/fournisseurs/create").permitAll()
                .antMatchers("/**/fournisseurs/update/{idFournisseur}").permitAll()
                .antMatchers("/**/fournisseurs/findById/{idFournisseur}").permitAll()
                .antMatchers("/**/fournisseurs/all").permitAll()
                .antMatchers("/**/fournisseurs/searchAllFournisseursOrderByIdDesc").permitAll()
                .antMatchers("/**/fournisseurs/search-all-active-fournisseurs").permitAll()
                .antMatchers("/**/fournisseurs/countNumberOfFournisseurs").permitAll()
                .antMatchers("/**/fournisseurs/delete/{idFournisseur}").permitAll()
                .antMatchers("/**/fournisseurs/delete-fournisseur/{idFournisseur}").permitAll()

                .antMatchers("/**/historiqueLogins/searchAllHistoriqueLoginsOrderByIdDesc").permitAll()
                .antMatchers("/**/historiqueLogins/search-all-active-historiqueLogins").permitAll()
                .antMatchers("/**/historiqueLogins/countNumberOfHistoriqueLogin").permitAll()
                .antMatchers("/**/historiqueLogins/delete/{idHisotiqueLogin}").permitAll()
                .antMatchers("/**/historiqueLogins/delete-historiqueLogin/{idHisotiqueLogin}").permitAll()

                .antMatchers("/**/lignecommandes/all").permitAll()
                .antMatchers("/**/lignecommandes/searchAllLigneCommandeOrderByIdDesc").permitAll()
                .antMatchers("/**/lignecommandes/findListArticleGroupByIdDesc").permitAll()
                .antMatchers("/**/lignecommandes/searchAllLigneCommandesByCommandeId/{comId}").permitAll()
                .antMatchers("/**/lignecommandes/searchTopLigneCommandesOrderByIdDesc").permitAll()
                .antMatchers("/**/lignecommandes/search-all-active-lignecommandes").permitAll()
                .antMatchers("/**/lignecommandes/delete-lignecommande/{idLignecommande}").permitAll()

                .antMatchers("/**/newsletters/create").permitAll()
                .antMatchers("/**/newsletters/findById/{idNewsletter}").permitAll()
                .antMatchers("/**/newsletters/countNumberOfNewsletters").permitAll()
                .antMatchers("/**/newsletters/searchAllNewslettersOrderByIdDesc").permitAll()
                .antMatchers("/**/newsletters/search-all-active-newsletters").permitAll()
                .antMatchers("/**/newsletters/delete/{idNewsletter}").permitAll()
                .antMatchers("/**/newsletters/delete-newsletters/{idNewsletter}").permitAll()

                .antMatchers("/**/notifications/all").permitAll()
                .antMatchers("/**/notifications/searchAllNotificationsOrderByIdDesc").permitAll()
                .antMatchers("/**/notifications/search-all-active-notifications").permitAll()
                .antMatchers("/**/notifications/findById/{idNotification}").permitAll()
                .antMatchers("/**/notifications/createNotificationToArticle").permitAll()
                .antMatchers("/**/notifications/createRatingToArticle/**").permitAll()
                .antMatchers("/**/notifications/searchTop3RatingOrderByCreatedDateDesc").permitAll()
                .antMatchers("/**/notifications/searchTop4RatingOrderByCreatedDateDescByProductId/{idProd}").permitAll()
                .antMatchers("/**/notifications/countNumberOfNotificationByProductId/{idProd}").permitAll()
                .antMatchers("/**/notifications/countNumberOfNotification").permitAll()
                .antMatchers("/**/notifications/delete/{idNotification}").permitAll()
                .antMatchers("/**/notifications/delete-notification/{idNotification}").permitAll()

                .antMatchers("/**/scategories/create").permitAll()
                .antMatchers("/**/scategories/update/{idScategory}").permitAll()
                .antMatchers("/**/scategories/findById/{idScategory}").permitAll()
                .antMatchers("/**/scategories/all").permitAll()
                .antMatchers("/**/scategories/searchAllSubCategoryOrderByIdDesc").permitAll()
                .antMatchers("/**/scategories/search-all-active-scategories").permitAll()
                .antMatchers("/**/scategories/delete/{idScategory}").permitAll()
                .antMatchers("/**/scategories/delete-scategorie/{idScategory}").permitAll()

                .antMatchers("/**/states/create").permitAll()
                .antMatchers("/**/states/update/{idState}").permitAll()
                .antMatchers("/**/states/findById/{idState}").permitAll()
                .antMatchers("/**/states/all").permitAll()
                .antMatchers("/**/states/searchAllStatesOrderByIdDesc").permitAll()
                .antMatchers("/**/states/search-all-active-states").permitAll()
                .antMatchers("/**/states/searchStateByCountryCode").permitAll()
                .antMatchers("/**/states/delete/{idState}").permitAll()
                .antMatchers("/**/states/delete-state/{idState}").permitAll()

                .antMatchers("/**/utilisateurs/all").permitAll()
                .antMatchers("/**/utilisateurs/searchAllUtilisateurOrderByIdDesc").permitAll()
                .antMatchers("/**/utilisateurs/search-all-active-utilisateurs").permitAll()
                .antMatchers("/**/utilisateurs/searchUtilisateurByUsername").permitAll()
                .antMatchers("/**/utilisateurs/findById/{idUtilisateur}").permitAll()
                .antMatchers("/**/utilisateurs/update/{idUser}").permitAll()
                .antMatchers("/**/utilisateurs/updateUsernameOfUserByUsername").permitAll()
                .antMatchers("/**/utilisateurs/updateUsernameOfUserById").permitAll()
                .antMatchers("/**/utilisateurs/avatar/{id}").permitAll()
                .antMatchers("/**/utilisateurs/uploadUserPhoto/{id}").permitAll()
                .antMatchers("/**/utilisateurs/updateCustomerProfileByUsername").permitAll()
                .antMatchers("/**/utilisateurs/delete-utilisateur/{idUtilisateur}").permitAll()


                .antMatchers("/**/addresslivraisons/all").permitAll()
                .antMatchers("/**/addresslivraisons/searchAllAddressLivraisonsOrderByIdDesc").permitAll()
                .antMatchers("/**/addresslivraisons/search-all-active-addresslivraisons").permitAll()
                .antMatchers("/**/addresslivraisons/delete-addresslivraisons/{idAddressLivraison}").permitAll()



                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
