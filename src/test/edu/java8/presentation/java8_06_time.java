package edu.java8.presentation;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import static edu.java8.presentation.assertion.DateAssertion.assertThat;

public class java8_06_time {

    @Test
    public void shouldCreateLocalDateTime() {
        //given
        LocalDate today = LocalDate.of(2014,03, 31);
        LocalTime presentationTime = LocalTime.of(16, 30);
        //when
        LocalDateTime todayWithTime = LocalDateTime.of(today, presentationTime);
        //then
        assertThat(todayWithTime).isYear(2014).isMonth(3).isDay(31).isHour(16).isMinute(30);

    }

    @Test
    public void shouldCreateLocalDateTimeFromZoneId() {
        //given
        LocalDateTime zoneId = LocalDateTime.from(ZonedDateTime.now(ZoneId.of("Europe/Paris")));
        LocalDateTime now = LocalDateTime.now();
        //when
        //then
        Assertions.assertThat(zoneId.getYear()).isEqualTo(now.getYear())  ;

    }

    class CustomClock extends Clock {

        private Instant instant;

        @Override
        public ZoneId getZone() {
            return ZoneId.systemDefault();
        }

        @Override
        public Clock withZone(ZoneId zone) {
            return Clock.system(zone);
        }

        @Override
        public Instant instant() {
            return instant;
        }
    }

    @Test
    public void shouldUseBuildInMethod() {
        //given
         //when
        LocalDate notLeap = LocalDate.of(2014,02,01);
        LocalDate leap = LocalDate.of(2012,02,01);
        //then
        Assertions.assertThat(notLeap.isLeapYear()).isFalse();
        Assertions.assertThat(leap.isLeapYear()).isTrue();
        Assertions.assertThat(notLeap.lengthOfMonth()).isEqualTo(28);
        Assertions.assertThat(leap.lengthOfMonth()).isEqualTo(29);
    }

    @Test
    public void shouldUseTemporalAdjuster() {
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2014,03,31, 16,30);
        //when
        LocalDateTime lastFridayInMonth = localDateTime.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
        //then
        assertThat(lastFridayInMonth).isDay(28);
    }

    @Test
    public void shouldFormatDate() {
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2014,3,31,16,30);
        //when
        String format = localDateTime.format(DateTimeFormatter.ofPattern("mm-yyyy-dd mm:HH"));
        //then
        Assertions.assertThat(format).isEqualTo("03-2014-31 30:16");
    }

}
