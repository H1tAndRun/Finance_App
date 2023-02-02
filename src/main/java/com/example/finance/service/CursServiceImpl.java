package com.example.finance.service;

import com.example.finance.entities.Currency;
import com.example.finance.entities.Curs;
import com.example.finance.handlers.NoQuoterAGivenDay;
import com.example.finance.repository.CurrencyRepository;
import com.example.finance.repository.CursRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "curs")
public class CursServiceImpl implements CursService {

    private final CursRepository cursRepository;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public CursServiceImpl(CursRepository cursRepository, CurrencyRepository currencyRepository) {
        this.cursRepository = cursRepository;
        this.currencyRepository = currencyRepository;
    }

    private final String BASE_URL_CURS_CENTRAL_BANK = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=";

    @Override
    @SneakyThrows
    @Cacheable(key = "#date")
    public Map<String, BigDecimal> convertRublesToCurrency(BigDecimal sum, LocalDate date) {
        if (cursRepository.getCursByDate(date).isEmpty()) {
            saveCursByDate(date);
        }
        return countCurrencyOfRubbles(sum, date);
    }

    @SneakyThrows
    private void saveCursByDate(LocalDate date) {
        JAXBContext jaxbContext = JAXBContext.newInstance(Curs.class);
        Curs curs = (Curs) jaxbContext.createUnmarshaller()
                .unmarshal(new URL(BASE_URL_CURS_CENTRAL_BANK
                        + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        if (curs.getValute() == null) {
            throw new NoQuoterAGivenDay("Нет котировок на заданный день");
        }
        curs.setDate(date);
        curs.getValute().forEach(e -> e.setCurs(curs));
        cursRepository.save(curs);
        currencyRepository.saveAll(curs.getValute());
    }

    private Map<String, BigDecimal> countCurrencyOfRubbles(BigDecimal sum, LocalDate date) {
        List<Currency> currencies = currencyRepository.getCurrenciesByCurs_Date(date);
        Map<String, BigDecimal> result = new HashMap<>();
        currencies.stream()
                .filter(e -> e.getName().equals("Доллар США") || e.getName().equals("Евро"))
                .forEach(e -> result
                        .put(e.getName(),
                                sum.divide(new BigDecimal(e.getValue()
                                        .replaceAll(",", ".")), 2, 2)));
        return result;
    }
}
