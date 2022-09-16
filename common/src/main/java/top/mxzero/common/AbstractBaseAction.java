package top.mxzero.common;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author zero
 * @email qianmeng6879@163.com
 * @since 2022/9/15
 */
public class AbstractBaseAction {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_Time_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(java.util.Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                LocalDate localDate = LocalDate.parse(text, DATE_FORMATTER);
                Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                setValue(java.util.Date.from(instant));
            }
        });
    }

    protected String format(LocalDateTime localDateTime){
        return localDateTime.format(DATE_Time_FORMATTER);
    }

    protected String format(java.util.Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).format(DATE_Time_FORMATTER);
    }
}
