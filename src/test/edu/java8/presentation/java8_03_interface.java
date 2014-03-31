package edu.java8.presentation;


import edu.java8.presentation.domain.Car;
import edu.java8.presentation.builder.CarTestBuilder;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Defender Methods or Virtual Extension methods
 */
public class java8_03_interface {

    private interface BeerTimer {
        default String itIsTime() {
            return "yes";
        }

        BigDecimal budget();
    }

    @Test
    public void shouldNotBeForcedToImplementDefaultMethod() {
        //given
        class CompanyBeerTimer implements BeerTimer {

            @Override
            public BigDecimal budget() {
                return BigDecimal.valueOf(300.00);
            }
        }
        //when
        CompanyBeerTimer companyBeerTimer = new CompanyBeerTimer();
        //then
        assertThat(companyBeerTimer.getClass().getDeclaredMethods()).hasSize(1);
        assertThat(companyBeerTimer.itIsTime()).isEqualTo("yes");

    }

    private interface Male {
        default String sex() {
            return "Male";
        }
    }

    private interface Female {
        default String sex() {
            return "Female";
        }
    }

    @Test
    public void shouldBeAbleToImplementSameMethodsFromDifferentInterfaces() {
        //given
        class Gender implements Male, Female {
            @Override
            public String sex() {
                return Male.super.sex() + " " + Female.super.sex();
            }
        }
        //when
        String quote = new Gender().sex();

        //then
        assertThat(quote).isEqualTo("Male Female");
    }

    private interface Ring {

        static String destination() {
            return "One Ring to rule them all.";
        }
    }

    @Test
    public void shouldReturnMessageFromStaticMethod() {
        //given
        //when
        String destination = Ring.destination();
        //then
        assertThat(destination).isEqualTo("One Ring to rule them all.");
    }

    private interface Beer {
        default String name() {
            return "I'm a beer. But i don't know my name.";
        }
    }

    private interface Guinness extends Beer {
        default String name() {
            return "I'm Guinness";
        }
    }

    @Test
    public void shouldOverrideBeAllowed(){
        //given
        class MyBeer implements Guinness {}
        //when
        MyBeer myBeer = new MyBeer();
        //then
        assertThat(myBeer.name()).isEqualTo("I'm Guinness");
    }

    @FunctionalInterface
    public interface CommissionAppraiser {
        void increasePrice(Car car);
    }

    @Test
    public void shouldUseCustomFunctionalInterfaceWithLambda() {
        //given
        class CarCommission {

            private final Car car;
            CarCommission(Car car) {
                this.car = car;
            }

            void acceptCar(CommissionAppraiser appraiser) {
                appraiser.increasePrice(car);
            }
        }
        Car astra = CarTestBuilder.car().withModel("Astra").withPrice("1000").build();

        //when
        new CarCommission(astra).acceptCar(car -> car.changePrice("20000"));
        //then
        assertThat(astra.getPrice()).isEqualByComparingTo(new BigDecimal("20000"));
    }
}
