package pl.yellowduck.streamapi;

import pl.yellowduck.streamapi.domain.Account;
import pl.yellowduck.streamapi.domain.AccountType;
import pl.yellowduck.streamapi.domain.Currency;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MainAccount {

    public static void main(String[] args) throws URISyntaxException, IOException {
        // imperatywnie za pomoca BufferedReadera (wydajonosc pamieci)
//        try(BufferedReader bufferedReader = Files.newBufferedReader(file())) {
//            String line;
//            while((line = bufferedReader.readLine()) != null) {
//                String[] split = line.split("|");
//
//            }
//        }
        Files.lines(file())
                .map(s -> s.split("\\|"))
                .map(MainAccount::build)
                .filter(account -> account.getCurrency().equals(Currency.PLN))
                .forEach(System.out::println);
    }

    private static Account build(String[] obj) {
        return Account.builder()
                .type(AccountType.ROR1)
                .number(obj[0])
                .amount(getAmount(obj[1]))
                .currency(Currency.valueOf(obj[2]))
                .build();
    }

    private static BigDecimal getAmount(String source) {
        try {
            return BigDecimal.valueOf(NumberFormat.getInstance().parse(source).doubleValue());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Path file() throws URISyntaxException {
        return Path.of("accounts.txt");
    }
}
