package flyingideal.org.springframework.format;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.number.CurrencyStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

/**
 * Created by Administrator on 2017/6/30.
 */
public class DefaultFormattingConversionServiceTest {

    @Test
    public void testWithDefaultFormattingConversionService() {
        DefaultFormattingConversionService conversionService =
                new DefaultFormattingConversionService();

        CurrencyStyleFormatter formatter = new CurrencyStyleFormatter();
        formatter.setFractionDigits(2); //保留小数点后几位
        formatter.setRoundingMode(RoundingMode.CEILING);

        conversionService.addFormatter(formatter);

        LocaleContextHolder.setLocale(Locale.US);
        Assert.assertEquals("$123.12", conversionService.convert(new BigDecimal("123.12"), String.class));

        LocaleContextHolder.setLocale(Locale.CHINA);
        Assert.assertEquals(new BigDecimal(123.12), conversionService.convert("￥123.12", BigDecimal.class));
    }
}
