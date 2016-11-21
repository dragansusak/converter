package de.zooplus.converter.web.controller;

import de.zooplus.converter.model.entity.Conversion;
import de.zooplus.converter.service.external.ExchangeRateService;
import de.zooplus.converter.service.internal.ConversionService;
import de.zooplus.converter.service.internal.CurrencyService;
import de.zooplus.converter.service.internal.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Currency;
import java.util.List;

/**
 * Created by Dragan Susak on 17-Nov-16.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConversionService conversionService;

    //http://api.fixer.io/latest?symbols=EUR&base=USD
    //http://api.fixer.io/2000-01-03?symbols=EUR&base=USD

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private CurrencyService currencyService;

    @RequestMapping
    public ModelAndView getHome() {
        ModelAndView modelAndView = new ModelAndView("home", "conversion", new Conversion());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createNewQuery(@ModelAttribute("conversion") @Valid Conversion conversion, BindingResult result) {
        if (result.hasErrors()) {
            return "home";
        }
        conversion.setRate(exchangeRateService.getExchangeRate(conversion.getSourceCurrency(), conversion.getTargetCurrency(), conversion.getValidOn()));
        conversion.setUser(userService.getById(1));
        conversionService.saveConversion(conversion);

        return "redirect:/home";
    }

    @ModelAttribute(value = "conversions")
    public List<Conversion> getCountries(){
        return conversionService.getAllForUser(1);
    }

    @ModelAttribute(value = "sourceCurrencies")
    public List<Currency> getSourceCurrencies(){
        return currencyService.getAllCurrencies();
    }

    @ModelAttribute(value = "targetCurrencies")
    public List<Currency> getTargetCurrencies(){
        return currencyService.getAllCurrencies();
    }

    @ModelAttribute(value = "loggedUser")
    public String getLoggedUser(){
        return "Logged user";
    }
}
